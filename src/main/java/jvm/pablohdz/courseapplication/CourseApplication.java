package jvm.pablohdz.courseapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jvm.pablohdz.courseapplication.course.Course;
import jvm.pablohdz.courseapplication.course.CourseService;
import jvm.pablohdz.courseapplication.user.Gender;
import jvm.pablohdz.courseapplication.role.Role;
import jvm.pablohdz.courseapplication.role.RoleName;
import jvm.pablohdz.courseapplication.role.RoleService;
import jvm.pablohdz.courseapplication.user.User;
import jvm.pablohdz.courseapplication.user.UserService;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService,
                          CourseService courseService, RoleService roleService) {
        return args -> {
            userService.saveUser(new User(null, "Peter", "Parker",
                    22, "Spiderman", "admin123", Gender.MALE,
                    "test@test.com", null, null
            ));

            userService.saveUser(new User(null, "Tony", "Stark",
                    42, "IronMan", "admin123", Gender.MALE,
                    "example@test.com", null, null
            ));

            courseService.saveCourse(new Course(null,
                    "Basic Javascript", "Javascript", null
            ));
            courseService.saveCourse(new Course(null,
                    "Basic React JS", "React JS", null
            ));
            courseService.saveCourse(new Course(null,
                    "Basic Node JS", "Node JS", null
            ));

            roleService.saveRole(new Role(RoleName.ROLE_USER));
            roleService.saveRole(new Role(RoleName.ROLE_ADMIN));
            roleService.saveRole(new Role(RoleName.ROLE_MASTER));
        };
    }
}
