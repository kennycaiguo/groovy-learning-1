import groovy.xml.MarkupBuilder

/**
 * 使用 MarkupBuilder 生成 XML。当在生成器上调用方法或属性时，其会根据调用的上下文，假定我们要创建的是xml中的元素名或属性名。
 * 如果生成的文档比较大（1000M字节），推荐使用 StreamingMarkupBuilder，它的内存占用会好很多，并且可以添加命名空间之类的。
 *
 * @author Harry Zhang
 * @since 2020/1/27 12:10
 */

def writer = new StringWriter()
// def builder = new MarkupBuilder() 如果不想把生成的 xml 写入到标准输出中，可以提供一个 Writer，生成的 xml 将会写入到 Writer 中。
def builder = new MarkupBuilder(writer)
// 调用了一个不存在的方法，将其当作根标签。
builder.languages() {
    // 在根标签的上下文中，调用任何不存在的方法都会被当作标签，如果参数是一个 Map，则作为标签的属性值；如果参数是一个普通变量，则当作标签值。
    language(name: 'C++') {
        author('Stroustrup')
    }
    language(name: 'Java') {
        author('Gosling')
    }
    language(name: 'Lisp') {
        author('McCarthy')
    }
}
println writer