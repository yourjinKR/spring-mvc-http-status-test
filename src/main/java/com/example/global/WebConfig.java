package com.example.global;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements InitializingBean {

    private final RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> defaultHandlers = adapter.getReturnValueHandlers();

        assert defaultHandlers != null;
        ArrayList<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();

        CustomRequestResponseBodyMethodProcessor customProcessor = new CustomRequestResponseBodyMethodProcessor(adapter.getMessageConverters());
        handlers.add(customProcessor);
        handlers.addAll(defaultHandlers);

        adapter.setReturnValueHandlers(handlers);
    }
}
