/**
 * 使用 Mixed 的链表结构实现装饰者模式。
 *
 * @author Harry Zhang
 * @since 2020/1/20 11:19 AM
 */


/**
 * 该抽象类定义了行为
 */
abstract class Writer {
    abstract void write(String message)
}

/**
 * Writer 的实现，用于将参数值打印出来。
 */
class StringWriter extends Writer {
    def target = new StringBuilder()

    @Override
    void write(String message) {
        target.append(message)
    }

    @Override
    String toString() {
        return target.toString()
    }
}

def writeStuff(Writer writer) {
    writer.write("This is stupid")
    println writer
}

/**
 * 创建一个 Writer 实例，给该实例注入一系列方法。
 *
 * @param theWriter 被创建的对象
 * @param filters 需要混入方法的 Mixin 类，给了默认值，使其成为一个可选参数。
 * @return Writer 实例
 */
static def create(theWriter, Object[] filters = []) {
    def instance = theWriter.newInstance()
    // 给实例混入方法
    filters.each { filter -> instance.metaClass.mixin filter }
    instance
}

writeStuff(create(StringWriter))

/**
 * 在不修改目标类的的前提下，实现其他特定化的 Writer，如：给定参数的值以大写方式写出。
 */
class UppercaseFilter {
    void write(String message) {
        def allUpper = message.toUpperCase()
        // 调用一个不存在的方法，这个方法在下面动态注入，该方法会调用当前 mixin 链的下一个 filter。
        invokeOnPreviousMinxin(this.metaClass, "write", allUpper)
    }
}

/**
 * 关键代码：调用 mixed 链的下一个过滤器。
 */
Object.metaClass.invokeOnPreviousMinxin = { MetaClass currentMixinMetaClass, String method, Object[] args ->
    // 链条中最左侧的实例就是目标实例，通过 delegate.getClass() 获得。
    def previousMixin = delegate.getClass()
    // 找到上一个 mixin，mixedIn 是 Groovy 保存所有有序 Mixin 的链表。
    for (mixin in mixedIn.mixinClasses) {
        if (mixin.mixinClass.theClass == currentMixinMetaClass.delegate.theClass) break
        previousMixin = mixin.mixinClass.theClass
    }
    // 调用上一个 mixin 的方法
    mixedIn[previousMixin]."$method"(*args)
}

// 调用 create 创建 StringWriter 实例，混入 UppercaseFilter 的方法，再调用打印方法。
writeStuff(create(StringWriter, UppercaseFilter))


/**
 * 再添加一个过滤器，用来过滤脏话。
 */
class ProfanityFilter {
    void write(String message) {
        def result = message.replace('stupid', 's*****')
        invokeOnPreviousMinxin(this.metaClass, 'write', result)
    }
}

writeStuff(create(StringWriter, ProfanityFilter))

writeStuff(create(StringWriter, UppercaseFilter, ProfanityFilter))
writeStuff(create(StringWriter, ProfanityFilter, UppercaseFilter))
