public class StringBufferDemo {
    public static void main(String[] args) {
        StringBuffer sbf=new StringBuffer("sbf content");

        System.out.println(sbf.hashCode());
        System.out.println(sbf);

        sbf.append(" - new content added");
        System.out.println(sbf);

        System.out.println(sbf.hashCode());
    }
}
