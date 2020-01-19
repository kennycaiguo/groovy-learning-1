/**
 * ExpandoMetaClass 是 MetaClass 的实现，使用 ExpandoMetaClass，可以向对象添加方法、属性、构造器和静态方法。
 * 不像 Category 只能在 use 代码块中运行，ExpandoMetaClass 注入的方法能在任意处像普通方法一样的调用。
 *
 * 限制：只能在 Groovy 中调用动态添加的方法，不能通过编译过的 Java 调用，也不能从 Java 中通过反射获得。
 *
 * @author Harry Zhang
 * @since 2020/1/19 10:12 PM
 */

/**
 * 案例1：在 Integer 上添加一个方法 daysFromNow，用于计算给定天数之后的日期。
 *
 * 在这段代码中，将方法 daysFromNow 引入到了 Integer 的 metaClass 中，如果要将该方法注入到任何对象中，可以将其添加到 Object 的 metaClass 上。
 */
Integer.metaClass.daysFromNow = { ->
    Calendar today = Calendar.instance
    // 给当前时间添加指定天数，delegate 就是调用方法的对象。
    today.add(Calendar.DAY_OF_MONTH, delegate)
    today.time
}
println 5.daysFromNow()


/**
 * 案例2：注入属性
 * . 号后的调用如果没有小括号，Groovy 会将其作为属性调用。所以要注入一个属性，我们需要添加该属性的 get 方法，如下：
 */
Integer.metaClass.getDaysFromNow = { ->
    Calendar today = Calendar.instance
    today.add(Calendar.DAY_OF_MONTH, delegate)
    today.time
}
println 5.daysFromNow


/**
 * 案例3：在基类上添加方法，子类（派生类）都能调用新方法。
 */
Number.metaClass.someMethod = { ->
    println delegate.dump()
    println "someMethod called"
}
2.someMethod()
2L.someMethod()
2F.someMethod()


/**
 * 案例4：注入静态方法
 */
Integer.metaClass.'static'.isEven = { it % 2 == 0 }
println "Is 2 even? ${Integer.isEven(2)}"
println "Is 3 even? ${Integer.isEven(3)}"


/**
 * 案例5：注入构造器，使用 constructor 属性和 << 操作符，注意：该操作符不能覆盖已有的构造器（要覆盖使用 = 操作符）。
 * 方法用于计算当前时间是今年的多少天。相当于：
 * Integer(Calendar calendar){ return new Integer(calendar.get(Calendar.DAY_OF_YEAR))}*/
Integer.metaClass.constructor << { Calendar calendar ->
    new Integer(calendar.get(Calendar.DAY_OF_YEAR))
}
println new Integer(Calendar.instance)


/**
 * 案例6：使用方法分组，批量注入多个不同类型方法。
 */
Integer.metaClass {
    // 注入实例方法
    daysFromNow = { ->
        Calendar today = Calendar.instance
        today.add(Calendar.DAY_OF_MONTH, delegate)
        today.time
    }
    // 注入实例属性
    getDaysFromNow = { ->
        Calendar today = Calendar.instance
        today.add(Calendar.DAY_OF_MONTH, delegate)
        today.time
    }
    // 声明静态方法块
    'static' {
        // 注入静态方法
        isEven = {
            it % 2 == 0
        }
    }
    // 注入构造器
    constructor = { Calendar calendar ->
        new Integer(calendar.get(Calendar.DAY_OF_YEAR))
    }
    // 覆盖构造器
    constructor = { int val ->
        println "Intercepting constructor call"
        constructor = Integer.class.getConstructor(Integer.TYPE)
        constructor.newInstance(val)
    }
}
println "---------"
println 5.daysFromNow()
println 5.daysFromNow
println "10 is even? " + Integer.isEven(10)
println new Integer(Calendar.instance)
println new Integer(10)
