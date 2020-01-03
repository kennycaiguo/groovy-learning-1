/**
 * 神奇的 with() 方法。
 *
 * with() 支持创建一个「上下文」，在 with 作用域内调用的任何方法，都会被定向到该上下文对象。（就是说：with作用域里面的方法，都是通过调用 with 的对象执行的）
 * 难以理解？先看一段没有使用 with 特性的代码
 *
 * @author Harry Zhang 
 * @since 2020/1/3 11:19 PM
 */

list = [1, 2]
list.add(3)
list.add(4)
println list.size()
println list.contains(2)

/*
 * 上述代码是一段在 Java 经常这样写的典型。在这段代码中，我们一直在调用 list 上的方法，这里没有隐含的上下文，我们在重复的使用 list 这个引用。
 * 在 Groovy 中，我们可以简化成如下：
 */
lst = [1, 2]
lst.with {
    add(3)
    add(4)
    println size()
    println contains(2)
}

/*
 * 上述代码用书面化的语言说：这种代码「噪音」很小。我们避免了重复（冗余）的使用一个引用。那么 Groovy 是怎么做到的呢？
 * 魔力就在于 with 方法所接收的闭包里面（闭包的 delegate 属性）。
 *
 * 输出：
 *      this is: With@48075da3
 *      owner is: With@48075da3
 *      delegate is: [1, 2, 3, 4]
 *      delegate class is: java.util.ArrayList
 *
 * 观察代码输出可以发现，闭包的 this、owner 属性是这个脚本对象没什么问题。
 * 但闭包的 delegate 属性却是 lst 引用所指向的 ArrayList 对象？
 *
 * 原理：当调用 with() 时，Groovy 会特殊的将闭包的 delegate 属性设置到调用 with 的对象上。
 * 流程：
 *      1. 闭包内调用 add() size() contains() 方法时，首先会去通过闭包的 this 属性找方法，没有找到，转发给 owner 处理；
 *      2. owner 也没有这些方法，交由 delegate 处理；
 *      3. delegate 正好被指向到了 lst 引用的地址，存在这些方法，所以就通过 delegate 把方法路由给 lst 执行了。
 *
 * —— "with 特性在创建领域特定语言（DSL）时十分有用"
 */
lst.with {
    println "this is: ${this}"
    println "owner is: ${owner}"
    println "delegate is: ${delegate}"
    println "delegate class is: ${delegate.getClass().name}"
}
