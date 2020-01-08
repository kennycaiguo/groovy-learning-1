/**
 * 使用 XMLlSlurper【推荐优先使用】
 *
 * 惰性求值，节约内存，对于大文档处理开销较低。
 * 可以访问不同命名空间的相同标签。
 *
 * @author Harry Zhang 
 * @since 2020/1/8 8:55 AM
 */

/**
 * 案例1：XmlSlurper 的使用与 XmlParser 基本类似。
 * 与 XmlParser 不同是，languages 是根元素，需要手动获取子元素集合。
 */
languages = new XmlSlurper().parse("languages.xml")
println "Languages and authors"
languages.language.each {
    println "${it.@name} authored by ${it.author[0].text()}"
}
def languagesByAuthor = { authorName ->
    languages.language.findAll() { it.author[0].text() == authorName }
            .collect() { it.@name }.join(', ')
}
println "Languages By Wirth:" + languagesByAuthor('Wirth')


/**
 * 案例2：XmlSlurper 可以访问命名空间的标签，要使用这个特性。通过 declareNamespace 指定一个代码中使用的 key 与 xml 中命名空间的映射。
 *
 * 访问指定命名空间的标签语法： key:标签名
 */
println "-----------------------------------------------"
languages = new XmlSlurper().parse("languages-namespace.xml").declareNamespace(human: 'Natural')
print "Computer Languages: "
println languages.language.collect { it.@name }.join(', ')

print "Natual Languages: "
println languages.'human:language'.collect { it.@name }.join(', ')
