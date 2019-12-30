import groovy.transform.TypeChecked

/**
 * 案例1：没有编译时检查的 Groovy，没有编译时检查时，代码会在运行时报错。
 *
 * 添加 @TypeChecked 注解后，Groovy 会开启静态类型检查，代码会在编译时检查。
 *
 *
 * @author Harry Zhang
 * @since 2019/12/19 9:34 PM
 */
//@TypeChecked
def shout(String str) {
    println("Printing in uppercase")
    println(str.toUpperCase())
    println("Printing again in uppercase")
    println(str.toUppercase())
}

try {
    shout("hello")
} catch (e) {
    println("Failed...")
}
