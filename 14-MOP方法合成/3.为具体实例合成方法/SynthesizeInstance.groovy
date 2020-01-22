/**
 * 向具体实例合成方法
 *
 * @author Harry Zhang 
 * @since 2020/1/21 9:56 AM
 */
class Person7 {}

def emc = new ExpandoMetaClass(Person7)
emc.methodMissing = { String name, args ->
    "I'm Jack of all trades... I can $name"
}
emc.initialize()

def jack = new Person7()
def paul = new Person7()

jack.metaClass = emc

println jack.sing()
println jack.dance()
println jack.juggle()

try {
    println paul.sing()
} catch (e) {
    println e
}
