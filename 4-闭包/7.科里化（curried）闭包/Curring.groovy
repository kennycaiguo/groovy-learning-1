/**
 * 科里化闭包（Curried Closure）
 * 通俗来讲就是：如果对一个闭包的调用需要传递某个固定值的变量，那么这个变量可以被 "省略" 掉，只传递变动的变量给闭包即可。
 *
 * @author Harry Zhang 
 * @since 2019/12/21 10:29 PM
 */
def tellFortunes(closure) {
    Date date = new Date()

    // 不使用 Curried Closure，给闭包传递参数是这样的。date 是一个固定实参，每次都会带过去。
    closure(date, "Your day is filled with ceremony")
    closure(date, "They're features, not bugs")

    /*
     * 使用 Curried Closure，对于 date 这种值比较固定的实参，可以省略掉。
     * Groovy 是通过预先绑定闭包形参来实现的。当对一个闭包调用 curry() 时，就是要求预先绑定某个形参，后续调用闭包时就不需要为这个形参提供实参了。
     */
    postFortune = closure.curry(date) // 绑定形参
    // postFortune 变量已经保存了科里化之后的闭包的引用，它已经预先绑定了 date 值。
    postFortune "Your day is filled with ceremony"
    postFortune "They're features, not bugs"

}

tellFortunes { date, fortune ->
    println "Fortune for ${date} is '${fortune}'"
}

/*
 * 使用 curry() 可以科里化任意多个形参，但这些形参必须是从前面开始的连续若干个。
 * 如果想科里化后面的形参，可以是用 rcurry() 方法。
 * 如果想科里化形参列表中间的形参，可以使用 ncurry() 方法。ncurry 方法需要传递中间参数的索引位置。
 */
