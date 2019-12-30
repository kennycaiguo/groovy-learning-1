// 传递闭包给 Calibrator 类

def calibrator1 = new Calibrator({ println "the calculation provided" })

def calculation = { println("another calculation provided") }
def calibrator2 = new Calibrator(calculation)
