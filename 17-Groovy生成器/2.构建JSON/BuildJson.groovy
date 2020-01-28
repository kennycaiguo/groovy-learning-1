import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

/**
 * 序列化：
 * JsonBuilder、StreamingJsonBuilder。前者生成在内存中，可以将其写入到流中或做进一步处理；后者直接写入到流中。
 *
 * 反序列化：
 * JsonSlurper
 *
 * @author Harry Zhang
 * @since 2020/1/28 11:56
 */

class User {
    String first
    String last
    def sigs
    def tools
}

def user = new User(first: 'John', last: 'Smith', sigs: ['Java', 'Groovy'], tools: ['script': 'Groovy', 'test': 'Spock'])

/**
 * 序列化
 */
def builder = new JsonBuilder(user)
def writer = new StringWriter()
builder.writeTo(writer) // 生成的 Json 需要写入到一个流中。
println writer

/**
 * 定制序列化
 *
 *{"firstName":"John","lastName":"Smith","Special interest groups":["Java","Groovy"],"preferred tools":{"numberOfTools":2,"tools":{"script":"Groovy","test":"Spock"}}}*/
def builder1 = new JsonBuilder(user)
builder1 {
    firstName user.first
    lastName user.last
    "Special interest groups" user.sigs
    "preferred tools" {
        numberOfTools user.tools.size()
        tools user.tools
    }
}
def writer1 = new StringWriter()
builder1.writeTo(writer1)
println writer1


/**
 * 反序列化
 *
 * 向 parse() 方法传入一个来源，返回为一个 Map。
 */
def slurper = new JsonSlurper()
def obj = slurper.parse(new FileReader("${this.getClass().getResource(".").getPath()}user.json"))
println obj
