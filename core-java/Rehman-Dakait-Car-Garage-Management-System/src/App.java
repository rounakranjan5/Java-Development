import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        try{
            System.out.println("🚙 Welcome to The Rehman Dakait Car Garage 🚙");
            System.out.println("Enter Your Good Name");
            String custName=sc.nextLine();

            System.out.println("Enter Your Car Number Please");
            String carNumb=sc.nextLine();

            System.out.println("Enter Your Car Type (Sedan/SUV/Super-Car)");
            String carType=sc.nextLine();

            Customer cust=new Customer(custName,carNumb,carType);

            Service[] services={
                    new Service(1,"Car Wash",250),
                    new Service(2,"Engine Fixes",425),
                    new Service(3,"Brakes Check",250),
                    new Service(4,"Coolant Change",500),
                    new Service(5,"Oil Change",790),
                    new Service(6,"Full Service",1250)
            };

            boolean selected[]=new boolean[services.length];

            while(true){
                boolean anyServicesAvailable=false;
                System.out.println("🧑‍🔧 Available Services");


                for(int i=0;i< services.length;i++){
                    if(!selected[i]){
                        System.out.println(services[i].getId()+". "+services[i].getName()+" - ₹"+services[i].getPrice());
                        anyServicesAvailable=true;
                    }

                }

                if(!anyServicesAvailable) System.out.println("Oops !! No services available at the moment...");

                System.out.println("0. Generate Bill");

                System.out.println("Select Service Id :");
                int choice=sc.nextInt();

                if(choice<0 || choice>services.length){
                    throw new Exception("Invalid Choice");
                }

                if(choice==0){
                    int totalBill=BillCalculator.calcBill(services,selected);

                    System.out.println("\n### Final Bill ###\n");
                    System.out.println("Customer Name : "+cust.getName());
                    System.out.println("Car Number : "+cust.getCarNumb());
                    System.out.println("Car Type : "+cust.getCarType());
                    System.out.println("Services Availed : ");

                    int cnter=1;
                    for (int i=0;i<services.length;i++){
                        if(selected[i]){
                            System.out.println(cnter+". "+services[i].getName()+" - ₹"+services[i].getPrice());
                            cnter++;
                        }
                    }

                    System.out.println("-----------------");
                    System.out.println("Grand Total : "+totalBill);
                    int gst=(int)((0.18)*totalBill);
                    System.out.println("GST (18%) : "+gst);
                    System.out.println("💵 Net Payable Amount : "+(totalBill+gst));

                    break;
                }

                if(selected[choice-1]) System.out.println("⚠️ Service already selected !!\n");
                else {
                    System.out.println("Service added 🪛 : "+services[choice-1].getName()+"\n");
                    selected[choice-1]=true;
                }

            }

        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}