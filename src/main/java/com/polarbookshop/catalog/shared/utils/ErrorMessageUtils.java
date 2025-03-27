package com.polarbookshop.catalog.shared.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ErrorMessageUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ErrorMessageUtils.context = applicationContext;
    }

    public static String getMessage(String code) {
        return context.getMessage(code,
                null,
                "The message code '" + code + "' is not defined",
                LocaleContextHolder.getLocale()
        );
    }

    public static String getMessage(String code, final String[] args) {
        return context.getMessage(code,
                args,
                "The message code '" + code + "' is not defined",
                LocaleContextHolder.getLocale()
        );
    }
}
