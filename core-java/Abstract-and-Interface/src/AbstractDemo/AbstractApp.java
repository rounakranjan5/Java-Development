package AbstractDemo;

public class AbstractApp {

    public static void main(String[] args) {

        Payment p=new UpiPayment();

        p.pay();
        p.invoice();

        Payment p2=new CardPayment();
        p2.pay();
        p2.invoice();

        System.out.println(p.varInAbstract);
        p.varInAbstract=99;
        System.out.println(p.varInAbstract);

        System.out.println(p2.varInAbstract);
    }

}
