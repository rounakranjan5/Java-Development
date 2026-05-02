package PreventExecution;

class MyThread2 implements Runnable{
    @Override
    public void run() {

        for(int i=0;i<2;i++){
        System.out.println("current running thread : "+Thread.currentThread().getName());
        Thread.yield();
        }

    }
}

public class YieldThread {

    public static void main(String[] args) {
        Thread t1=new Thread(new MyThread2());
        t1.start();
        Thread t2=new Thread(new MyThread2());
        t2.start();
        System.out.println("main thread");
    }

}
