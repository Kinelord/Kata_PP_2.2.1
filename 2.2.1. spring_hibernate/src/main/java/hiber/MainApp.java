package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("User1",
                "Lastname1", "user1@mail.ru", new Car("Ferrari", 202)));
        userService.add(new User("User2",
                "Lastname2", "user2@mail.ru", new Car("Lada Classic", 0)));
        userService.add(new User("User3",
                "Lastname3", "user3@mail.ru", new Car("Porch", 11)));
        userService.add(new User("User4",
                "Lastname4", "user4@mail.ru", new Car("Ford", 303)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            printUser(user);
        }
        User user = userService.getUser("Lada Classic", 0);
        printUser(user);
        user = userService.getUser("Porch", 11);
        printUser(user);
        context.close();
    }

    private static void printUser(User user) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Car = " + user.getCar());
        System.out.println();
    }
}
