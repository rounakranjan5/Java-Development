package ThreadNaming;

class MyThread implements  Runnable{
    @Override
    public void run() {
        System.out.println("executing - "+Thread.currentThread().getName());
    }
}

public class ThreadNamingDemo {
    public static void main(String[] args) {
        Thread t1=new Thread(new MyThread());
        System.out.println("before changing the name of the thread is : "+t1.getName());
        t1.start();// executing Mera Pehla Thread
        t1.setName("Mera Pehla thread");

        System.out.println("after changing the name of the thread is : "+t1.getName());

        Thread t2=new Thread(new MyThread());
        System.out.println("thread 2 name is : "+t2.getName());
        t2.start();//executing Thread-1

        Thread t3=new Thread(new MyThread(),"teesra thread");
        System.out.println("Third thread name printing : "+t3.getName());

        t3.setName("edited teesra Thread");
        System.out.println(t3.getName());
    }
}
