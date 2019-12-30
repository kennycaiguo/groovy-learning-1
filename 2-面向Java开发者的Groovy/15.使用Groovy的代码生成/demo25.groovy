// @Signleton

@Singleton(lazy = true, strict = false)
class TheUnique {
    private TheUnique() { println('Instance created') }

    def static hello() { println('hello') }
}

println("Access TheUnique")
// 第一次调用 instance 属性时，才会创建对象，该属性会映射到 getInstance() 方法上。
TheUnique.instance.hello()

// 第二次调用，不用初始化了。
TheUnique.instance.hello()


// 注意：使用 @Singleton 注解会使得目标类的构造器成为私有的。但是因为 Groovy 不区分公开还是私有，所以在 Groovy 中仍然可以
// 使用 new 关键字，但是不应该这样做，如下面的做法是错误的，但是代码不会有什么异常。
//println(new TheUnique())
