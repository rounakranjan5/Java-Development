package StaticVarPack;

public class StaticVar {
    public static int counter=5;
    public static void main(String[] args) {
        System.out.println(counter);
        StaticVar svr=new StaticVar();

        svr.counter=42;
        System.out.println(counter);

        StaticVar svr2=new StaticVar();

        System.out.println(counter);
    }
}
