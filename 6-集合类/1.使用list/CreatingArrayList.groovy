/**
 * List 的基本使用
 *
 * @author Harry Zhang 
 * @since 2019/12/31 11:41 AM
 */

/**
 * 案例1：不必使用 new 关键字创建 list
 */
list = [1, 3, 4, 1, 8, 9, 2, 6]
println list
println list.getClass().name

/**
 * 案例2：使用 [] 操作符获取集合中的某个元素
 */
println list[0]
println list[list.size() - 1]

/**
 * 案例3：使用 [] 操作符获取集合中的某个元素，从右边遍历。
 */
println list[-1] // 获取到的是最后一个元素，即：6
println list[-2]

/**
 * 案例4：使用 Range 对象获取集合中范围的值，并且可以使用负索引。
 */
println list[2..5]
println list[-6..-3]


/**
 * 案例5：使用 Range 对象，Groovy 会返回一个指向原来内容的新实例引用。所以对新实例的修改会应用到旧实例上（2.5.8 版本中有所变化，旧引用指向的内容不会变化）。
 */
sublist = list[2..5]
println sublist.dump()
sublist[0] = 55
println "After sublist[0] = 55, list = $list"
println "After sublist[0] = 55, sublist = $sublist"

