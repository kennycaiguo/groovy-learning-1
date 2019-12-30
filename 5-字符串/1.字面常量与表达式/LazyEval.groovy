/**
 * 一）GString 的惰性求值问题:
 * 在一个 GString 中改变表达式的引用对象的属性不会有什么问题，但是如果改变引用会有问题。
 */

/**
 * 案例1： 下面这个例子中，改变的是 text 这个引用的对象的属性值（改变的是 StringBuilder 而不是 what 本身），所以不会有问题。
 */
what = new StringBuilder('fence')
text = "The cow jumped over the ${what}"
println text
what.replace(0, 5, 'moon')
println text
println "-------------------------"

/**
 * 如果修改的 what 指向的对象呢？通过另外一个例子来体现：
 * 案例2： 在这个例子中，我们想在 stocks 这个 Map 中循环的改变 price 和 price 所指向的值来达到打印不同公司的股价，但是实际输出和预期有些不同。
 * 我们期望的输出是：  Today Google stock closed at 684.71
 *                  Today Apple stock closed at 663.01
 *                  Today Microsoft stock closed at 30.95
 *
 * 实际代码的输出是：  Today Google stock closed at 684.71
 *                  Today Google stock closed at 684.71
 *                  Today Google stock closed at 684.71
 *
 * 为什么会有这种情况呢？
 *      那是因为在 #1 处定义了 GString 对象 quote，其包含了表达式 price 和 company 分别指向了两个不可变的对象。
 *      而在后续的 each 中，虽然改变了 price 和 company 的指向地址，但是 quote 对象中的 price 和 company 指向的仍然是修改之前的引用地址。
 *      因为遍历中没有修改 quote 的值，自然两个变量的引用不会改变（在 Java 中亦是如此，@see Demo.java）。
 */
price = 684.71
company = 'Google'
quote = "Today $company stock closed at $price" // #1
println quote
stocks = [Apple: 663.01, Microsoft: 30.95]
stocks.each { key, value ->
    company = key
    println("修改后的 company：$company") // #2 company 改变了引用，所以值不一样。
    price = value
    println("修改后的 value：$value") // #2 value 也改变了引用，所以值不一样。
    // quote = "Today $company stock closed at $price" 如果给quote 重新赋值修改后的引用，结果就会正确。
    println quote // #3
}

/**
 * 对于案例 2 的中这种情况，如果想要动态改变 GString 对象 quote 的值，需要重新给 quote 赋值：
 * stocks.each { key, value ->
 *     company = key
 *     price = value
 *     quote = "Today $company stock closed at $price" // 重新给 quote 赋值
 *     println quote
 *}* 但这就不是动态改变了。
 *
 * 在 Groovy 中，可以指定变量 company 和 price 为闭包来实现这个需求。
 *
 * 前提知识点：当前一个 GString 实例求值时，如果其中包含一个变量，该变量的值会被打印到一个 Writer 中，通常是 StringWriter。
 * 如果变量指向的是一个闭包，而非变量，该闭包会被调用。如果闭包接受一个参数，GString 会把 Writer 传给他。如果闭包不接受参数，
 * 那么 GString 会简单的调用闭包，并打印我们想返回给 Writer 的结果。
 */
println "-------------------------"
companyClosure = { it.write("$company") }
priceClosure = { it.write("$price") }
quote = "Today $companyClosure stock closed at $priceClosure"
stocks.each { key, value ->
    company = key
    price = value
    println quote
}

// 如果闭包不包含参数，可以去掉 it 参数，GString 会使用我们返回的内容
println "-------------------------"
companyClosure = { -> company }
priceClosure = { -> price }
quote = "Today $companyClosure stock closed at $priceClosure"
stocks.each { key, value ->
    company = key
    price = value
    println quote
}
