package com.bwei.day01_Fresco;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 2018/9/2.
 * 注解类
 */
@Retention(RetentionPolicy.RUNTIME)//可以在JVM中读取Annotation信息。告诉这个注解在使用的时候即可以用到.Class文件，又能在运行过程中通过反射的方式读取出来
@Target(ElementType.TYPE)
public @interface ContentView {

    int value();
}
