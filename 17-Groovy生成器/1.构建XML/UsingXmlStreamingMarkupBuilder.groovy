import groovy.xml.StreamingMarkupBuilder

/**
 * 如果生成的文档比较大（1000M字节），推荐使用 StreamingMarkupBuilder，它的内存占用会好很多，并且可以添加命名空间之类的。
 *
 * @author Harry Zhang
 * @since 2020/1/28 11:31
 */

langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']

def builder = new StreamingMarkupBuilder().bind() {
    // mkp 是 StreamingMarkupBuilder 的属性。使用其定义文档和命令空间。
    mkp.xmlDeclaration()
    mkp.declareNamespace(computer: 'Computer')

    languages {
        comment << 'Created Using StreamingMarkupBuilder'
        langs.each { key, value ->
            computer.language(name: key) {
                author(value)
            }
        }
    }
}
println builder

def path = this.getClass().getResource(".").getPath()
new File(path + "languages.xml").withWriter { // 使用 withWriter 会自动刷新关闭流。
    it << builder.toString()
}