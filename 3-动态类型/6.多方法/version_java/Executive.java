package version_java;

import java.math.BigDecimal;

/**
 * 实现父类的方法，并重载实现自己的方法。
 * 注意：BigDecimal 是继承自 Number 的。
 *
 * @author Harry Zhang
 * @since 2019/12/19 8:46 AM
 */
public class Executive extends Employee {
    @Override
    public void raise(Number amount) {
        System.out.println("Executive got raise");
    }

    public void raise(BigDecimal amount) {
        System.out.println("Executive got outlandish raise");
    }
}
