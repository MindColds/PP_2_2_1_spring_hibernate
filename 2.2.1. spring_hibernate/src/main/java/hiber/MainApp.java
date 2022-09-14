package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      userService.addUser(new User("Александр", "Петров", "apetrov@gmail.com", new Car("BMWшечка", 321)));
      userService.addUser(new User("Сергей", "Брилев", "s.briliov@gmail.com", new Car("Победа", 441)));
      userService.addUser(new User("Тимофей", "Скрыпник", "somestrang@mail.ru", new Car("Т-34", 27)));


      List<User> users = userService.showAllUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println(user.getCar());
      }
      System.out.println("-------------------------------------------------");
      User user = userService.getUserCar("Т-34", 27);
      System.out.format("User who has %s: ", user.getCar() +"\n" + user.getFirstName() +"\n" + user.getLastName() +"\n" + user.getEmail());


         context.close();
      }

}
