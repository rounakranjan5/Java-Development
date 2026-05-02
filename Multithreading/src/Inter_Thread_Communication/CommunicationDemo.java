package Inter_Thread_Communication;


class WeddingHall{
   boolean isDinnerReady=false;
}

class Guest extends Thread{
    WeddingHall hall;
    Guest(WeddingHall wh){
        hall=wh;
    }

    @Override
    public void run() {
        synchronized (hall){

            System.out.println("Guests Enter the Hall ...");
            while(!hall.isDinnerReady){
                System.out.println("Dinner is not ready yet... Guests Please wait");

                try {
                    hall.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Guests are having dinner...");
        }
    }
}

class Chef extends Thread{

    WeddingHall hall;
    Chef(WeddingHall wh){
        hall=wh;
    }

    @Override
    public void run() {
        synchronized (hall){
            System.out.println("Chef started preparing Dinner...");
            try{
                Thread.sleep(3000);
            }
            catch (Exception e){
                System.out.println(e);
            }

            hall.isDinnerReady=true;
            System.out.println("Dinner is ready...");
            hall.notify();
//            hall.notifyAll();
        }
    }
}

public class CommunicationDemo {

    public static void main(String[] args) {
        WeddingHall hall=new WeddingHall();
        Guest guest=new Guest(hall);
        Chef cf=new Chef(hall);

        guest.start();
        cf.start();
    }

}
