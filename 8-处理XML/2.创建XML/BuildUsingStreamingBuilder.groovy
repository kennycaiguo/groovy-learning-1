import groovy.xml.StreamingMarkupBuilder

/**
 * 创建 XML
 *
 * 使用 MarkupBuilder 或 StreamingMarkupBuilder 从任意源创建 xml 格式的数据输出。
 * 其提供了一些便捷方法使得创建 xml 变得更加容易。
 *
 * MarkupBuilder 会将生成的 xml 输出到标准输出（System.out）
 * StreamingMarkupBuilder 会将生成的 xml 返回为一个 GString
 *
 * @author Harry Zhang 
 * @since 2020/1/8 10:44 AM
 */

langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'MacCartthy']

def xmlDocument = new StreamingMarkupBuilder().bind() {
    mkp.xmlDeclaration()
    mkp.declareNamespace('computer': 'Computer')

    // 指定根标签 languages
    languages {
        // 给根标签添加的注释
        comment << "Created using StreamingMarkupBuilder"
        langs.each { key, value ->
            /*
             * 给根标签循环添加标签 <Computer:language>，传入一个 map，key 是属性，value 是属性值。
             */
            computer.language([name: key]) {
                author(value)
            }

            /*
             * 测试：添加多级标签。只需要给当前标签提供一个闭包，以下代码会输出：
             *
             * <computer:tag1 testproperty1='property-value' testproperty2='property-value'>
             *     <computer:tag2>
             *         <tag22>value</tag22>
             *     </computer:tag2>
             * </computer:tag1>
             */
            computer.tag1(testproperty1: 'property-value', testproperty2: 'property-value') {
                computer.tag2() {
                    tag22('value')
                }
            }
        }
    }
}
println xmlDocument

def path = this.getClass().getResource(".").getPath()
new File("${path}create-namespace-xml.xml").withWriter() { writer ->
    writer << xmlDocument
}
