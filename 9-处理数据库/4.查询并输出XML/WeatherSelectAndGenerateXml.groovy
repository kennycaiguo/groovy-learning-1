import groovy.sql.Sql
import groovy.xml.StreamingMarkupBuilder

/**
 * @author Harry Zhang 
 * @since 2020/1/8 5:07 PM
 */

def sql = Sql.newInstance('jdbc:mysql://localhost:3306/weatherinfo', 'root', 'harry@admin', 'com.mysql.jdbc.Driver')
def select = 'select * from weather'

def xmlDocument = new StreamingMarkupBuilder().bind() {
    mkp.xmlDeclaration()
    weather {
        sql.eachRow(select) { resultSet ->
            city(name: resultSet.city) {
                temperature(resultSet.temperature)
            }
        }
    }
}

println xmlDocument
new File("${this.getClass().getResource(".").getPath()}city-weather.xml").withWriter {
    it << xmlDocument
}
