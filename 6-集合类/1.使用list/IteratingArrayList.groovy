/**
 * List 的迭代
 * 在「2. 循环的方式」中查看 Groovy 外部迭代的方式。
 * 使用 collect() 可以 each() 的同时返回一个新的结果集合。
 *
 * @author Harry Zhang 
 * @since 2019/12/31 2:31 PM
 */


/**
 * 案例1：
 * 使用 each() 进行内部迭代。该方法仅接受一个闭包参数，对于集合上的每一个元素都会调用我们传递的闭包；
 * 使用 reverseEach() 可以反向迭代；
 * 使用 eachWithIndex() 向闭包内传递两个参数：元素值和索引。
 */
list = [1, 3, 4, 1, 8, 9, 2, 6]
list.each { print "$it " }
println()
list.reverseEach() { print "$it " }
list.eachWithIndex() { int entry, int i -> println "entry: $entry, it: $i" }

/**
 * 案例2：不使用 collect 时，需要获得集合中的每个元素的两倍时需要这样做
 */
println()
doubled = []
list.each() { doubled << it * 2 }
println list
println doubled


/**
 * 案例3：使用 collect 时，返回集合中每个元素处理之后的新集合
 *
 * collect() 和 each() 不同的是，collect() 会为元素中每个元素调用闭包并保存它的返回值，最后返回生成的结果集合。
 */
doubled = list.collect() { it * 2 } // 闭包会给最后一行表达式包含了一个隐式的 return
println doubled
