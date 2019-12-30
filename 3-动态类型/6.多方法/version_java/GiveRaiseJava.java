package version_java;

import java.math.BigDecimal;

/**
 * 在 Java 中，raise 方法是多态的，这意味在运行时，被调用的方法依赖的并不是目标的引用类型，而是所引用对象的实际类型。
 *
 * @author Harry Zhang
 * @since 2019/12/19 8:48 AM
 */
public class GiveRaiseJava {
    public static void giveRaise(Employee employee) {
        // 传递的类型是 BigDecimal，由于 BigDecimal 是 Number 的实现类。所以会把 BigDecimal 视为 Number。
        employee.raise(new BigDecimal(10000.00));
    }

    public static void main(String[] args) {
        // 调用的是 Employee 的 raise(Number amount)
        giveRaise(new Employee());
        // 调用的是 Executive 的 raise(Number amount)
        giveRaise(new Executive());
    }
}
