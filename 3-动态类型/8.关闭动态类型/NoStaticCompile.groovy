import groovy.transform.CompileStatic

/**
 * 不使用静态编译，使用 @CompileStatic 关闭动态编译。
 *
 * @author Harry Zhang 
 * @since 2019/12/19 10:18 PM
 */
@CompileStatic
def shout1(String str) {
    println str.toUpperCase()
}
