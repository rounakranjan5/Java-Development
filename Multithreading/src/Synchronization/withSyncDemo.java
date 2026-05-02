package Synchronization;

class BankAccount1{
    int balance=1000;

    synchronized public void withdraw(int amount){
        if(balance>=amount){
            System.out.println(Thread.currentThread().getName()+" is withdrawing $ "+amount);
            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e){
                System.out.println("Error : Withdraw Interrupted "+e);
            }
            balance-=amount;

            System.out.println(Thread.currentThread().getName()+" Completed withdraw and Current Balance is $ "+balance);
        }
        else{
            System.out.println("Not Enough Balance For - "+Thread.currentThread().getName());
        }
    }
}

class Person1 extends Thread{

    BankAccount1 account;

    Person1(String name,BankAccount1 account){
        super(name);
        this.account=account;
    }

    @Override
    public void run() {
        account.withdraw(500);
    }
}

public class withSyncDemo {

    public static void main(String[] args) {

        BankAccount1 account=new BankAccount1();

        Person1 p1=new Person1("Father",account);
        Person1 p2=new Person1("Mother",account);
        Person1 p3=new Person1("Child",account);

        p1.start();
        p2.start();
        p3.start();

    }

}
