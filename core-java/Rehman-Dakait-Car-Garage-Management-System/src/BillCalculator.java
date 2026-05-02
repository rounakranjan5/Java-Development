public class BillCalculator {

    public static int calcBill(Service[] services,boolean[] selected){
        int total=0;
        for(int i=0;i< services.length;i++){
            if(selected[i]){
                total+=services[i].getPrice();
            }
        }
        return total;
    }
}
