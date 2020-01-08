/**
 * 创建 XML
 *
 * 用纯 Groovy 的方式创建 xml，利用了 GString 的强大能力。
 *
 * @author Harry Zhang 
 * @since 2020/1/8 10:29 AM
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'MacCartthy']

def content = ''
langs.each { language, author ->
    def fragment = """
        <language name="${language}">
            <author>${author}</author>
        </language>
    """
    content += fragment
}
xml = "<languages>$content</languages>"
println xml

// 获得了 xml 内容，接下来就是创建文件了。
def path = this.getClass().getResource(".").getPath()
new File("${path}create-xml.xml").withWriter() { writer ->
    writer << xml
}
