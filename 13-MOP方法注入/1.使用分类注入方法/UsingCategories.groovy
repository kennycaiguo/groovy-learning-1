/**
 * Groovy 的分类提供了一种可控的方法注入方式 —— 方法注入的作用可以限定在一个代码块中。
 * Category 可以修改类的 MetaClass 对象，并且这种修改仅在代码块的作用域和执行线程内有效，当代码退出一切会恢复原状。
 *
 * @author Harry Zhang 
 * @since 2020/1/19 4:52 PM
 */

/**
 * 案例1：想在 String 和 StringBuilder 类上注入一个方法 toSSN()。
 */
class StringUtil {
    /**
     * @param self 要注入的该方法的对象，如果想限定参数为 String 类型，那么只有 String 类能够调用 toSSN()；
     * StringBuilder 使用的话需要再添加一个参数类型为 StringBuilder 的 toSSN() 方法了。
     *
     * self 未指定类型默认为 Object 类型，所有对象都能使用。
     */
    def static toSSN(self) {
        if (self.size() == 9) {
            "${self[0..2]}-${self[3..4]}-${self[5..8]}"
        }
    }
}

// 闭包代码块，要注入的方法只会在该代码块中生效。在代码块中调用 String 和 StringBuilder 上的 toSSN() 时，方法会被路由到 StringUtil 上的静态方法。
use(StringUtil) {
    println "123456789".toSSN()
    println new StringBuilder("987654321").toSSN()
}

// 代码块之外调用会报错
try {
    println "123456789".toSSN()
} catch (e) {
    println e.getMessage()
}


/**
 * 案例2：前面的实现方式中，必须将需要注入的方法标记为 static 的，Groovy 提供了一个注解，用于将实例方法转为静态方法。
 *
 * toSSN 方法会转变成：public static toSSN(String self)
 * 注意：value 属性指定要注入的方法的对象类型，方法会被限制为只能 String 能调用注入的 toSSN()。
 */
@Category(value = String)
class StringUtilAnnotated {
    def toSSN() {
        if (size() == 9) {
            "${this[0..2]}-${this[3..4]}-${this[5..8]}"
        }
    }
}

use(StringUtilAnnotated) {
    println "123456789".toSSN()
}


/**
 * 案例3：注入方法的类型可以是对象或闭包，如：
 */
class FindUtil {
    def static extractOnly(String self, closure) {
        def result = ''
        self.each {
            if (closure(it)) {
                result += it
            }
        }
        result
    }
}

use(FindUtil) {
    println "121254123".extractOnly({ it == '4' || it == '5' })
}


/**
 * 案例4：在 use 中使用多个分类
 *
 * 注意：多个分类有相同的方法怎么办？Groovy 会调用最后一个分类的方法。
 */
use(StringUtil, FindUtil) {
    str = "123487651"
    println str.toSSN()
    println str.extractOnly() { it == '8' || it == '1' }
}


/**
 * 案例5：使用分类实现方法拦截。
 * 在 「12-使用MOP拦截方法」中，提到了还可以使用分类来拦截方法。
 *
 * 优点：1. 使用分类拦截方法可以做到侵入性最小，其效果仅包含在 use 代码块中，一旦离开了代码块注入的方法就失效了。
 *      2. 使用方法注入的方式拦截类，感觉就是扩充了所接收对象的类型。当离开方法时，这个对象的类也不会受到影响。
 *
 * 缺点：1. 这种方式不像 12 章的那两种拦截方式（Interceptable、metaClass）那么优雅的可以拦截类上的所有方法。使用分类只能拦截单个方法。
 *      2. 只能在单个代码块、当前线程执行，多次进入退出 use 代码块有开销的。每次进入时 Groovy 会要检查静态方法，并将其加入到新的作用域。退出方法时还要清除该作用域。
 *
 * 适用场景：调用不频繁，想要这种可控的方法注入所提供的隔离性。
 */
class Helper {
    def static toString(String self) {
        def method = self.metaClass.getMethods().find { it.name == 'toString' }
        def result = method.invoke(self, null)
        "!$result!"
    }
}

use(Helper) {
    println "hello".toString()
}
