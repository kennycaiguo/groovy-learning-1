/**
 * TodoBuilder 的升级版，抽取公用代码，创建树结构。
 *
 * BuilderSupport 提供了用于识别节点结构的便捷方法，我们不需要亲自编写处理结构的逻辑，只需要简单的监听调用即可。
 *
 * @author Harry Zhang
 * @since 2020/1/28 21:48
 */
class TodoBuilderWithSupport extends BuilderSupport {
    int level = 0
    def result = new StringWriter()

    @Override
    protected void setParent(Object parent, Object child) {}

    @Override
    protected Object createNode(Object name) {
        if (name == 'build') {
            result << 'To-Do:\n'
            'build-done'
        } else {
            handle(name, [:])
        }
    }

    @Override
    protected Object createNode(Object name, Object value) {
        throw new Exception("Invalid format")
    }

    @Override
    protected Object createNode(Object name, Map attributes, Object value) {
        throw new Exception("Invalid format")
    }

    @Override
    protected Object createNode(Object name, Map attributes) {
        handle(name, attributes)
    }

    def propertyMissing(String name) {
        handle(name, [:])
        level--
    }

    @Override
    protected void nodeCompleted(Object parent, Object node) {
        level--
        if (node == 'build-done') {
            println result
        }
    }

    def handle(name, attributes) {
        level++
        level.times { result << '  ' }
        result << placeXifStatusDone(attributes)
        result << name.replaceAll('_', ' ')
        result << printParameters(attributes)
        result << '\n'
        name
    }

    static def placeXifStatusDone(attributes) {
        attributes['status'] == 'done' ? 'x ' : '- '
    }

    static def printParameters(attributes) {
        def values = ' '
        if (attributes.size() > 0) {
            values += '['
            def count = 0
            attributes.each { key, value ->
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
