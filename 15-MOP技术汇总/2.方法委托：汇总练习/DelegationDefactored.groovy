/**
 * Delegation 方法委托简化版
 *
 * @author Harry Zhang 
 * @since 2020/1/21 2:30 PM
 */
class Manager2 {
    {
        delegateCallsTo(Worker1, Expert1, GregorianCalendar)
    }

    def schedule() { println "Scheduling..." }
}

Object.metaClass.delegateCallsTo = { Class... klassOdDelegates ->
    // 所有要委托的类，实例化成对象集合。
    def objectDelegates = klassOdDelegates.collect { it.newInstance() }
    // 给调用该方法 delegateCallsTo 的对象添加 methodMissing 方法。
    delegate.metaClass.methodMissing = { String name, args ->
        println ">> intercepting call to $name"
        // 是否有委托对象有该方法？
        def delegateTo = objectDelegates.find {
            it.metaClass.respondsTo(it, name, args)
        }
        if (delegateTo) {
            // 给委托对象添加方法
            delegate.metaClass."$name" = { Object[] varArgs ->
                delegateTo.invokeMethod(name, varArgs)
            }
            delegateTo.invokeMethod(name, args)
        } else {
            throw new MissingMethodException(name, delegate.getClass(), args)
        }
    }
}

def peter = new Manager2()
peter.schedule()
peter.simpleWork1('fast')
peter.simpleWork1('quality')

peter.simpleWork2()
peter.simpleWork2()

peter.advancedWork1('fast')
peter.advancedWork1('quality')

peter.advancedWork2('prototype', 'fast')
peter.advancedWork2('product', 'quality')

println "Is 2008 a leap year? ${peter.isLeapYear(2008)}" // 委托给了 GregorianCalendar

try {
    peter.simpleWork3()
} catch (e) {
    println e
}
