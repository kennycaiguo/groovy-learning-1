/**
 * 间接访问方法
 *
 * 如果以 String 形式接收到方法名，想用调用该方法，使用反射需要这样实现 —— 首先从实例获取 Class 元对象，然后调用 getMethod() 得到 Method 实例，
 * 最后在实例上调用 invoke() 方法，并且还需要注意随时可能会出现的异常。
 *
 * 在 Groovy 中只需要简单的调用 invokeMethod() 方法。Groovy 中所有的对象都支持该方法。
 *
 * @author Harry Zhang 
 * @since 2020/1/5 4:28 PM
 */
class Person2 {
    def walk() { println "Walking..." }

    def walk(int miles) { println "Walking $miles miles..." }

    def walk(int miles, String where) { println "Walking $miles $where ..." }
}

peter = new Person2()
peter.invokeMethod("walk", null)
peter.invokeMethod("walk", 10)
peter.invokeMethod("walk", [2, 'uphill'] as Object[])
