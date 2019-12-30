// @Delegate 委托注解

class Worker {
    def work(){
        println 'className: ' + this.getClass().name
        println 'get work done'
    }
    def analyze(){
        println 'className: ' + this.getClass().name
        println 'analyze...'
    }
    def writeReport(){
        println 'className: ' + this.getClass().name
        println 'get report written'
    }
}

class Expert {
    def analyze(){
        println 'className: ' + this.getClass().name
        println 'expert analyze...'
    }
}

class Manager {
    @Delegate Expert expert = new Expert()
    @Delegate Worker work = new Worker()
}

/*
编译时 Groovy 会检查 Manager 类，如果其中没有被委托类中的方法，会把这些方法从被委托类中引进来。
1. 因此首先会引进 Expert 类中的 analyze 方法。
2. 再从 Worker 类中引进 work 和 writeReport 方法。由于 Expert 类中的 analyze 方法已经被引入了，所以 Worker 中的 analyze 不会被引入。

对于引入的方法，Groovy 会把对该方法的调用「路由」到实例上相应的方法。
如：
    bernie.analyze() 实际上是调用 Manager 中引入的 analyze 方法。在内部通过实例调用具体方法。

    def analyze(){
        expert.analyze()
    } 

有了 @Delegate 注解，使得 Manager 是可拓展的。如果在 Worker 或 Expert 中添加或去掉代码，不必对 Manager 类做任何修改，相应的变化就会生效。
*/
def bernie = new Manager()
bernie.analyze() 
bernie.work()
bernie.writeReport()