package ThreadGroup;

class MyThread implements Runnable{

    @Override
    public void run() {
        try{
            System.out.println("Current Running Thread : "+Thread.currentThread().getName());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class ThreadGrpDemo {

    public static void main(String[] args) {
        ThreadGroup th1=new ThreadGroup("LOGIN-API");
        ThreadGroup th2=new ThreadGroup("signup-API");
        Thread t1=new Thread(th1,new MyThread(),"api-1");
        Thread t2=new Thread(th1,new MyThread(),"api-2");
        Thread t3=new Thread(th2,new MyThread(),"api-3");
        Thread t4=new Thread(th2,new MyThread(),"api-4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        System.out.println(t1.getThreadGroup().getName());
        System.out.println(t2.getThreadGroup().getName());
        System.out.println(t3.getThreadGroup().getName());
        System.out.println(t4.getThreadGroup().getName());
        th1.list();
        th2.list();


    }

}
