package ThreadPriority;

import java.sql.SQLOutput;

class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("executing thread - "+Thread.currentThread().getName());
    }
}

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        Thread t1=new Thread(new MyThread());
        Thread t2=new Thread(new MyThread());
        Thread t3=new Thread(new MyThread());
        Thread t4=new Thread(new MyThread());
        Thread t5=new Thread(new MyThread());
        Thread t6=new Thread(new MyThread());
        Thread t7=new Thread(new MyThread());

        t1.setPriority(10);
        t2.setPriority(1);
        t3.setPriority(5);
        t4.setPriority(Thread.MAX_PRIORITY);
        t5.setPriority(Thread.MIN_PRIORITY);
        t6.setPriority(Thread.NORM_PRIORITY);
        t7.setPriority(7);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

        System.out.println("---- Main Thread ----");
        System.out.println("Before changing the priority of the Main Thread : "+Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(9);
        System.out.println("After changing the priority of the Main Thread : "+Thread.currentThread().getPriority());

        Thread t8=new Thread(new MyThread());
        System.out.println("t1 priority "+t8.getPriority());//9 -> inherited the priority from its parent

        // In Java, when a thread creates another thread, the newly created thread inherits the priority of its parent thread by default. So, if the parent thread has priority 8, the child thread will also start with priority 8 automatically. This inherited priority remains in effect until and unless the priority of the child thread is explicitly changed using setPriority(). However, even with the same or higher priority, thread scheduling and execution order are still not guaranteed, as priority only serves as a scheduling hint to the operating system.

    }
}
