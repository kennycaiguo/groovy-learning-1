/**
 * 当 Java 类依赖其他 Java 类时，如果没有找到字节码，javac 将会将编译其认为必须的字节码。但是 Java 依赖 Groovy 时不会自动编译。
 * @author Harry Zhang
 * @since 2020/1/10 11:28 AM
 */
public class AJavaClass {

    {
        System.out.println("Created Java Class.");
    }

    public void sayHello(){
        System.out.println("hello!");
    }

}
