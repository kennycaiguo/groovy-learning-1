/**
 * 普通递归
 *
 * 这种递归在数量小的情况下效率很高，但是递归基数太大时会严重拖慢速度甚至导致 StackOverflow。
 *
 * @author Harry Zhang 
 * @since 2019/12/22 11:43 AM
 */
def factorial(BigInteger number) {
    if (number == 1) 1
    else number * factorial(number - 1)
}

println "Factorial of 5 is ${factorial(5)}"
//println "Factorial of 5 is ${factorial(50000)}" 基数太大
