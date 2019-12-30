/**
 * Groovy 中实现递归，可以将递归代码写在闭包中，通过调用闭包上的特殊方法 trampoline() 即可实现递归。
 * 之所以叫做「尾递归」是因为方法最后的表达式要么是结束递归，要么是调用自身。
 *
 *
 * @author Harry Zhang
 * @since 2019/12/22 11:46 AM
 */
// 定义闭包的引用
def factorial
// number 是要计算的阶乘，theFactorial 保存的是上一步计算后的结果。
factorial = { int number, BigInteger theFactorial ->
    number == 1 ? theFactorial :
            // 调用闭包的 trampoline 方法，传递参数就是该方法的参数。trampoline 会实现递归调用该闭包。
            factorial.trampoline(number - 1, number * theFactorial)
}.trampoline()

println("Factorial of 5 is ${factorial(5, 1)}")
println("Factorial of bits in the result is ${factorial(5000, 1).bitCount()}")
