package com.example.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware, BeanFactoryPostProcessor {
    static {
        System.out.println("i am applicationContextHelper");
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("set applicationContext");
        ApplicationContextHelper.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext != null ? applicationContext.getBean(beanName) : null;
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext != null ? applicationContext.getBean(type) : null;
    }

    public static <T> T getBean(String beanName, Class<T> type) {
        return applicationContext != null ? applicationContext.getBean(beanName, type) : null;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBean");
    }
}
