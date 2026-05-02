package ThreadCreation;

class MyThreadRunnable implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<2;i++){
            System.out.println("running thread -> "+Thread.currentThread().getName());
        }
    }
}

public class ThreadDemo2 {

    public static void main(String[] args) {
        MyThreadRunnable mt1=new MyThreadRunnable();

        Thread t1=new Thread(mt1);
        t1.start();

        Thread t2=new Thread(new MyThreadRunnable());
        t2.start();

        System.out.println("main thread ...");
    }
}
