/**
 * 在 Java 中调用闭包
 *
 * @author Harry Zhang
 * @since 2020/1/13 10:26 PM
 */
public class UsingAGroovyClass {

    public static void main(String[] args) {
        AGroovyClass groovyClass = new AGroovyClass();

        // 通过匿名内部类的方式给 groovy 方法传递一个闭包参数。
        Object result = groovyClass.useClosure(new Object() {
            public String call() {
                return "...闭包逻辑...";
            }
        });
        System.out.println("Java 接收闭包返回：" + result);

        // 传递带参数的闭包给 groovy 方法。
        Object result2 = groovyClass.useClosure(111, new Object() {
            public String call(int value) {
                return "...闭包逻辑...，闭包参数：" + value;
            }
        });
        System.out.println("Java 接收带参数闭包的返回：" + result2);
    }

}
