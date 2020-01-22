/**
 * 通过实现 methodMissing() 可以拦截对不存在方法的调用，通过实现 PropertyMissing() 可以拦截对不存在属性的调用。
 * 在第一次调用时，如果方法不存在就注入到 metaClass 中缓存下来，下次调用就能直接调用，这被称之为："拦截-缓存-调用"模式。
 *
 * @author Harry Zhang 
 * @since 2020/1/20 3:34 PM
 */
class Person5 {
    def work() { "working..." }
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def methodMissing(String name, args) {
        System.out.println("methodMissing called for $name")
        // 如果调用的方法名包含 play 前缀，就去 plays 中找到对应的技能，然后合成一个方法提供给外部调用。
        def methodInList = plays.find { it == name.split('play')[1] }
        if (methodInList) {
            Person5 instance = this
            instance.metaClass."$name" = { arguments ->
                "playing ${name.split("play")[1]}"
            }
            instance."$name"(args)
        } else {
            throw new MissingMethodException(name, Person5.class, args)
        }
    }
}

def jack = new Person5()
println jack.work()

println "> 第一次调用方法不存在，会被 methodMissing() 拦截。"
println jack.playTennis()
println jack.playBasketBall()
println jack.playVolleyBall()

println "> 第一次调用时已注入了方法到实例上，第二次调用不会进入 methodMissing()"
println jack.playTennis()
println jack.playBasketBall()
println jack.playVolleyBall()

println "> 不存在，报错"
try {
    println jack.playPolitics()
} catch (e) {
    println e
}
