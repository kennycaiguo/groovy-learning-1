/**
 * find() & findAll()
 * @author Harry Zhang 
 * @since 2020/1/1 3:07 PM
 */

/**
 * 找出元素为 2 的元素（第一个），返回的是对象，不是索引。如果没有找到则返回 null
 */
list = [4, 3, 1, 2, 4, 1, 8, 9, 2, 6]
println list.find() { it == 2 }

/**
 * 查找集合中大于 4 的元素值（第一个）
 */
println list.find() { it > 4 }

/**
 * 查找所有，返回数组
 */
println list.findAll() { it > 4 }

/**
 * 查找返回索引
 */
println list.findIndexOf() { it > 4 }
