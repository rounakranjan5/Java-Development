public class PublicAccMod {
    public static String pubDet="I m the Public Access Modifier";
    static void printPublicDetails(){
        System.out.println(pubDet);
    }
    public static void main(String[] args) {
        System.out.println("public access modifier");
        printPublicDetails();
    }
}
