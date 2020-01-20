/**
 * Groovy 的 Mixin 是一种运行时能力，可以将多个类的实现引入进来。如果将一个类混入到另一个类中，Groovy 会在内存中将这些类的实例链接起来。
 * 当调用一个方法时 Groovy 会将调用路由到混入的类中，如果该方法存在这个类中则处理。否则由主类处理。
 * 可以将多个类混入到一个类中，最后加入的 Mixin 优先级最高。
 *
 * @author Harry Zhang 
 * @since 2020/1/20 11:19 AM
 */
class Friend {
    def listen() {
        "$name is listening as a friend.."
    }
}

/**
 * 案例1：使用 Mixin 注解引入参数中的类的方法，Friend 中的方法会被混入到 Person3 中。
 *
 * 限制：使用注解的方式限制了混入类的作者需要是自己才行，如果向混入方法到一个其他类，就不能用这种方式了。
 */
@Mixin(value = Friend)
class Person3 {
    String firstName
    String lastName

    String getName() {
        "$firstName $lastName"
    }
}

def john = new Person3(firstName: "John", lastName: "Smith")
println john.listen()


/**
 * 案例2：向已存在的类混入方法。使用 mixin() 方法，传入需要被混入的类
 */
class Dog {
    String name
}

Dog.mixin(Friend)

def buddy = new Dog(name: "Buddy")
println buddy.listen()


/**
 * 案例3：向具体实例混入方法
 */
class Cat {
    String name
}

def socks = new Cat(name: "Socks")
socks.metaClass.mixin(Friend)
println socks.listen()
