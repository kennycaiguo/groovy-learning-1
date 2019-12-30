/*
@Newify 通过方法创建构造器
使用 @Newify 注解，可以不使用 new 关键字，new 变成了类的一个方法。
 */

@Newify(value = [Person, CreditCard])
def fluentCreate() {
    println Person.new(firstName: "John", lastName: "Doe", age: 20)
    println Person(firstName: "John", lastName: "Doe", age: 20)
    println CreditCard("123-456-789", 22000)
}

fluentCreate()
