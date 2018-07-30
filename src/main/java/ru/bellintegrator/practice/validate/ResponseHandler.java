package ru.bellintegrator.practice.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.validate.view.DataView;
import ru.bellintegrator.practice.validate.view.ErrorView;
import ru.bellintegrator.practice.validate.view.SuccessView;

@RestControllerAdvice(basePackages = "ru.bellintegrator.practice")
public class ResponseHandler implements ResponseBodyAdvice<Object> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter arg1, MediaType arg2,
                                       Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest arg4, ServerHttpResponse arg5) {

        // Get a handle to your response object and make changes here
        if(arg1.getParameterType().getSimpleName().equals("void")) {
            SuccessView successView = new SuccessView();
            DataView dataView = new DataView(successView);
            return dataView;
        };

        if(arg1.getParameterType().getSimpleName().equals("ErrorView")) {
            return body;
        };

        DataView dataView = new DataView(body);
        return dataView;
    }

}
