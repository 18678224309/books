package com.jtfu.zuul.filter;

import com.jtfu.zuul.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx=RequestContext.getCurrentContext();
        //被代理到的微服务
        String proxy = (String)ctx.get("proxy");
        //请求的地址
        String requestURI = (String)ctx.get("requestURI");

        //zuul路由后的url
        System.out.println(proxy+"/"+requestURI);

        HttpServletRequest request = ctx.getRequest();
        String loginCookie = CookieUtil.getLoginCookie(request);
        ctx.addZuulRequestHeader("login_key",loginCookie);
        return null;
    }
}
