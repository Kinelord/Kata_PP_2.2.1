package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
       // В комментариях к задаче было сообщение, о добавлении слоя Dao и Service для Car

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        carService.add(new Car("Ferrari", 202));
        carService.add(new Car("Lada Classic", 0));
        carService.add(new Car("Porch", 11));
        carService.add(new Car("Ford", 303));

        User user1 = userService.getUser(1L);
        user1.setCar(carService.getCar(1L));
        userService.update(user1);
        User user2 = userService.getUser(2L);
        user2.setCar(carService.getCar(2L));
        userService.update(user2);
        User user3 = userService.getUser(3L);
        user3.setCar(carService.getCar(3L));
        userService.update(user3);
        User user4 = userService.getUser(4L);
        user4.setCar(carService.getCar(4L));
        userService.update(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        Car car = carService.getCar(3L);
        User user = userService.getUser(car.getModel(), car.getSeries());
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Car = " + user.getCar());
        System.out.println();
        context.close();
    }
}
