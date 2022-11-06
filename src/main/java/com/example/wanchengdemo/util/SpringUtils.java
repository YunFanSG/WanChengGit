

package com.example.wanchengdemo.util;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alibaba.druid.sql.ast.expr.SQLSizeExpr.Unit.T;

@ConditionalOnMissingBean(value = SpringUtils.class)
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static ConfigurableListableBeanFactory bean;

    private static ApplicationContext applicationContext;

    public static <T> T getBean(String name) throws BeansException {
        return (T) bean.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        T result = bean.getBean(clazz);
        return result;
    }

    public static <T> List<T> getBeans(Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        Map<String, T> beansOfType = applicationContext.getBeansOfType(clazz);
        beansOfType.forEach((k, v) -> list.add(v));
        return list;
    }


    public static boolean containsBean(String name) {
   return bean.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return bean.isSingleton(name);

}
        public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {

            return bean.getType(name);
        }
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {

        return bean.getAliases(name);
    }
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }

    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    public static String getActiveProfile() {
        final String[] activeProfiles = getActiveProfiles();
        return activeProfiles.length > 0 ? activeProfiles[0] : null;
    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bean) throws BeansException {
    SpringUtils.bean = bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
  SpringUtils.applicationContext = applicationContext;
    }
}