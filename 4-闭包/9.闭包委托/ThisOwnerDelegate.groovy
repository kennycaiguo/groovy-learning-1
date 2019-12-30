/**
 * 闭包的三个属性：this、owner、delegate，用于确定哪个对象处理该闭包内方法的调用。
 * 一般而言，delegate 会设置为 owner。
 *
 * this 指向的是闭包所绑定的对象，闭包内的所有变量和方法都会绑定到 this，它负责处理任何方法调用以及属性和变量的访问。
 * 如果 this 无法处理，会转向 owner，最后才是 delegate。
 *
 * @author Harry Zhang 
 * @since 2019/12/22 11:06 AM
 */
def examineClosure(closure) {
    closure()
}

examineClosure() {
    println "In First Closure:"
    println "class is: " + getClass().name
    println "this is: " + this + ", super: " + this.getClass().superclass.name
    println "owner is: " + owner + ", super: " + owner.getClass().superclass.name
    println "delegate is: " + delegate + ", super: " + delegate.getClass().superclass.name

    // 闭包内再调用了闭包
    examineClosure() {
        println "--"
        println "In Closure within the First Closure:"
        println "class is: " + getClass().name
        println "this is: " + this + ", super: " + this.getClass().superclass.name
        // 因为第二个闭包是在第一个闭包内定义的，所以第一个闭包成为了第二个闭包的 owner。
        println "owner is: " + owner + ", super: " + owner.getClass().superclass.name
        // delegate 设置成了 owner，输出一样。
        println "delegate is: " + delegate + ", super: " + delegate.getClass().superclass.name
    }
}
