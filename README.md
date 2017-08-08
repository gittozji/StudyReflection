# 学习反射相关知识


## 代码输出

```
--->>>通过最简单的newInstance方法生成实例，调用sayHello（）方法
Hello, I am zji.
--->>>通过带参构造器生成的实例，调用sayHello（）方法：
Hello, I am zji.
--->>>操作类的私有域
Hello, I am imyu.
--->>>通过反射调用私有方法：
I am thinking.
--->>>通过反射调用带参公共方法：
Hello, I am zji.
--->>>通过反射调用带多参公共方法：
Mon Aug 07 19:34:18 GMT+08:00 2017 HZ
--->>>通过反射调用类静态方法：
I am a static method
--->>>使用getFields()方法，输出公共域的信息，包括父类：
public java.lang.String Employee.publicField
public java.lang.String Person.publicValue
--->>>使用getDeclaredFields()方法，输出本类域的信息，不包括父类：
private double Employee.salary
public java.lang.String Employee.publicField
```

## 获取成员的方法

`Class`类提供获取`Constructor`、`Field`、`Method`等方法。关于是否带`Declared`的方法，如`getDeclaredMethods()`与`getMethods()`，其本质不同在于`Member.DECLARED`与`Member.PUBLIC`的区别。

文档对这两个变量的注释如下：

```
/**
 * Identifies the set of all public members of a class or interface,
 * including inherited members.
 * @see java.lang.SecurityManager#checkMemberAccess
 */
public static final int PUBLIC = 0;

/**
 * Identifies the set of declared members of a class or interface.
 * Inherited members are not included.
 * @see java.lang.SecurityManager#checkMemberAccess
 */
public static final int DECLARED = 1;
```

因此带`Declared`的方法能获取本类的成员信息，而不带`Declared`的方法能获取本来和父类的成员信息，但只能是`public`修饰的成员。


> 操作调用类的私有成员，需要调用`setAccessible(true)`方法，否则会抛`java.lang.IllegalAccessException`异常。
