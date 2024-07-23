package ru.logs.log;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LogTransformationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(LogTransformation.class)) // если на компоненте установлена аннотация
            return Utils.cache(bean);

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
