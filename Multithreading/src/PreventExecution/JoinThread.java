package PreventExecution;

class MyThread3 extends Thread{

    MyThread3(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("washing started....");
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException ie){
            System.out.println(ie);
        }
        System.out.println("washing completed");

    }
}

public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread3 mt3=new MyThread3("Washing Machine Thread");
        mt3.start();
        mt3.join();
//        mt3.join(1000);
//        mt3.join(100,200);
        System.out.println("Drying started...");
        Thread.sleep(2000);
        System.out.println("Drying completed...");
    }
}
