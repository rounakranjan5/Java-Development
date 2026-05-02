package Synchronization;

class BankAccount{
    int balance=1000;

    public void withdraw(int amount){
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

class Person extends Thread{

    BankAccount account;

    Person(String name,BankAccount account){
        super(name);
        this.account=account;
    }

    @Override
    public void run() {
        account.withdraw(500);
    }
}

public class withoutSyncDemo {

    public static void main(String[] args) {

        BankAccount account=new BankAccount();

        Person p1=new Person("Father",account);
        Person p2=new Person("Mother",account);
        Person p3=new Person("Child",account);

        p1.start();
        p2.start();
        p3.start();

    }

}
