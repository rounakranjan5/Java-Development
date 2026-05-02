package LooseCoupling;

public class LearnSpring implements Learn{
    @Override
    public void learning() {
        System.out.println("Spring Learning Started ...");
    }

    public LearnSpring() {
        System.out.println("Spring Learning Constructor....");
    }
}
