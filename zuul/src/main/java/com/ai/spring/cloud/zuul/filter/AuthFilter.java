package com.ai.spring.cloud.zuul.filter;

import com.ai.spring.cloud.zuul.service.AuthService;
import com.ai.spring.cloud.zuul.util.WebUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

public class AuthFilter extends ZuulFilter {

    public static final String AUHTHRIZATION_HEADER = "Authorization";

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Autowired
    private ProxyRequestHelper helper;

    @Autowired
    private AuthService authService;

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1; // run before PreDecoration
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String url = this.helper.buildZuulRequestURI(ctx.getRequest());
//        return WebUtils.shouldFilter(url);
        return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
                && !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        String url = this.helper.buildZuulRequestURI(request);
        String method = request.getMethod();
        String authType = request.getAuthType();
        String auth = request.getHeader(AUHTHRIZATION_HEADER);
        auth = !StringUtils.isEmpty(auth) ? auth : WebUtils.getCookie(AUHTHRIZATION_HEADER, request);
        log.info("Get into AuthFilter filter, url: {}, method: {}, authType: {}, Authentication: {}", url, method, authType, auth);
        boolean authorized = false;
        try {
            authorized = authService.verify(auth);
        } catch (Exception e) {
            log.info("verify token failed due to reason: {}", e.getMessage());
        }

        if (!authorized) {
            log.error("Un-Authorized user");
            RequestDispatcher requestDispatcher = null;
            if (WebUtils.isAjaxRequest(request)) {
                log.error("Ajax Request, return error JSON");
                request.setAttribute("errorType", "Authorization");
                request.setAttribute("errorCode", "401");
                request.setAttribute("errorMessage", "Un-Authorized");
                requestDispatcher = request.getRequestDispatcher("/sso/error");
            } else {
                log.error("Normal Request, Jump to login page");
                requestDispatcher = request.getRequestDispatcher("/sso/login");
            }
            try {
                requestDispatcher.forward(request, response);
            } catch (Exception e) {
                log.error("Error caused when forward to login page", e);
            }
        }
        return null;
    }
}
