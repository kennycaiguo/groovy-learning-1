/**
 * 每个实例都有一个 MetaClass，如果希望这个实例的行为该类的其他实例不同，可以将方法注入到该实例获得的 metaClass 中。
 *
 * @author Harry Zhang 
 * @since 2020/1/20 10:59 AM
 */
class Person1 {
    def play() { println "playing" }
}

def jack = new Person1()
def paul = new Person1()

jack.metaClass.sing = { ->
    'oh baby baby...'
}

println jack.sing()
try {
    println paul.sing()
} catch (e) {
    println e
}

// 取消掉注入到实例的方法
jack.metaClass = null
try {
    println jack.sing()
} catch (e) {
    println e
}
