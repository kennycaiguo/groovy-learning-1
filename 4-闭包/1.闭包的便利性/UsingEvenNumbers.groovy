/**
 * 通过传统方式与 Groovy 的方式对比体现闭包的便利性。
 *
 * 闭包（Closure）：通俗来讲就是用来替换「匿名内部类」的轻量级、短小、简洁的代码块。
 *      使用闭包可以创建轻量级、复用性高的代码。
 *
 * 以下是传统方式：
 *      在这个案例中，循环的代码是相同的，但是需求不同每次都要重复这些遍历数字的代码。
 *
 * @author Harry Zhang 
 * @since 2019/12/20 8:02 AM
 */

// 需求1：求 1 到某个数 n 之间所有偶数的和
def sum(n) {
    total = 0
    for (int i = 2; i <= n; i += 2) {
        total += i
    }
    total
}

println "Sum of even numbers from 1 to 10 is: ${sum(10)}"

// 需求2：不想求和了，计算 1 到某个数 n 之间所有偶数的积。
def product(n) {
    prod = 0
    for (int i = 2; i <= n; i += 2) {
        prod *= i
    }
    prod
}

println "Product of even numbers from 1 to 10 is: ${product(10)}"

// 需求3：不想求积了，现在想得到由这些偶数值的平方所组成的集合，怎么做？
def sqr(n) {
    squared = []
    for (int i = 2; i <= n; i += 2) {
        squared << i**2
    }
    squared
}

println "Squared of even numbers from 1 to 10 is: ${sqr(10)}"

