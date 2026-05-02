package ThreadCreation;

class MyThread extends Thread{
    @Override
    public void run() {
        for(int i=0;i<2;i++){
            System.out.println("Running Thread : "+Thread.currentThread().getName());
        }
        super.run();
    }
}

public class ThreadDemo1 {

    public static void main(String[] args) {
        MyThread t1=new MyThread();
        t1.start();

        MyThread t2=new MyThread();
        t2.start();

        System.out.println("main thread....");
    }
}
