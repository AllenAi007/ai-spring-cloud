package com.ai.spring.cloud.zuul.filter;

import com.ai.spring.cloud.zuul.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

public class QueryParamPreFilter extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(QueryParamPreFilter.class);

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
        String auth = request.getHeader("Authentication");
//        request.get
        log.info("Get into QueryParamPreFilter filter, url: {}, method: {}, authType: {}, Authentication: {}", url, method, authType, auth);
        boolean authorized = authService.verify(auth);
        if (authorized) {
            log.info("token {} is authorized", auth);
        } else {
            log.error("token {} is not authorized , jumping to login page", auth);
            try {
                request.getRequestDispatcher("/login").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
