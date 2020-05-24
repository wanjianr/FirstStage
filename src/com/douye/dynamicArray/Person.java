package com.douye.dynamicArray;

public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // 对象销毁后执行
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("person:"+name+"已经销毁！");
    }

    // 默认比较两个对象的地址是否相同
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Person){
            Person person = (Person) obj;
            return this.age == person.age;
        }
        return false;
    }
}
