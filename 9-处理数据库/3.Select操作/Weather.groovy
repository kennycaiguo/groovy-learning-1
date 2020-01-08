import groovy.sql.Sql

/**
 * @author Harry Zhang 
 * @since 2020/1/8 11:39 AM
 */

def sql = Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo',
        'root', 'harry@admin', 'com.mysql.jdbc.Driver')
def select = 'select * from weather'

/**
 * 案例1：使用 eachRow() 执行 SQL 查询，对于表中每一行调用闭包。
 * 使用闭包的参数 GroovyResultSet 对象来访问表中的列。可以使用列名，也可以使用索引。
 */
println "City                Temperature"
sql.eachRow(select) { it ->
    printf("%-20s%s\n", it.city, it[1])
}


/**
 * 案例2：
 * 在上一个例子中，字段名是硬编码的，如果要从数据库获取字段名呢？
 * 可以使用 eachRow() 的另一个重载版本，其接收两个闭包，一个用于处理元数据，一个用于处理数据。
 * 闭包 1 的参数是 ResultSetMetaSet，这个闭包之后调用一次。另一个闭包对每一行调用一次。
 */
println "============================================"
def processMeta = { metaData ->
    metaData.columnCount.times { i ->
        printf("%-21s", metaData.getColumnLabel(i + 1))
    }
    println ''
}

sql.eachRow(select, processMeta) {
    printf("%-20s%s\n", it.city, it[1])
}

/**
 * 案例3：使用 rows() 会返回一个 ArrayList，这里还使用了提供分页的重载方法。
 */
rows = sql.rows(select, 2, 2)
println rows


/**
 * 案例4：
 * firstRow() 会返回结果的第一行。
 * call() 可以调用存储过程。
 * withStatement() 可以设置一个在查询之前调用的闭包，如果想在执行之前拦截做一些处理这会十分有效。
 */
