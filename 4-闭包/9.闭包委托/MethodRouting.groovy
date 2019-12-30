/**
 * 案例1
 *
 * @author Harry Zhang 
 * @since 2019/12/22 11:22 AM
 */
class Handle {
    def f1() { println "f1 of Handle called ..." }

    def f2() { println "f2 of Handle called ..." }
}

class Example {
    // 这两个方法其实跟闭包没有关系，他们属于 Example，闭包不属于 Example。
    def f1() { println "f1 of Example called ..." }
    def f2() { println "f2 of Example called ..." }

    def foo(closure) {
        // 将闭包内的 delegate 属性指向 Handle 对象。这样当闭包内没有的方法时会路由到 Handler
        closure.delegate = new Handle()
        closure()
    }
}


// 该方法属于闭包的
def f1() { println "f1 of Script called ..." }
// 测试闭包没有 f2 方法 def f2() { println "f2 of Script called ..." }

new Example().foo {
    // 闭包内存在三个属性 this、owner、delegate
    // 闭包有 f1，调用自己 this 的。
    f1()
    // 闭包没有 f2，调用 owner 的，owner 也没有，调用 delegate 的
    f2()
}
