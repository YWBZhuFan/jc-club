package com.jichi.subject.infra.rpc;

import com.jichi.auth.api.UserFeignService;
import com.jichi.auth.entity.AuthUserDTO;
import com.jichi.auth.entity.Result;
import com.jichi.subject.infra.entity.UserInfo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author ZhuFan
 * @data 2024/10/13/013 13:32
 */
@Component
public class UserRpc {

    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()) {
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setUserName(data.getUserName());
        userInfo.setNickName(data.getNickName());
        return userInfo;
    }
}
