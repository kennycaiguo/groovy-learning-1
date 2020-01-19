/**
 * 【使用MOP拦截方法】
 *
 * Groovy 中提供三种方式拦截方法：
 *
 * 1. 让对象拦截：实现 GroovyInterceptable 接口，所有的方法都会被 invokeMethod() 所拦截。
 *    通过 invokeMethod()，可以轻松实现 AOP，在适合的地方路由到目标方法通过：metaClass 的 MetaMethod。
 *    注意：这种方法只适用于被拦截方法是自己创建的类，对于其他非自己的类或者 Java 类，我们无权修改源代码。自然不能够实现拦截的。
 *
 * 2. 让 MetaClass 拦截。
 * 3. 使用分类 Categories 拦截（见 13-MOP方法注入 UsingCategories.groovy,Helper.class）。
 *
 * @author Harry Zhang 
 * @since 2020/1/17 10:14 AM
 */
class Car4 implements GroovyInterceptable {

    def check() { System.out.println("check called...") }

    def start() { System.out.println("start called...") }

    def drive() { System.out.println("drive called...") }

    /**
     * 拦截所有方法，在本案例中，实现了「前置通知」的效果。
     *
     * @param name 目标方法名
     * @param args 目标方法参数
     * @return
     */
    def invokeMethod(String name, args) {
        System.out.println("------------------------------")
        System.out.println("Call to [$name()] interceptable...")

        // 运行前校验
        if ('check' != name) {
            System.out.println("running filter...")
            Car4.metaClass.getMetaMethod('check').invoke(this, null)
        }

        // 执行目标方法
        def validMethod = Car4.metaClass.getMetaMethod(name, args)
        System.out.println("validMethod existing ? " + (null != validMethod))
        if (null != validMethod) {
            validMethod.invoke(this, args) // 如果目标方法存在，调用。
        } else {
            Car4.metaClass.invokeMethod(this, name, null) // 如果目标方法不存在，将方法路由到它的 metaClass 上去。
        }
    }
}

def car = new Car4()
car.start()
car.drive()
car.check()
try {
    car.speed() // 找不到方法，在 46 行抛出异常。
} catch (e) {
    println e
}
