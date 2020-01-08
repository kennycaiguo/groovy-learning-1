/**
 * 使用我们自己创建的拓展方法
 *
 * @author Harry Zhang 
 * @since 2020/1/7 10:57 AM
 */

def ticker = "ORCL"

println "Price for $ticker using instance method is ${String.getPrice(ticker)}"
println "Price for $ticker using instance method is ${ticker.getPrice()}"
