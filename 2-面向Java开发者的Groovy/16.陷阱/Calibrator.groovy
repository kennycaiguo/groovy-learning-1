/**
 * Groovy 的闭包与匿名内部类的有冲突
 *
 * 什么是闭包？不重要，不熟悉语法更好。Groovy 的闭包语法是这样的：
 *
 * new Calibrator({ // 闭包的代码逻辑 })
 *
 * @author Harry Zhang 
 * @since 2019/12/18 9:42 PM
 */
class Calibrator {
    Calibrator(calculationBlock) {
        println("using...")
        calculationBlock()
    }
}
