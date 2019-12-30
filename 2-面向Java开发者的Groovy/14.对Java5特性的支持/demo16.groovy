// enum 支持

// Groovy 中可以在 case 语句使用枚举值。甚至可以使用一组枚举值，多个枚举值区间。
enum CoffeeSize {SHORT, SMAILL, MEDIUM, LARGE, MUG}
def orderCoffee(size){
    print "Cofee order recevied for size $size: "
    switch (size) {
        case [CoffeeSize.SHORT, CoffeeSize.SMAILL]: // 多个枚举值
            println "you're health conscious"
            break
        case CoffeeSize.MEDIUM..CoffeeSize.LARGE: // 枚举值区间
            println "you gotta be a programmer"
            break
        case [CoffeeSize.MUG]:
            println "you should try Caffeine IV" 
            break
    }
}

orderCoffee CoffeeSize.SMAILL
orderCoffee CoffeeSize.LARGE
orderCoffee CoffeeSize.MUG

println ''
println 'Available sizes are:'
for (size in CoffeeSize.values()) {
    println "$size"
}