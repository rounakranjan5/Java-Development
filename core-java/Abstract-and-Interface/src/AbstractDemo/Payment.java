package AbstractDemo;

public abstract class Payment {
    Payment(){
        System.out.println("Payment Abstract class running");
    }

    abstract void pay();

    void invoice(){
        System.out.println("invoice generated");
    }

    int varInAbstract=18;



}
