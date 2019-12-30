package version_groovy

import version_java.Employee
import version_java.Executive

/**
 * @author Harry Zhang 
 * @since 2019/12/19 8:57 AM
 */
static void giveRaise(Employee employee){
    // 注意：传入的类型是 BigDecimal，而不是 Number。
    employee.raise(new BigDecimal(10000.00))
}

// 毫无疑问，执行的是 Employee 类的 raise(Number amount)
giveRaise(new Employee())
// 调用的是 Executive 的 raise(Number amount) 还是 raise(BigDecimal amount) ？？
giveRaise(new Executive())


/*
输出：
Employee got raise
Executive got outlandish raise（疑问）

为什么 Executive 调用的是自己的重写方法？因为 Groovy 的方法分派基于 "目标加参数"，这也称之为「多方法」
虽然 Employee 和 Executive 都是 Employee 类型，但是 Executive 中有更符合的参数 raise(BigDecimal amount)
 */
