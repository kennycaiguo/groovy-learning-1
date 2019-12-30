// enum 定义构造器和方法

enum Methodolgies {
    Evo(5),
    XP(21),
    Scrum(30);

    final int daysInIteration
    Methodolgies(days) {daysInIteration = days}

    def interationDetails(){
        println "${this} recommends $daysInIteration days for interation"
    }
}

for (methodolgy in Methodolgies.values()){
    methodolgy.interationDetails()
}