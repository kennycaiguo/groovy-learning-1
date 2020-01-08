import groovy.xml.DOMBuilder
import groovy.xml.dom.DOMCategory

/**
 * Groovy 的分类（见后续MOP知识点）可以在类上定义动态方法，其中有一个分类用于处理文档对象模型，那就是 DOMCategory。
 *
 * 使用 DOMCategory，通过类 GPath（Groovy路径表达式） 在 DOM 结构中导航。
 *
 * 使用 DOMCategory，根元素是 rootElement，可通过 rootElement 获取到所有的子元素。子元素通过 . 即可调用下一个子元素，
 * 元素通过 .'@name' 可以获取属性名为 name 的值。
 *
 * @author Harry Zhang
 * @since 2020/1/7 9:06 PM
 */

// 把文档加载进内存
document = DOMBuilder.parse(new FileReader('languages.xml'))
rootElement = document.documentElement

// 要使用 DOMCategory，需要代码放入到 use 块中
use(DOMCategory) {
    println "Languages and authors"
    // 根据根元素获取所有的 language 标签
    def languages = rootElement.language
    languages.each { language ->
        // language.'@name' 获取属性值；language.author[0].text() 获取第一个 author 标签的文本值。
        println "${language.'@name'} authored by ${language.author[0].text()}"
    }

    // 定义一个闭包，接收一个参数。
    def languagesByAuthor = { authorName ->
        // 在所有的 language 标签中找出 author 标签文本值等于输入参数的集合。
        languages.findAll { it.author[0].text() == authorName }
                // 将其 name 属性值收集并用 , 分隔。
                .collect { it.'@name' }.join(', ')
    }

    println "Languages by Wirth:" + languagesByAuthor('Wirth')
}
