/**
 * 在不修改源码的前提下添加 methodMissing 实现，"拦截-缓存-调用"模式。
 *
 * @author Harry Zhang 
 * @since 2020/1/20 9:47 PM
 */
class Person6 {
    def work() { "working..." }
}

// 给类的 metaClass 添加方法
Person6.metaClass.methodMissing = { String name, args ->
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']
    System.out.println("methodMissing called for $name")
    def methodInList = plays.find { it == name.split('play')[1] }
    if (methodInList) {
        Person6.metaClass."$name" = { Object[] vars ->
            "playing ${name.split("play")[1]}"
        }
        "$name"(args)
    } else {
        throw new MissingMethodException(name, Person6.class, args)
    }
}

def jack = new Person6()
println jack.work()
println jack.playTennis()
println jack.playTennis()
try {
    println jack.playPolitics()
} catch (e) {
    println e
}
