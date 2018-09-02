package com.bwei.day01_Fresco;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created on 2018/9/2.
 */
public class MyButterKnife {
    
    public static Activity contenView;
    
    public static void bind(Activity activity){
        setContentView(activity);
        bindView(activity);
        process(activity);
    }

    public static void process( final Object o) {
        Class<?> c = o.getClass();
        Method[] methods = c.getDeclaredMethods();
        for (final Method m:methods){
            MyOnClick click = m.getAnnotation(MyOnClick.class);//通过反射api获取方法上面的注解
            if (click!=null){
                if (o instanceof Activity){
                    if (click.value()==-1)return;
                    View view = ((Activity) o).findViewById(click.value());//通过注解的值获取View控件
                    if (view==null)return;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                m.invoke(o);//通过反射来调用被注解修饰的方法
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }

    }

    private static void bindView(Activity activity) {
        Class<? extends Activity> c = activity.getClass();
        Field[] fields = c.getDeclaredFields();
        if (fields!=null && fields.length>0){
            for (Field field:fields){
                field.setAccessible(true);//让属性，方法，构造函数等等可见，public，允许访问
                try {
                    MyBandView myBandView = field.getAnnotation(MyBandView.class);
                    if (myBandView!=null){
                        View view = activity.findViewById(myBandView.value());
                        field.set(activity, view);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void setContentView(Activity activity) {

        Class<? extends Activity> c = activity.getClass();
        try {
            Method method = c.getMethod("setContentView", int.class);
            ContentView contentView = c.getAnnotation(ContentView.class);
            if (contentView!=null){
                method.invoke(activity,contentView.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
