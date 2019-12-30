
// 1）return 语句是可选的；

// 2）分号分割语句是可选的；

// 3）方法和类默认是 public 的；

// 4）?. 操作符只有对象引用不为空时才会分派调用；

// 5）可以使用具名参数初始化 JavaBean？？？

// 6）不强制捕获不关心的异常，这些异常会自动传递到调用者

// 7）静态方法可以使用 this 来引用 Class 对象，如：下面的例子中，learn 方法返回的是 Class 对象，所以可以使用链式调用：

class Wizard {
    def static learn(trick, action){
        // ...
        this
    }
}

Wizard.learn('java', {})
    .learn('php', {})
    .learn('python', {})
