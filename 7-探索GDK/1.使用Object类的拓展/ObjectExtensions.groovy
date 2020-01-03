/**
 * Object 类上的拓展
 *
 * 在第 6 章学习了 Groovy 添加在 Collection 上面的 each() collect() find() findAll() any() every() 方法。
 * 其实 Groovy 不仅在 Collection 添加了这些方法，任何对象上都能使用他们。
 *
 * 这个文件主要熟悉 Groovy 在 Object 上拓展的常用方法。
 *
 * @author Harry Zhang 
 * @since 2020/1/3 10:31 PM
 */

/**
 * 案例1：dump() 和 inspect()
 *
 * dump() 会打印目标实例的类型、值、散列码
 * inspect() 会返回创建一个对象需要提供什么。如果类没有实现该方法，则会返回调用 toString() 时的内容。
 */
str = 'Hello'
println str
println str.dump()
println str.inspect()
