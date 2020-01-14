/**
 * 定义一个接收参数的 Groovy 脚本
 *
 * @author Harry Zhang 
 * @since 2020/1/14 8:40 AM
 */

// 该脚本接收一个变量 name
println "Hello ${name}"
// 这里的设置影响调用方的 name 变量。
name = "Harry"
