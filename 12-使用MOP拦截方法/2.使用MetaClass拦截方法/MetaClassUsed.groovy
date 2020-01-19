import groovy.transform.Immutable

/**
 * @author Harry Zhang 
 * @since 2020/1/19 2:32 PM
 */
def printMetaClassInfo(instance) {
    print "MetaClass of [$instance] is: ${instance.metaClass.class.simpleName}"
    println " with delegate: ${instance.metaClass.delegate.class.simpleName}"
}

printMetaClassInfo(2)
println "MetaClass of Integer is ${Integer.metaClass.class.simpleName}"

println "----- Adding a method to Integer metaClass -----"
Integer.metaClass.someMethod = { -> /* method body */ }
printMetaClassInfo(2)
println "MetaClass of Integer is ${Integer.metaClass.class.simpleName}"


println '\n-------------- MyClass --------------'

@Immutable
class MyClass {
    String name
}

def obj1 = new MyClass("obj1")
printMetaClassInfo(obj1)
println "----- Adding a method to MyClass metaClass ----- "
MyClass.metaClass.somNewMethod = { -> /* method body */ }
printMetaClassInfo(obj1)

obj2 = new MyClass("obj2")
printMetaClassInfo(obj2)
