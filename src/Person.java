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

    public String getPublicValue() {
        return publicValue;
    }

    public void setPublicValue(String publicValue) {
        this.publicValue = publicValue;
    }

    public String publicValue;

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
}
