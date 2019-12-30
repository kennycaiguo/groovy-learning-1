// Groovy 在 groocy.transform 包和一些其他包中提供了很多代码生成的注解。

// 如：@Canonical 会去做 toString() 的活，实现简单以逗号分割的字段值。
import groovy.transform.*

// @Canonical(excludes = "lastName, age") // 指定排除字段
@Canonical
class Person {
    String firstName
    String lastName
    int age
}

def sara = new Person(firstName: "Sara", lastName: "Walker", age: 21)
println sara