/**
 * 使用 Expando 类提供动态合成类的能力。可以在创建类时使用 Map 指定属性和方法，也可以动态的随时指定。
 *
 * @author Harry Zhang 
 * @since 2020/1/21 10:15 AM
 */
def carA = new Expando()
def carB = new Expando(year: 2012, miles: 0)
carA.year = 2012
carA.miles = 10

println "carA: $carA"
println "carB: $carB"


/**
 * 不仅可以动态添加属性，还可以添加方法。
 */
def car = new Expando(year: 2012, miles: 0, turn: { println "turing..." })
car.drive = {
    miles += 10
    println "$miles miles driven"
}
car.turn()
car.drive()
