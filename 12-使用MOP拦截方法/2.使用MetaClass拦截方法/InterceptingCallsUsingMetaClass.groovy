/**
 * 2. 使用 MetaClass 实现拦截（AOP）
 *
 * 如果想对其他类库或者 Java 类施加拦截，此时就需要指定 metaClass 的 invokeMethod() 了。
 * 目标类没有实现 GroovyInterceptable 会去找其 metaClass 上的拦截器即 invokeMethod 或目标方法（拦截器如果存在就不会去找目标方法）。
 *
 * @author Harry Zhang 
 * @since 2020/1/17 10:14 AM
 */
class Car5 {
    def check() { System.out.println("check called...") }

    def start() { System.out.println("start called...") }

    def drive() { System.out.println("drive called...") }
}

/**
 * 通过闭包添加 metaClass 上的拦截器（invokeMethod），和前者不同的是，需要注意两点：
 *
 * 1. 在闭包内，执行方法的对象是 delegate，而不是 this
 *      this 指的是当前文件 InterceptingCallsUsingMetaClass，作用域：InterceptingCallsUsingMetaClass@21282ed8
 *      delegate 指的是调用闭包的对象，即要拦截其方法的目标对象：Car5@437da279
 *
 * 2. 当通过 metaClass 没有找到目标方法时，我们调用的是 invokeMissingMethod，而不是 invokeMethod，因为当前重写的就是这个方法，不能在里面递归调用了。
 */
Car5.metaClass.invokeMethod = { String name, args ->
    System.out.println("------------------------------")
    System.out.println("Call to [$name()] interceptable...")

    // 运行前校验
    if ('check' != name) {
        System.out.println("running filter...")
        // delegate
        Car5.metaClass.getMetaMethod('check').invoke(delegate, null)
    }

    // 执行目标方法
    def validMethod = Car5.metaClass.getMetaMethod(name, args)
    System.out.println("validMethod existing ? " + (null != validMethod))
    if (null != validMethod) {
        validMethod.invoke(delegate, args)
    } else {
        // Car5.metaClass.invokeMethod(delegate, name, null)
        Car5.metaClass.invokeMissingMethod(delegate, name, args)
    }
}

def car = new Car5()
car.start()
car.drive()
car.check()
try {
    // 1. 类没有实现 GroovyInterceptable;
    // 2. metaClass 和 POGO 上都没有 speed();
    // 3. POGO 没有 speed 这个属性，
    // 4. 不存在 MissingMethod，调用 invokeMethod.
    // 5. invokeMethod 不存在，抛出异常 MissingMethodException。
    car.speed()
} catch (e) {
    println e
}
