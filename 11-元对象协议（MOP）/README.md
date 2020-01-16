# 元对象协议（MetaObject Protocol，MOP）
在 Java 中，可以通过反射在运行时探索对象的结构，操作类、方法、方法的参数，然而这仅仅局限于所创建的静态结构。在 Groovy 中，基于元编程能力，
可以在程序运行时修改对象的类型，动态的添加方法，使代码更具灵活性。

元编程意味着能够编写操作程序的程序，包括操作程序本身。

## Groovy 对象
在 Groovy 应用中，存在三种对象：POJO、POGO、Groovy拦截器

- POJO：普通 java 对象；
- POGO：用 Groovy 编写的对象，拓展了 java.lang.Object，实现了 groovy.lang.GroovyObject 接口；
- Groovy拦截器：拓展了 GroovyInterceptable 的 Groovy 对象，具有方法拦截功能。

**GroovyObject**

GroovyObject 接口定义了以下方法：

```java
Object invokeMethod(String name, Object args);
Object getProperty(String propertyName);
void setProperty(String propertyName, Object newValue);
MetaClass getMetaClass();
void setMetaClass(MetaClass metaClass);
```

invokeMethod、getProperty、setProperty 可用来处理运行中创建的方法和属性。getMetaClass、setMetaClass 可用来创建代理拦截方法和注入方法。
一个对象被加载进 JVM 其元对象 class 不能被修改，但是可以通过调用 setMetaClass() 修改它的 metaClass，实现修改了类的效果。

**GroovyInterceptable**

GroovyInterceptable 是拓展了 GroovyObject 的标记接口。对于实现了该接口的类，其上的所有方法调用不管是否存在都会被它的 invokeMethod() 方法拦截。

```java
public interface GroovyInterceptable extends GroovyObject {
}
```

Groovy 支持对 POJO 和 POGO 进行元编程。对于 POJO，Groovy 维护了一个 MetaClassRegister，其包含了一个 MetaClass。对于 POGO，Groovy 有一个到其 MetaClass 的直接引用。

### 方法调用机制
调用一个方法时，Groovy 会检查对象是一个 POJO 还是 POGO，对于不同的类型处理方式是不一样的。

1）对于 POJO，Groovy 会去其的 MetaClassRegister 上找 MetaClass，并将方法委托给它。因此可以在其 MetaClass 上定义拦截器或方法。

2）对于 POGO，Groovy 会采用一些额外的步骤。如果对象实现了 GroovyInterceptable，所有的调用都会路由到它的 invokeMethod()，
在这个拦截器内可以把调用路由给实际的方法，使类的 AOP 成为可能。

![](http://img.yuzh.xyz/20200116150004_ofi6Wz_Screenshot.png)

_测试代码：TestMethodInvocation.groovy_
