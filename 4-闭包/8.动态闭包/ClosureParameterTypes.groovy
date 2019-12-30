/**
 * 获得闭包参数的类型
 *
 *       闭包引用.parameterTypes
 *
 * 注意：即使一个闭包没有使用任何形参，像 {} 或 {it} 其实会接受一个 Object 类型的的参数（名字默认为 it），如果调用者没有向闭包传递实参，
 * 则第一个形参为 null。如果需要指定闭包没有参数，使用 {->}。
 *
 * @author Harry Zhang
 * @since 2019/12/22 10:41 AM
 */
def examine(closure) {
    println "$closure.maximumNumberOfParameters paramater(s) given: "
    for (aParameter in closure.parameterTypes) {
        println aParameter.name
    }
    println "--"
}

// 1 个参数，类型：Object
examine() {}
// 1 个参数，类型：Object
examine() { it }
// 0 个参数
examine() { -> }
// 1 个参数，类型：Object
examine() { val1 -> }
// 1 个参数，类型：Date
examine() { Date val1 -> }
// 2 个参数，类型：Date，Object
examine() { Date val1, val2 -> }
// 2 个参数，类型：Date，String
examine() { Date val1, String val2 -> }
