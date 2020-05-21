package com.example.demo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})//Annotation所修饰的对象范围:方法参数
@Retention(RetentionPolicy.RUNTIME)//Annotation被保留时间:运行时保留(有效)
public @interface CurrentUser {
}
