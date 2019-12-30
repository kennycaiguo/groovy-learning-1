/**
 * @author Harry Zhang 
 * @since 2019/12/21 11:07 AM
 */
class Resource {
    def open() { print "opened..." }

    def close() { print "closed..." }

    def read() { print "read..." }

    def write() { print "write..." }

    def static use(closure) {
        def r = new Resource()
        try {
            r.open()
            closure(r)
        } finally {
            r.close()
        }
    }
}

def resource = new Resource()
resource.open()
resource.read()
resource.write()
// 忘记调用 close 了。

println "\n----------------------"
/*使用闭包做前后置操作，只在闭包内传递业务逻辑。(如果方法只有闭包一个参数，括号都不用写。)*/
Resource.use { res ->
    res.read()
    res.write()
}
