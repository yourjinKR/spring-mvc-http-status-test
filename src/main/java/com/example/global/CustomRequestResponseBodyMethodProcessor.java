package com.example.global;

import com.example.controller.CommonResponse;
import java.io.IOException;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.ErrorResponse.Interceptor;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class CustomRequestResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {
    public CustomRequestResponseBodyMethodProcessor(
            List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters,
                                                    @Nullable ContentNegotiationManager manager) {
        super(converters, manager);
    }

    public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters,
                                                    @Nullable List<Object> requestResponseBodyAdvice) {
        super(converters, requestResponseBodyAdvice);
    }

    public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters,
                                                    @Nullable ContentNegotiationManager manager,
                                                    @Nullable List<Object> requestResponseBodyAdvice) {
        super(converters, manager, requestResponseBodyAdvice);
    }

    public CustomRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters,
                                                    @Nullable ContentNegotiationManager manager,
                                                    List<Object> requestResponseBodyAdvice,
                                                    List<Interceptor> interceptors) {
        super(converters, manager, requestResponseBodyAdvice, interceptors);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return super.supportsReturnType(returnType)
                && CommonResponse.class.isAssignableFrom(
                returnType.getParameterType()
        );
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException,
            HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {

        if (returnValue instanceof CommonResponse<?> response) {
            createOutputMessage(webRequest).setStatusCode(HttpStatusCode.valueOf(response.status()));
        }

        super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
