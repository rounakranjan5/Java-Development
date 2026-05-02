class LocalVar {

    static void fun(){
        int b=245;
        System.out.println("value of b in fun : "+b);
    }

    public static void main(String[] args) {
        int a=20;
        {
            int b=22;
            fun();
            System.out.println("value of b : "+b);
        }
//        System.out.println("value of b : "+b);//error

        LocalVar lv=new LocalVar();
        lv.fun();
    }
}
