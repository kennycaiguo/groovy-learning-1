/**
 * 动态闭包是指通过一系列方法判断闭包：
 * 1. 是否是一个闭包
 * 2. 闭包的参数个数和类型
 *
 * @author Harry Zhang 
 * @since 2019/12/22 10:25 AM
 */
def doSomething(closure) {
    // 判断是否是一个闭包，如果不是做默认的处理。
    if (closure) {
        closure()
    } else {
        println "Using default implementation"
    }
}

doSomething() { println "Use specialized implementation" }
doSomething()

println "-------------------------------------------------"
/**
 * 使用闭包计算销售税额
 *
 * @param amount 金额
 * @param taxComputer 闭包引用
 * @return 无
 */
def completeOrder(amount, taxComputer) {
    tax = 0
    // 判断闭包的参数个数以决定调用不同逻辑
    if (taxComputer.maximumNumberOfParameters == 2) {
        tax = taxComputer(amount, 6.05)
    } else {
        tax = taxComputer(amount)
    }
    println "Sales tax is ${tax}"
}

// 传递了只有一个形参的闭包
completeOrder(100) { amount -> amount * 0.0825 }
// 传递了有两个形参的闭包
completeOrder(100) { amount, rate -> amount * (rate / 100) }
