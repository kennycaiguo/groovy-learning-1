/**
 * 使用闭包轻松实现策略模式
 *
 * 在这个案例中，totalSelectValues 将方法计算的条件委托闭包来执行。
 *
 * @author Harry Zhang 
 * @since 2019/12/20 8:52 AM
 */
def totalSelectValues(n, closure) {
    total = 0
    for (i in 1..n) {
        if (closure(i))
            total += i
    }
    total
}

print "Total of even numbers from 1 to 10 is: "
println totalSelectValues(10, { it % 2 == 0 })

print "Total of odd numbers from 1 to 10 is: "
println totalSelectValues(10, { it % 2 != 0 })
