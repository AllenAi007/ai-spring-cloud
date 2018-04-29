package com.ai.spring.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.ROUTE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;

public class OkHttpRoutingFilter extends ZuulFilter {

    private static final Logger LOG = LoggerFactory.getLogger(OkHttpRoutingFilter.class);

    @Autowired
    private ProxyRequestHelper helper;

    @Override
    public String filterType() {
        return ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String url = this.helper.buildZuulRequestURI(request);
        String method = request.getMethod();

        LOG.info("Get into routing filter, method: {}, url: {}", method, url);

//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                // customize
//                .build();
//
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//
//        String method = request.getMethod();
//
//        String uri = this.helper.buildZuulRequestURI(request);
//
//        Headers.Builder headers = new Headers.Builder();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            Enumeration<String> values = request.getHeaders(name);
//
//            while (values.hasMoreElements()) {
//                String value = values.nextElement();
//                headers.add(name, value);
//            }
//        }
//
//        InputStream inputStream = request.getInputStream();
//
//        RequestBody requestBody = null;
//        if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
//            MediaType mediaType = null;
//            if (headers.get("Content-Type") != null) {
//                mediaType = MediaType.parse(headers.get("Content-Type"));
//            }
//            requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
//        }
//
//        Request.Builder builder = new Request.Builder()
//                .headers(headers.build())
//                .url(uri)
//                .method(method, requestBody);
//
//        Response response = httpClient.newCall(builder.build()).execute();
//
//        LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
//
//        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
//            responseHeaders.put(entry.getKey(), entry.getValue());
//        }
//
//        this.helper.setResponse(response.code(), response.body().byteStream(),
//                responseHeaders);
//        context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running
        return null;
    }
}