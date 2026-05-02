package PreventExecution;

class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("Current Running Thread : "+Thread.currentThread().getName());
        System.out.println("Started Preparing Tea....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tea is Ready to Drink....");
    }
}

public class SleepThread {

    public static void main(String[] args) {
        MyThread1 mt1=new MyThread1();
        mt1.start();
//        MyThread1 mt2=new MyThread1();
//        mt2.start();
        System.out.println("main thread");
    }

}
