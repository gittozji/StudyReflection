import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by imyu on 2017/8/3.
 */
public class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    private void think() {
        System.out.println("I am thinking.");
    }

    public void sayHello() {
        System.out.println("Hello, I am " + name + ".");
    }

    public void date(Date date, String place) {
        System.out.println(date.toString() + " " + place);
    }

    public static void staticMethod() {
        System.out.println("I am a static method");
    }

    public final static void main(String[] args) {
        /** 通过反射创建一个对象 **/
        try {
            // 此处要求Person有默认无参构造方法。如果重写了Person带参构造方法，则需要在写一个Person无参构造方法。
            Person person = (Person) Class.forName("Person").newInstance();
            person.setName("zji");
            person.sayHello();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**类的字段信息**/
        Class clazz = Person.class;
        System.out.println("类名：" + clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("类字段：");
        for (Field field : fields) {
            System.out.println("    " + field.toString());
        }

        /**类方法**/
        Method[] methods = clazz.getDeclaredMethods();
        System.out.println("类方法（不包括父类方法）：");
        for (Method method : methods) {
            System.out.println("    " + method.toString());
        }

        /**调用类的构造方法**/
        try {
            Constructor constructor = clazz.getDeclaredConstructor(new Class[] { String.class, int.class });
            Person person = (Person) constructor.newInstance(new Object[]{"zji", 1});
            System.out.println("通过带参构造器生成的实例，调用sayHello（）方法：");
            person.sayHello();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /**调用类的指定方法**/
        try {
            Person person = (Person) clazz.newInstance();
            Method method0 = clazz.getDeclaredMethod("think", null);
            System.out.println("通过反射调用私有方法：");
            method0.invoke(person);
            Method method = clazz.getDeclaredMethod("setName", String.class);
            System.out.println("通过反射调用带参公共方法：");
            method.invoke(person, "zji");
            person.sayHello();
            Method method1 = clazz.getDeclaredMethod("date", Date.class, String.class);
            System.out.println("通过反射调用带多参公共方法：");
            method1.invoke(person, new Date(), "HZ");
            Method method2 = clazz.getDeclaredMethod("staticMethod");
            System.out.println("通过反射调用类静态方法：");
            method2.invoke(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
