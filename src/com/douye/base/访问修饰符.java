package com.douye.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class 访问修饰符 {
    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Class demoClass = demo.getClass();
        try {
            Method getAge = demoClass.getDeclaredMethod("getAge", int.class);
            Method getName = demoClass.getMethod("getName", String.class);
            Method getDesc = demoClass.getMethod("getDesc");
            Method getStatic = demoClass.getMethod("getStatic");
            System.out.println(getAge);
            System.out.println(getName);
            System.out.println(getDesc);
            System.out.println("---------------");
            // 通过反射调用private方法
            getAge.setAccessible(true);
            System.out.println(getAge.invoke(demo, 100));
            // 调用public方法
            System.out.println(getName.invoke(demo,"xiaohaong"));
            // 调用静态方法时不用传指定的实例
            System.out.println(getStatic.invoke(null));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class Demo {
    private Integer age;
    public String name;
    protected String desc;

    private int getAge(int age) {
        return age;
    }

    public String getName(String name) {
        return name;
    }

    public String getDesc() {
        return "desc";
    }

    public static String getStatic() {
        return "static";
    }
}