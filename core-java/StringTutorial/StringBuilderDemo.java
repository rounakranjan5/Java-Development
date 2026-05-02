class StringBuilderDemo {

    public static void main(String[] args) {
        StringBuilder sb=new StringBuilder("stringbuilder content");
        System.out.println(sb.hashCode());
        System.out.println(sb);

        sb.append(" edited");
        System.out.println(sb);

        System.out.println(sb.hashCode());
    }
}
