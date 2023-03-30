package org.example;

import org.example.configuration.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User user = new User("James", "Brown", (byte) 25);
        user.setId(3L);
        String a = communication.saveUser(user);
        //System.out.println(a);

        user.setName("Thomas");
        user.setLastName("Shelby");
        String b = communication.updateUser(user);
        //System.out.println(b);

        String c = communication.deleteUser(3L);
        System.out.println(a + b + c);

    }
}
