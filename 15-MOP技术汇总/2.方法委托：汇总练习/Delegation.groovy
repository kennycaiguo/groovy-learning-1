/**
 * 方法委托，一个 Manager 将工作委托给一个 Worker 和一个 Expert，使用 MethodMissing() 和 ExpandoMetaClass 可以实现。
 * 如果在 Manager 上调用的方法不存在，该实例的 methodMissing() 会将调用路由到 Worker 或 Expert。
 *
 * @author Harry Zhang 
 * @since 2020/1/21 10:52 AM
 */
class Worker1 {
    def simpleWork1(spec) { println "worker dose work1 with spec $spec" }

    def simpleWork2() { println "worker dose work2" }
}

class Expert1 {
    def advancedWork1(spec) { println "Expert dose work1 with spec $spec" }

    def advancedWork2(scope, spec) {
        println "Expert dose work2 with scope $scope spec $spec"
    }
}

class Manager1 {
    def worker = new Worker1()
    def expert = new Expert1()

    def schedule() { println "Scheduling..." }

    def methodMissing(String name, args) {
        println ">> interceping call to $name..."
        def delegateTo = null

        if (name.startsWith('simple')) delegateTo = worker
        if (name.startsWith('advanced')) delegateTo = expert

        if (delegateTo?.metaClass?.respondsTo(delegateTo, name, args)) {
            // this 是当前运行的 Manager1 对象实例，即：peter
            Manager1 instance = this
            // 给 instance 实例添加动态方法，执行者是委托对象。
            instance.metaClass."${name}" = { Object[] varArgs ->
                /*
                 * instance 实例将方法委托给委托实例。
                 *
                 * 方式1：使用委托对象直接执行 invokeMethod() 方法（注意不要写错了方法名😓，由于把 invokeMethod 写成 invokMethod 被坑了好久。。。）
                 *       delegateTo.invokeMethod(name, varArgs)
                 *
                 * 方式2：使用委托对象的metaClass获取到方法再执行 invoke()
                 */
                def method = delegateTo.metaClass.getMetaMethod(name, varArgs)
                method.invoke(delegateTo, varArgs)
            }
            // 调用委托实例的方法
            delegateTo.invokeMethod(name, args)
        } else {
            throw new MissingMethodException(name, Manager.class, args)
        }
    }
}

def peter = new Manager1()
peter.schedule()
peter.simpleWork1('fast')
peter.simpleWork1('quality')

peter.simpleWork2()
peter.simpleWork2()

peter.advancedWork1('fast')
peter.advancedWork1('quality')

peter.advancedWork2('prototype', 'fast')
peter.advancedWork2('product', 'quality')

try {
    peter.simpleWork3()
} catch (e) {
    println e
}
