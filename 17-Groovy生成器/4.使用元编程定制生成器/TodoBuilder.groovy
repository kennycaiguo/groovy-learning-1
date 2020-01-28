/**
 * 创建一个生成待办事项清单的 DSL，关键点在于充分利用 methodMissing() 和 propertyMissing() 特性。
 *
 * 注意：每次调用闭包时需将闭包的 delegate 设置为当前生成器对象 TodoBuilder，以实现递归调用的效果。
 *
 * @author Harry Zhang
 * @since 2020/1/28 20:29
 */
class TodoBuilder {
    def level = 0
    def result = new StringWriter()

    def build(closure) {
        result << "To-Do:\n"
        closure.delegate = this
        closure()
        println result
    }

    def methodMissing(String name, args) {
        handle(name, args)
    }

    def propertyMissing(String name) {
        Object[] args = []
        handle(name, args)
    }

    def handle(String name, args) {
        level++
        level.times { result << '  ' } // 打印级别空格
        result << placeXifStatusDone(args) // 标记完成的待办为 x
        result << name.replaceAll('_', ' ') // 待办名转换
        result << printParameters(args) // 打印待办参数
        result << '\n'

        if (args.length > 0 && args[-1] instanceof Closure) { // 当前的待办事项是否包含闭包参数，如果包含，递归调用闭包。
            def theClosure = args[-1]
            theClosure.delegate = this // 将闭包 delegate 属性设置为当前生成器对象。
            theClosure()
        }
        level--
    }

    /**
     * 将状态为 done 的待办标记为「x」
     */
    static def placeXifStatusDone(args) {
        (args.length > 0 && args[0] instanceof Map && args[0]['status'] == 'done')
                ? 'x ' : '- '
    }

    /**
     * 返回待办事项参数，忽略 status 属性。
     */
    static def printParameters(args) {
        def values = ' '
        if (args.length > 0 && args[0] instanceof Map) {
            values += '['
            def count = 0
            args[0].each { key, value ->
                if (key == 'status') return
                count++
                values += count > 1 ? ' ' : ''
                values += "${key}: ${value}"
            }
            values += ']'
        }
        values
    }
}
