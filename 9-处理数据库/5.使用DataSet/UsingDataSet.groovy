import groovy.sql.Sql

/**
 * 使用 DateSet，不需要手动执行 sql 语句。给 dataSet 指定一个表名，它会返回一个虚拟代理，直到迭代时才会去读取实际的行。
 *
 * 使用 findAll 可以过滤结果，直到调用 each() 时才会去读取数据。
 *
 * @author Harry Zhang 
 * @since 2020/1/8 5:52 PM
 */

def sql = Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo', 'root', 'harry@admin', 'com.mysql.jdbc.Driver')

def dataSet = sql.dataSet('weather')
def citiesBelowFreezing = dataSet.findAll { it.temperature < 32 }
println "Cities below freezing: "
citiesBelowFreezing.each {
    println it.city
}
