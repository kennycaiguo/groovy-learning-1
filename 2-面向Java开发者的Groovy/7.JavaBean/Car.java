// Java 版本的类
public class Car {
    private int miles;
    private final int year;

    public Car(int theYaer) {
        year = theYaer;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getYear() {
        return year;
    }

    public static void main(String[] args) {
        Car car = new Car(2008);
        System.out.println("Year: " + car.getYear());
        System.out.println("Miles: " + car.getMiles());
        System.out.println("Setting Miles: ");
        car.setMiles(25);
        System.out.println("Miles: " + car.getMiles());
    }
}
