import groovy.sql.Sql

/**
 * @author Harry Zhang 
 * @since 2020/1/8 6:01 PM
 */

def sql = Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo', 'root', 'harry@admin', 'com.mysql.jdbc.Driver')

/**
 * 案例1：使用 DataSet 添加数据不需要写 Sql
 */
def dataSet = sql.dataSet('weather')
println "Number of cities: " + sql.rows("select * from weather").size()
dataSet.add(city: 'Denver', temperature: 19)
println "Number of cities: " + sql.rows("select * from weather").size()


/**
 * 案例2：使用传统方式新增数据 execute() executeInsert()
 */
temperature = 50
sql.executeInsert("""insert into weather (city, temperature) values ('Oklahoma City', ?)""", [temperature])
println sql.firstRow("select temperature from weather where city = 'Oklahoma City'")
