/**
 *
 * @author Harry Zhang 
 * @since 2020/1/13 10:41 PM
 */
class DynamicGroovyClass {

    /**
     * 一个特殊方法，当要运行的方法不存在时，该方法会被处理。
     */
    def methodMissing(String name, args) {
        println("You called $name with ${args.join(', ')}")
        args.size()
    }

    /**
     * 普通方法
     */
    def method1(Object[] args) {
        println "method1 -> args: ${args.join(', ')}"
        args.size()
    }

}
