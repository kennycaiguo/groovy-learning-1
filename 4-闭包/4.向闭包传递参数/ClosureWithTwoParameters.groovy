/**
 * 如何向闭包传递参数？
 *
 * 前面已经了解过，如果向闭包内传递了一个参数，闭包内使用 it 变量即可。
 * 但如果传递了多个参数呢？这时就需要指定参数了。
 *
 * @author Harry Zhang 
 * @since 2019/12/21 10:41 AM
 */
static def tellFortune(closure) {
    closure(new Date("12/21/2019"), "Your day is filled with ceremony")
}


// 在闭包内分别指定参数名
// 因为 Groovy 支持可选类型，所以可以不指定类型。通常情况下定义一个表现力好的变量名可以不指定类型。但在「元编程」中我们可以使用闭包来覆盖
// 或替换方法，这时类型信息就比较重要了。
tellFortune() { date, fortune ->
    println("Fortune for ${date} is '${fortune}'")
}
