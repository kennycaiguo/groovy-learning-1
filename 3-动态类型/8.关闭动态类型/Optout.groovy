import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

/**
 * 案例4：静态类型检查时，可以指定 TypeCheckingMode.SKIP 属性来跳过某个方法的静态检查。
 *
 * @author Harry Zhang 
 * @since 2019/12/19 10:08 PM
 */
@TypeChecked
class Sample {
    def method1() {

    }

    // 除了这个类不执行静态类型检查，该类的所有方法都将进行静态类型检查。
    @TypeChecked(TypeCheckingMode.SKIP)
    def method2(String str) {
        str.shout()
    }
}

