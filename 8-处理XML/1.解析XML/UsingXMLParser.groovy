/**
 * XMLParser 和 DOMCategory 的区别在于不需要使用 use 块。
 * XMLParser 向元素添加了便捷的迭代器，所以可以使用诸如 each() collect() 和 find() 方法轻松实现导航。
 * 但是 XMLParser 没有保留 XML infoSet，并且会忽略注释。
 *
 * @author Harry Zhang 
 * @since 2020/1/8 8:43 AM
 */

def languages = new XmlParser().parse("languages.xml")
println "Languages and authors"

// 自动获取根元素下的元素集合
languages.each {
    println "${it.@name} authored by ${it.author[0].text()}"
}

def languagesByAuthor = { authorName ->
    languages.findAll() { it.author[0].text() == authorName }
            .collect() { it.@name }.join(', ')
}

println "Languages By Wirth:" + languagesByAuthor('Wirth')
