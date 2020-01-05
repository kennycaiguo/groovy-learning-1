/**
 * 间接访问属性
 *
 * @author Harry Zhang 
 * @since 2020/1/4 11:10 PM
 */
class Car1 {
    int miles
    int fuelLevel
}

def car = new Car1(miles: 25, fuelLevel: 80)

properties = ['miles', 'fuelLevel']
properties.each { name ->
    println "$name = ${car[name]}"
}

car[properties[1]] = 100
println "fuelLevel now is ${car.fuelLevel}"
