package org.example;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);

        User user =context.getBean(User.class);
        System.out.println(user.getName()+" is placing an order");

        OrderService order=context.getBean(OrderService.class);
        order.placeOrder();

    }
}
