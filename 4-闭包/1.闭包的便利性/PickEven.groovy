/**
 * 通过传统方式与 Groovy 的方式对比体现闭包的便利性。
 *
 * 以下是 Groovy 闭包便利性的体现：
 *
 *      pickEven(n, block) 是一个高阶函数，即函数为参数，或返回一个函数作为结果的函数。
 *      - 在这个方法中，不同于传统方式那样。它将值发送给了一个代码块，在 Groovy 中称这种匿名代码块为「闭包」
 *      - 变量 block 保存了一个指向闭包的引用，可以像传递对象一样传递闭包。
 *
 * @author Harry Zhang
 * @since 2019/12/20 8:20 AM
 */
static def pickEven(n, block) {
    for (int i = 2; i <= n; i += 2)
        block(i) // 将值 i 传递给闭包
}

/*
语法：
1. 如果只向闭包传递一个参数，那这个参数可以被 it 这个特殊变量所指代。这里的 it 就是 pickEven 的循环变量 i，当然还可以自己取一个名字如：
        pickEven(10, { number -> println(number) })
2. 闭包传递的数量和顺序没有限制，如果闭包是最后一个实参，还可以这样写：
        pickEven(10) { println(it) }
   可以将闭包附着在方法调用上。
 */
pickEven(10, { println(it) })
pickEven(10, { number -> println(number) })

println("----------------------------------")
// 有了闭包，关于偶数计算的问题可以这样写
def total = 0
pickEven(10, { total += it })
println "Sum of even numbers from 1 to 10 is: ${total}"

// 有了闭包，代码的复用性变高了。求积只需要更改闭包的代码
def prod = 0
pickEven(10) { number -> prod *= number }
println "Product of even numbers from 1 to 10 is: ${prod}"

// 求偶数值平方的集合
def sqr = []
pickEven(10, { sqr << it**2 })
println "Squared of even numbers from 1 to 10 is: ${sqr}"

