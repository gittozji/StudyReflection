import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static java.lang.System.*;

/**
 * Created by imyu on 2017/8/7.
 */
public class Employee extends Person {
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPublicField() {
        return publicField;
    }

    public void setPublicField(String publicField) {
        this.publicField = publicField;
    }

    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    private double salary;

    public String publicField;

    public void publicMethod() {
        out.println("public method.");
    }

    private void privateMethod() {
        out.println("private method");
    }

    public final static void main(String[] args) {
        /**测试父类**/
        {
            /** 通过反射创建一个对象 **/
            try {
                // 此处要求Person有默认无参构造方法。如果重写了Person带参构造方法，则需要在写一个Person无参构造方法。
                Person person = (Person) Class.forName("Person").newInstance();
                person.setName("zji");
                System.out.println("--->>>通过最简单的newInstance方法生成实例，调用sayHello（）方法");
                person.sayHello();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Class clazz = Person.class;
            /**调用类的构造方法**/
            try {
                Constructor constructor = clazz.getDeclaredConstructor(String.class, int.class);
                Person person = (Person) constructor.newInstance(new Object[]{"zji", 1});
                out.println("--->>>通过带参构造器生成的实例，调用sayHello（）方法：");
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
            /**操作类的指定域**/
            try {
                Person person = new Person("zji", 100);
                Field name = clazz.getDeclaredField("name");
                //不加该方法，会抛异常：java.lang.IllegalAccessException: Class Employee can not access a member of class Person with modifiers "private"
                name.setAccessible(true);
                //
                name.set(person, "imyu");
                System.out.println("--->>>操作类的私有域");
                person.sayHello();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            /**调用类的指定方法**/
            try {
                Person person = (Person) clazz.newInstance();
                Method method0 = clazz.getDeclaredMethod("think", null);
                out.println("--->>>通过反射调用私有方法：");
                //不加该方法，会抛异常：java.lang.IllegalAccessException: Class Employee can not access a member of class Person with modifiers "private"
                method0.setAccessible(true);
                //
                method0.invoke(person);
                Method method = clazz.getDeclaredMethod("setName", String.class);
                out.println("--->>>通过反射调用带参公共方法：");
                method.invoke(person, "zji");
                person.sayHello();
                Method method1 = clazz.getDeclaredMethod("date", Date.class, String.class);
                out.println("--->>>通过反射调用带多参公共方法：");
                method1.invoke(person, new Date(), "HZ");
                Method method2 = clazz.getDeclaredMethod("staticMethod");
                out.println("--->>>通过反射调用类静态方法：");
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

        /**测试子类**/
        {
            Class clazz = Employee.class;
            Field[] fields = clazz.getFields();
            out.println("--->>>使用getFields()方法，输出公共域的信息，包括父类：");
            for (Field field : fields){
                out.println(field.toString());
            }

            Field[] declaredFields = clazz.getDeclaredFields();
            out.println("--->>>使用getDeclaredFields()方法，输出本类域的信息，不包括父类：");
            for (Field declaredField : declaredFields) {
                out.println(declaredField.toString());
            }
        }
    }
}
