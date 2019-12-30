/**
 * 所谓协程（Coroutine）是指两个函数之间相互调用，调用完之后继续从调用之前的作用域执行逻辑。
 *
 * @author Harry Zhang 
 * @since 2019/12/21 11:26 AM
 */
def iterator(n, closure) {
    // [1, n] 循环
    1.upto(n) {
        println "In iterator with value ${it}"
        closure(it)
    }
}

println "Calling iterator"
total = 0
iterator(4, {
    total += it
    println "In closure total so far is ${total}"
})
println "Done"

/*
每次调用闭包，都会从上一次调用中恢复 total 的值。
 */
