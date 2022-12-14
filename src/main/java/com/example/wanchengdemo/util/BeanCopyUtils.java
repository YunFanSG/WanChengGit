package com.example.wanchengdemo.util;

import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 对象属性赋值工具类
 *
 * @author explore
 * @since 2019/11/30 22:53
 **/
public class BeanCopyUtils extends BeanUtils {

    /**
     * 如果目标对象与源对象有相同字段，并且不为空，则忽略复制
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        if (source != null && target != null) {
            List<Field> fieldList = ReflectionKit.getFieldList(target.getClass());
            Set<String> set = new HashSet<>();
            try {
                for (Field field : fieldList) {
                    field.setAccessible(true);
                    Optional.ofNullable(field.get(target)).ifPresent(e -> set.add(field.getName()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String[] ignoreProperties = set.toArray(new String[0]);
            BeanUtils.copyProperties(source, target, ignoreProperties);
        }
    }

    /**
     * 复制对象属性
     *
     * @param source 源对象
     * @param clazz  目标类对象
     * @param <T>    目标类泛型
     * @return 目标对象实例
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
