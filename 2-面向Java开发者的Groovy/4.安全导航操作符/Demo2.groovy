// 安全导航（save navigation）操作符（?.）
// 使用安全导航操作符可以避免经常检查引用是否为空（null）

def foo(str) {
    // if (str != null) {str.reverse()}
    // ?. 操作符只有在引用不为null时才会调用指定的方法或属性
    str?.reverse()
}

println foo('evil')
// 在空引用上调用 reverse() 并没有抛出空指针异常，而是打印出一个 null。
println foo(null)