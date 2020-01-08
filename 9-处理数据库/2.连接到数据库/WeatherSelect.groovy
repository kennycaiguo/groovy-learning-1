import groovy.sql.Sql

/**
 * groovy.sql.Sql 是 Groovy 中对数据库操作的对象
 *
 * 使用 newInstance 创建一个 Sql 实例，如果已经有 java.sql.Connection 实例，或者 java.sql.DataSource 就可以直接使用 Sql 的构造器实例一个 Sql 对象。
 *
 * @author Harry Zhang 
 * @since 2020/1/8 11:23 AM
 */

def sql = Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo',
        'root', 'harry@admin', 'com.mysql.jdbc.Driver')
println sql.connection.catalog
