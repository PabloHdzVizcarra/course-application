package jvm.pablohdz.courseapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jvm.pablohdz.courseapplication.user.Gender;
import jvm.pablohdz.courseapplication.user.User;
import jvm.pablohdz.courseapplication.user.UserService;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveUser(new User(null, "Peter", "Parker",
                    22, "Spiderman", "admin123", Gender.MALE,
                    "test@test.com", null));

            userService.saveUser(new User(null, "Tony", "Stark",
                    42, "IronMan", "admin123", Gender.MALE,
                    "example@test.com", null));
        };
    }
}
