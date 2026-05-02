package ThreadNaming;

class customThread extends Thread{
    @Override
    public void run() {
        System.out.println("running "+Thread.currentThread().getName());
    }
}

public class ThreadNamingDemo2 {
    public static void main(String[] args) {
        customThread ct1=new customThread();
        System.out.println("before change : "+ct1.getName());
        ct1.start();// running Mera First Thread
        ct1.setName("Mera First Thread");
        System.out.println("after change : "+ct1.getName());

    }
}
