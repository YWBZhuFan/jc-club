package com.jichi.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jichi.auth.common.enums.AuthUserStatusEnum;
import com.jichi.auth.common.enums.IsDeletedFlagEnum;
import com.jichi.auth.domain.convert.AuthUserBOConverter;
import com.jichi.auth.domain.entity.AuthUserBO;
import com.jichi.auth.domain.redis.RedisUtil;
import com.jichi.auth.domain.service.AuthUserDomainService;
import com.jichi.auth.infra.basic.entity.*;
import com.jichi.auth.infra.basic.service.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jichi.auth.domain.constants.AuthConstant.NORMAL_USER;

@Service
@Slf4j
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource private AuthUserService authUserService;
    @Resource private AuthUserRoleService authUserRoleService;
    @Resource private AuthPermissionService authPermissionService;
    @Resource private AuthRolePermissionService authRolePermissionService;
    @Resource private AuthRoleService authRoleService;
    @Resource private RedisUtil redisUtil;

    private static final String LOGIN_PREFIX = "loginCode";
    private String authPermissionPrefix = "auth.permission";
    private String authRolePrefix = "auth.role";
    private String salt = "ywb123456";

    /**
     * 注册
     *
     * @param authUserBO
     */
    @Override
    @SneakyThrows
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser existAuthUser = new AuthUser();
        existAuthUser.setUserName(authUserBO.getUserName());
        // 校验用户是否存在
        List<AuthUser> authUsers = authUserService.queryByCondition(existAuthUser);
        if (!authUsers.isEmpty()){
            return true;
        }
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        if (StringUtils.isNotBlank(authUser.getPassword())){
            authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), salt));
        }
        authUser.setStatus(AuthUserStatusEnum.OPEN.getCode());
        authUser.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        Integer count = authUserService.insert(authUser);
        // 与角色关联
        AuthRole authRole = new AuthRole();
        authRole.setRoleKey(NORMAL_USER);
        AuthRole roleResult = authRoleService.queryByCondition(authRole);
        Long roleId = roleResult.getId();
        Long userId = authUser.getId();
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        authUserRole.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
        authUserRoleService.insert(authUserRole);

        String roleKey = redisUtil.buildKey(authRolePrefix, authUser.getUserName());
        List<AuthRole> roleList = new LinkedList<>();
        roleList.add(authRole);
        redisUtil.set(roleKey, new Gson().toJson(roleList));

        AuthRolePermission authRolePermission = new AuthRolePermission();
        authRolePermission.setRoleId(roleId);
        List<AuthRolePermission> rolePermissionList = authRolePermissionService.
                queryByCondition(authRolePermission);

        List<Long> permissionIdList = rolePermissionList.stream()
                .map(AuthRolePermission::getPermissionId).collect(Collectors.toList());
        //根据roleId查权限
        List<AuthPermission> permissionList = authPermissionService.queryByRoleList(permissionIdList);
        String permissionKey = redisUtil.buildKey(authPermissionPrefix, authUser.getUserName());
        redisUtil.set(permissionKey, new Gson().toJson(permissionList));

        return count > 0;
    }

    /**
     * 更新用户信息
     *
     * @param authUserBO
     */
    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authUserService.updateByUserName(authUser);
        return count > 0;
    }

    @Override
    public Boolean updateUserStatus(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBOToEntity(authUserBO);
        Integer count = authUserService.updateById(authUser);
        return count > 0;
    }


    /**
     * 更新用户信息
     *
     * @param authUserBO
     */
    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = new AuthUser();
        authUser.setId(authUserBO.getId());
        authUser.setIsDeleted(IsDeletedFlagEnum.DELETED.getCode());
        Integer count = authUserService.update(authUser);
        //有任何的更新，都要与缓存进行同步的修改
        return count > 0;
    }

    @Override
    public SaTokenInfo doLogin(String validCode) {
        String key = redisUtil.buildKey(LOGIN_PREFIX, validCode);
        String openId = redisUtil.get(key);
        if (StringUtils.isBlank(openId)){
            return null;
        }
        AuthUserBO authUserBO = new AuthUserBO();
        authUserBO.setUserName(openId);
        Boolean isSuccess = register(authUserBO);
        if (!isSuccess){
            return null;
        }
        StpUtil.login(openId);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        return null;
    }

    @Override
    public List<AuthUserBO> listUserInfoByIds(List<String> ids) {
        return null;
    }
}
