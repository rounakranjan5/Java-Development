package ListInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListBasics {

    public static void main(String[] args) {
        List<Integer> li=new ArrayList<>();

        System.out.println(li.getClass().getName());

        li.add(2);
        li.add(3);
        li.add(33);
        li.add(46);
        li.add(463);
        li.add(1,99);
        li.set(4,456);
        li.remove(4);
        li.remove(Integer.valueOf(463));

        Collections.sort(li);

        System.out.println("----");
        for (int ele : li){
            System.out.println(ele);
        }
        System.out.println("----");

        System.out.println(li.get(3));
        System.out.println(li.contains(2));//true
        System.out.println(li.contains(12));//false
        System.out.println(li.contains("ff"));//false

        System.out.println(li.size());
    }

}
