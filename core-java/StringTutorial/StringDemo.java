public class StringDemo {
    public static void main(String[] args) {
        String str="static string";
        System.out.println(str.hashCode());
        String str3=str;
        System.out.println(str.equals(str3));
        System.out.println(str);
        str+=" - changed string";
        System.out.println(str.hashCode());
        System.out.println(str);
        System.out.println(str==str3);
        System.out.println(str.equals(str3));

        String str2=new String("string obj");
        System.out.println(str2);
        String str4=str2;
        System.out.println(str2.equals(str4));

        str2+=" updated string";
        System.out.println(str2);
        System.out.println(str2==str4);
        System.out.println(str2.equals(str4));
    }
}
