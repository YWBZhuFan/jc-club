package com.jichi.gateway.filter;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ZhuFan
 * @data 2024/10/13/013 0:05
 */
@Slf4j
@Component
public class LoginFilter implements GlobalFilter {
    @Override
    @SneakyThrows
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获得一个ServerHttpRequest.Builder对象，允许对原始请求进行修改而不改变其本身。
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        log.info("LoginFilter.filter.url：{},", url);
        if (url.equals("/user/doLogin")){
            // 直接放行
            return chain.filter(exchange);
        }
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        String loginId = tokenInfo.getLoginId().toString();
        if (StringUtils.isEmpty(loginId)){
            throw new Exception("未获取到用户信息");
        }
        mutate.header("loginId", loginId);
        // 构建新的请求对象，这里也就是往请求头部添加了loginId并重新构建了request进行后面的逻辑
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
