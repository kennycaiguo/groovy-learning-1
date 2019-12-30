import groovy.transform.Canonical
import jdk.nashorn.internal.ir.annotations.Immutable

// @Immutable 标记类不可变，创建不可变值对象。线程安全
// 使用 @Immutable 标记的类，其类中的属性都会标记为 final，并且会额外添加 hashCode(), equals(), toString()。

@Immutable
@Canonical
class CreditCard {
    String cardNumber
    int creditLimit

    CreditCard(String cardNumber, int creditLimit) {
        this.cardNumber = cardNumber
        this.creditLimit = creditLimit
    }
}

println new CreditCard("4000-1111-2222-3333", 1000)
