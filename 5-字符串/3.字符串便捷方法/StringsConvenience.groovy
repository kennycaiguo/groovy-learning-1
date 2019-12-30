/**
 * 字符串便捷方法
 *
 * @author Harry Zhang 
 * @since 2019/12/30 9:33 AM
 */

// 案例1：使用拓展在 String 类上的方法 execute()，其会产生一个 Process 对象用于执行系统命令。
gitCommand = "git --help".execute().text
println gitCommand

/**
 *  案例2：使用 String 类的 "-=" 操作符，这是由于 Groovy 在 String 类上添加的 minus() 方法实现的。Groovy 还向 String 添加了其他方法：
 *  plus() -> + / multiply() -> * / next() -> ++ / replaceAll() / tokenize() 等。
 */
str = "It's rainy day in Seattle"
println str
str -= "rainy"
println str

/**
 * 案例3：在 String 上遍历（d 到 m 之间遍历）
 */
for (str in 'held'..'helm'){
    print "${str} "
}
