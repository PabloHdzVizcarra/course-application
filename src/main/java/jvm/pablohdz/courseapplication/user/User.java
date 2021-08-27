package jvm.pablohdz.courseapplication.user;


import com.sun.istack.NotNull;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import jvm.pablohdz.courseapplication.course.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "user")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "user_name")
    private String name;

    @NotNull
    @Column(name = "user_lastname")
    private String lastname;

    @NotNull
    @Column(name = "user_age")
    private int age;

    @NotNull
    @Column(name = "user_username")
    private String username;

    @NotNull
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Email
    @Column(name = "user_email")
    private String email;

    @ManyToMany
    private Collection<Course> courses = new ArrayList<>();

}


