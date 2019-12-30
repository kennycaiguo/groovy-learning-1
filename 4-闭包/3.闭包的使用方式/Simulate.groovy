/**
 * @author Harry Zhang 
 * @since 2019/12/21 10:27 AM
 */
class Equipment {
    // 定义了一个成员变量，你不知道它具体是什么。可能是任何类型的变量，也可能是一个闭包引用。
    def calculator

    Equipment(cal) {
        calculator = cal
    }

    def simulate() {
        println("Running simulation")
        // 加了 (), 想把变量当作闭包使用，闭包实锤了。
        calculator()
    }
}

// 传递闭包给构造器，成员变量变成了一个闭包。
def eq1 = new Equipment({ println "Calculator 1" });
// 如果闭包在多处被复用，可以把闭包保存到一个变量中。
def aCalculator = { println "Calculator 2" }
def eq2 = new Equipment(aCalculator);
def eq3 = new Equipment(aCalculator);

eq1.simulate()
eq2.simulate()
eq3.simulate()

/*
 疑问，我如果传递一个普通变量然后调用 simulate() 呢？abc() ?
 groovy.lang.MissingMethodException: No signature of method: java.lang.String.call() is applicable for argument types: () values: []
 */
// new Equipment("abc").simulate()
