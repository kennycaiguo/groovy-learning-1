/**
 * 验证 Groovy 对 POGO 对象的方法调用机制
 *
 * @author Harry Zhang 
 * @since 2020/1/16 2:00 PM
 */
class TestMethodInvocation extends GroovyTestCase {

    /**
     * 1. Integer 没有实现 GroovyInterceptable
     * 2. 目标方法存在 metaClass 中， 👉 调用 metaClass 中的 toString
     */
    void testInterceptedMethodCallOnPojo() {
        def val = new Integer(3)
        Integer.metaClass.toString = { -> 'intercepted' }
        assertEquals("intercepted", val.toString())
    }

    /**
     * 1. AnInterceptable 实现了 GroovyInterceptable，不论目标方法是否存在都会调用 👉 invokeMethod()
     */
    void testInterceptableCalled() {
        def obj = new AnInterceptable()
        // invokeMethod() 返回了 intercepted，所以下面两个测试都能通过。
        assertEquals('intercepted', obj.existingMethod())
        assertEquals('intercepted', obj.nonExistingMethod())
    }

    /**
     * 1. AGroovyObject 没实现 GroovyInterceptable;
     * 2. 目标方法 existingMethod2 存在 metaClass 中 👉 调用 metaClass 中的 existingMethod2
     */
    void testInterceptedExistingMethodCalled() {
        // 修改方法，返回 'intercepted'（原本返回 'existingMethod2' ）
        AGroovyObject.metaClass.existingMethod2 = { -> 'intercepted' }
        def obj = new AGroovyObject()
        assertEquals('intercepted', obj.existingMethod2())
    }

    /**
     * 1. AGroovyObject 没实现 GroovyInterceptable;
     * 2. 目标方法不存在 metaClass 中 👉 调用 POGO 自身的 existingMethod
     */
    void testUnInterceptedExistingMethodCalled() {
        def obj = new AGroovyObject()
        assertEquals('existingMethod', obj.existingMethod())
    }

    /**
     * 1. AGroovyObject 没实现 GroovyInterceptable;
     * 2. 目标方法没在 metaClass 中；
     * 3. POGO 自身也没有目标方法；
     * 4. 存在以目标方法名的属性，并且属性是闭包类型 👉 调用闭包
     */
    void testPropertyThatIsCLousreCalled() {
        def obj = new AGroovyObject()
        assertEquals('closure called', obj.closureProp())
    }

    /**
     * 1. ClassWithInvokeAndMissingMethod 没实现 GroovyInterceptable;
     * 2. 目标方法 existingMethod() 没在 metaClass 中 👉 调用自身的 existingMethod；
     * -----------------------------------------------------------------------
     * 2. 目标方法 nonExistingMethod() 没在 metaClass 中；
     * 3. 不存在以 nonExistingMethod() 命名的属性；
     * 4. 存在 methodMissing() 👉 调用 methodMissing。
     */
    void testMethodMissingCalledOnlyForNonExisting() {
        def obj = new ClassWithInvokeAndMissingMethod()
        assertEquals('existingMethod', obj.existingMethod())
        assertEquals('missing called', obj.nonExistingMethod())
    }

    /**
     * 1. ClassWithInvokeOnly  没实现 GroovyInterceptable;
     * 2. 目标方法 existingMethod 没在 metaClass 中，调用自身的 existingMethod。
     * --------------------------------------------------------------------
     * 2. 目标方法 nonExistingMethod 没在 metaClass 中；
     * 3. 目标方法 nonExistingMethod 也没在 POGO 中；
     * 4. 不存在以 nonExistingMethod 命名的属性；
     * 5. 不存在 methodMissing，调用 invokeMethod()
     */
    void testInvokeMethodCalledForOnlyNonExisting() {
        def obj = new ClassWithInvokeOnly()
        assertEquals('existingMethod', obj.existingMethod())
        assertEquals('invoke called', obj.nonExistingMethod())
    }

    /**
     * 1. TestMethodInvocation 没实现 GroovyInterceptable；
     * 2. nonExistingMethod() 没在 metaClass 中；
     * 3. nonExistingMethod() 没在 POGO 中；
     * 4. 没有 nonExistingMethod 这个属性；
     * 5. 没有 methodMissing()；
     * 6. 没有 invokeMethod() 👉 抛出 MissingMethodException
     */
    void testMethodFailsOnNonExistent() {
        def obj = new TestMethodInvocation()
        shouldFail(MissingMethodException) { obj.nonExistingMethod() }
    }
}


class AnInterceptable implements GroovyInterceptable {
    def existingMethod() {}

    def invokeMethod(String name, args) { 'intercepted' }
}

class AGroovyObject {
    def existingMethod() { 'existingMethod' }

    def existingMethod2() { 'existingMethod2' }
    def closureProp = { 'closure called' }
}

class ClassWithInvokeAndMissingMethod {
    def existingMethod() { 'existingMethod' }

    def invokeMethod(String name, args) { 'invoke called' }

    def methodMissing(String name, args) { 'missing called' }
}

class ClassWithInvokeOnly {
    def existingMethod() { 'existingMethod' }

    def invokeMethod(String name, args) { 'invoke called' }
}
