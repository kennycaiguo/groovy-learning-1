/**
 * 案例：metaClass 拦截 Integer 类上的方法。
 *
 * @author Harry Zhang 
 * @since 2020/1/19 2:08 PM
 */
Integer.metaClass.invokeMethod { methodName, args ->
    System.out.println("Call $methodName intercepted on $delegate")
    def metaMethod = Integer.metaClass.getMetaMethod(methodName, args)
    if (null == metaMethod) {
        // 找不到方法调用 invokeMissingMethod，注意：是 delegate 对象
        Integer.metaClass.invokeMissingMethod(delegate, methodName, args)
    } else {
        // 找到方法执行目标方法，可以方法前后施加切面逻辑。
        System.out.println("running pre-filter...")
        def result = metaMethod.invoke(delegate, args)
        System.out.println("running post-filter...")
        result
    }
}

println 5.floatValue()
println 5.intValue()
try {
    println 5.empty()
} catch (e) {
    println e
}

