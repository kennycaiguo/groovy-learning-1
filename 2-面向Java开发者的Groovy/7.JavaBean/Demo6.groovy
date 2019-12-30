class Car3 {
    // 只读，不可修改
    final year

    // 使用 private 修饰的变量不允许在该类的外部对属性进行修改
    private miles = 0

    Car3(theYear) {
        year = theYear
    }

    def getMiles() {
        println "getMiles called"
        // miles 等于 return miles
        miles
    }

    private void setMiles(miles){
        throw new IllegalAccessException("you're not allowed to change miles");
    }

    def drive(dist){
        if(dist > 0) {
            // 在类的内部可以修改 private 修饰的属性 miles
            miles += dist
        }
    }
}

// 调用
def car = new Car3(2008);

println "Year: $car.year"
println "Miles: $car.miles"
println "Driving"
car.drive(10)
println "Miles: $car.miles"

try {
    print 'Can I set the year?'
    car.year = 1900
} catch (e){
    // final 修饰不可修改
    // Cannot set readonly property: year for class: Car
    println e.message
}

try {
    print 'Can I set the miles?'
    car.miles = 12
} catch (e){
    // private 修饰外部不可修改
    // you're not allowed to change miles
    println e.message
}
