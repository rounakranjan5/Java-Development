public class Customer {
    String name;
    String carNumb;
    String carType;

    Customer(String name,String carNumb,String carType){
        this.name=name;
        this.carNumb=carNumb;
        this.carType=carType;
    }

    public String getCarNumb() {
        return carNumb;
    }

    public void setCarNumb(String carNumb) {
        this.carNumb = carNumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
