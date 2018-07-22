package ru.bellintegrator.practice.validate;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseSuccess implements ResponseBodyAdvice<Object> {


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2,
                                       Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {
        // Get a handle to your response object and make changes here
        if(arg1.getParameterType().getSimpleName().equals("void")) {
            SuccessView successView = new SuccessView();
            successView.result = "success";
            return successView;
        };
        return body;
    }

    @Override
    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
        return true;
    }
}
