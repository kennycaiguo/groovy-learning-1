/**
 * 创建两个 Groovy 的闭包
 *
 * @author Harry Zhang 
 * @since 2020/1/13 10:25 PM
 */
class AGroovyClass {

    def useClosure(closure) {
        println "Groovy: 调用闭包"
        closure()
    }

    def useClosure(int value, closure) {
        println "Groovy: 调用带参数的闭包"
        closure(value)
    }
}
