import groovy.transform.TypeChecked

/**
 * 案例3：
 * 与 Java 相比，Groovy 的类型检查有一个优势。如果使用了 instanceOf 检查类型，那么在接下来使用该类型的特定方法或属性时，
 * 不需要执行强制类型转换。
 *
 * @author Harry Zhang 
 * @since 2019/12/19 10:01 PM
 */
@TypeChecked
def use(Object instance) {
    if (instance instanceof String)
        // Object 类没有 length()，这里其实已经做了 （String）instance
        println instance.length()
    else
        println instance
}

use "hello"
use 4
