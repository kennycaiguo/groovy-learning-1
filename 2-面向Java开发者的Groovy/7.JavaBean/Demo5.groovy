// Groovy 重写 Car

class Car2 {
    def miles = 0
    
    // 设置了属性只读
    final year

    Car2(theYear) {
        year = theYear
    }
}

Car2 car = new Car2(2008)


println "Year: $car.year"
println "Miles: $car.miles"
println "Setting miles"
car.miles = 25
println "Miles: $car.miles"
