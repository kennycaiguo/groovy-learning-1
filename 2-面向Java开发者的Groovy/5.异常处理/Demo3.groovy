// 异常处理
// 在 Groovy 中对于我们不想处理的异常，或者不适合在当前方法处理的异常，Groovy并不强制我们处理，而是会自动传递到更高的一层。

// 如：openFile 方法并没有处理 FileNotFoundException 异常。如果产生了异常，它会被传递到调用代码，由调用代码来处理。
def openFile(fileName) {
    new FileInputStream(fileName)
}


// 1. 直接调用，会在调用方抛出异常
// println openFile('/Users/Harry/Workspace/groovy-learning/5.异常处理/no-exist.groovy')


// 2. 调用方处理异常
try {
    openFile("/Users/Harry/Workspace/groovy-learning/5.异常处理/no-exist.groovy")
} catch (FileNotFoundException e) {
    println "Oops: " + e
}


// 3. 捕获任意 Exception 异常. 注意：catch 不指定类型只能捕获 Exception 类型，不能捕获 Error 或 Throwable异常。
try {
    openFile("/Users/Harry/Workspace/groovy-learning/5.异常处理/no-exist.groovy")
} catch (e) {
    println "Oops: " + e
}

// 4. 捕获 Error 或 Throwable 异常，使用 Throwable throwable
try {
    openFile("/Users/Harry/Workspace/groovy-learning/5.异常处理/no-exist.groovy")
} catch (Throwable throwable) {
    println "Oops: " + throwable
}
