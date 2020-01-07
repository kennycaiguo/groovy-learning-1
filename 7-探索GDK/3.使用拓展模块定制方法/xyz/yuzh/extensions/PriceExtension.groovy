package xyz.yuzh.extensions
/**
 * 使用 Groovy 的拓展模块特性，可以在 编译期间 向现有类添加实例方法或静态方法，并在运行时在应用中使用他们。
 *
 * 【如何使用拓展模块特性？】
 * 1. 想要添加的方法必须定义在一个拓展模块类中（例如：在本类 PriceExtension 中，我们给 String 类添加了一个非静态的拓展方法。）
 * 2. 在清单文件（groovy 的拓展模块配置文件）中放一些描述信息，告诉 groovy 编译器要查找的拓展模块。
 *
 * 添加了拓展方法后，将包含「配置文件和拓展模块类」打成一个 jar 包放在 classpath 下就能被调用使用了。
 *
 * 【创建拓展方法需要注意的点】
 * 1. 拓展方法必须是 static；
 * 2. 第一个参数必须是该方法要加入到的类型，如本类中 getPrice 要拓展的是 String 类，第一个参数就必须是 String；
 *
 * 编写的拓展模块类可以是 Groovy，也可以是 Java。
 *
 * @author Harry Zhang
 * @since 2020/1/5 8:00 PM
 */
class PriceExtension {

    /**
     * 这个方法是添加在 String 上的实例方法，这里并没有体现出是一个实例方法，需要在 manifest 配置文件中定义。
     */
    public static double getPrice(String self) {
        def url = "http://ichart.finance.yahoo.com/table.csv?s=self".toURL()
        def data = url.readLines()[1].split(",")
        Double.parseDouble(data[-1])
    }

}
