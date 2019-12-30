package example_groovy

/**
 * 在 Groovy 中，实现协程没有 java 那样这么麻烦。
 * 利用闭包，轻松实现了 Java 中依赖多线程才能解决的问题。
 *
 * @author Harry Zhang 
 * @since 2019/12/21 9:44 PM
 */
def product(closure) {
    while (true) {
        println "生产者：买了 10 个苹果。"
        closure(10)
    }
}

product { apple ->
    1.upto(apple) {
        println "消费者：吃了[1]个苹果，还剩[${--apple}]个苹果。"
        sleep(500)
    }
    println "消费者：没苹果了～～～"
}
