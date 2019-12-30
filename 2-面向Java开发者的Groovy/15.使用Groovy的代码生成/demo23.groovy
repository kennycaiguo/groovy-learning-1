
// @Lazy 对象创建延迟到调用时初始化
// 使用 @Lazy 标记的对象不仅会延迟创建，还会标记为 volatile，保证创建期间是线程安全的。
// @Lazy 提供了轻松实现线程安全的虚拟代理模式

class Heavy {
    def size = 10

    Heavy() { println "Creating Heavy with $size" }
}

class AsNeeded {
    def value

    // 延迟创建
    @Lazy
    Heavy heavy1 = new Heavy()
    // 创建逻辑放在闭包中
    @Lazy
    Heavy heavy2 = { new Heavy(size: value) }()

    AsNeeded() { println "created AsNeeded" }
}


// created AsNeeded
def asNeeded = new AsNeeded(value: 1000)

// Creating Heavy with 10
// 10
println asNeeded.heavy1.size

// 10
println asNeeded.heavy1.size

// Creating Heavy with 10
// 1000
println asNeeded.heavy2.size
