package TestStaticVarPack;

import StaticVarPack.StaticVar;

public class testStaticVar {

    public static void main(String[] args) {
        StaticVar svr=new StaticVar();
        System.out.println(svr.counter);

        svr.counter=12345;

        StaticVar svr2=new StaticVar();
        System.out.println(svr2.counter);
    }

}
