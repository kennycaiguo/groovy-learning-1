import groovy.sql.Sql
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// def sql = Sql.newInstance("jdbc:mysql://localhost:3306/uaa", "root", "harry@admin")

Logger log = LoggerFactory.getLogger(this.getClass())
println dataSource.dump()

// 使用已有的数据源创建 Sql 对象
def sql = new Sql(dataSource)

def rows = sql.rows("select * from jhi_user")
rows.each { log.info("it.dump(): {}", it.dump()) }

rows.collect() { it['login'] }
