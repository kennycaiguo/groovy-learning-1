/**
 * Groovy 中，== 操作符映射到 java 的 equals()，而引用地址的比较需要用 is() 方法
 *
 * 注意：当且仅当被比较的类没有实现 Comparable 接口时，== 操作符才会映射到 equals()
 * 若目标类实现了 Comparable 接口，则会映射到 compareTo() 方法。
 *
 * @author Harry Zhang
 * @since 2019/12/18 9:17 PM
 */
class A {
    boolean equals(other) {
        println "equals called"
        false
    }
}

class B implements Comparable {
    boolean equals(other) {
        println "equals called"
        false
    }

    int compareTo(Object o) {
        println "compareTo called"
        0
    }
}

/*
  结果：equals called
  映射的是 equals()
 */
new A() == new A()

/*
  结果：compareTo called
  映射的是 compareTo()
 */
def b = new B()
def b2 = new B()
b == b2
