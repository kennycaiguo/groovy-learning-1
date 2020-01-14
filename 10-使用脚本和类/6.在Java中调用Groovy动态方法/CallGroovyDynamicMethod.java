import groovy.lang.GroovyObject;

/**
 * 在 Java 中调用 Groovy 的动态方法，即：在 Java 中调用 Groovy 运行时生成的方法。
 * <p>
 * 每一个 Groovy 类都实现了 GroovyObject 接口，该接口有一个方法 invokeMethod() 可用于调用 Groovy 使用元编程动态生成的方法。
 * <p>
 * 使用 GroovyObject 接口，在 Java 端可以调用 Groovy 实例的任意预定义方法和动态方法。若方法不存在，会进入到特殊方法 methodMissing()
 *
 * @author Harry Zhang
 * @since 2020/1/13 10:46 PM
 */
public class CallGroovyDynamicMethod {
    public static void main(String[] args) {
        GroovyObject groovyObject = new DynamicGroovyClass();
        Object result1 = groovyObject.invokeMethod("method1", new Object[]{1, 2, 3});
        System.out.println("result1: " + result1);

        Object result2 = groovyObject.invokeMethod("method2", new Object[]{"like", "a", "cat"});
        System.out.println("result2: " + result2);
    }
}
