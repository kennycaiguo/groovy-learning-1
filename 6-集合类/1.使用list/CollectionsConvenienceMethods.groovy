/**
 * 集合类上的其他便捷方法
 *
 * 需求：计算集合中每个元素的字符之和
 *
 * @author Harry Zhang 
 * @since 2020/1/1 3:32 PM
 */

/**
 * 案例1：不用便捷方法一般会这样做
 *
 * 注意：size() 方法是添加到 String 类上的一个方法，用于计算字符数量。
 */
list = ['Programming', 'In', 'Groovy']
count = 0
list.each { count += it.size() }
println count

/**
 * 案例2：使用 Groovy 为 JDK 集合类添加的 collect() 和 sum() 实现需求
 *
 * 分析：1.使用 collect() 为集合上的每一个元素计算并返回，处理完之后会返回一个新的集合。
 *      2.对新集合使用 sum() 计算每个元素之和。
 */
println list.collect({ it.size() }).sum()


/**
 * 案例2：使用 inject() 方法实现需求
 * inject() 会对集合中的每个元素调用闭包，闭包接受两个参数：carryOver 是汇总值，element 是集合中的每一个元素。
 * inject() 把要注入的值当作参数，通过 carryOver 参数把它放在第一次对闭包的调用里，并且会把闭包的结果保存起来注入到后续的闭包调用中。
 */
println list.inject(0) { carryOver, element -> carryOver += element.size() }


/**
 * 案例3：使用 join() 连接集合中元素
 */
println list.join(' ')


/**
 * 案例4：给集合添加子集合
 */
sublist = ['Be', 'Productive']
list[0] = sublist;
println list


/**
 * 案例5：拉平案例4中的子集合到父集合
 */
list = list.flatten()
println list


/**
 * 案例5：在 list 上使用 - （minus()） 操作符
 *
 * 操作符右边的值会从集合中被移除，如果传入了不存在的值则会跳过。
 * 可以传递单个值或一个列表。
 */
println list - ['Productive', 'In']


/**
 * 案例6：使用 reverse() 得到集合的反向之后的副本
 */
println list.reverse()


/**
 * 案例7：使用 * 操作符（展开操作符）在每个元素上执行，而不用遍历。
 * list.size()：获取的是整个集合的大小
 * list*.size()： * 会作用集合上的每一个元素，在元素上执行 size() 后返回一个结果集合。实现效果如 list.each { it.size() } 一样
 */
println list.size()
println list*.size()


/**
 * 案例8：使用展开操作符 *，将集合打散作为参数传递给方法。
 *
 * 因为集合中包含四个元素：[Groovy, In, Productive, Be]，* 操作符将这四个元素值作为参数传递给了 words() 参数。
 * 注意：如果集合数量和方法参数数量不一致会报错。
 */
def words(a, b, c, d) {
    println "$a, $b, $c, $d"
}

words(*list)
