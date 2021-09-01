package jvm.pablohdz.courseapplication.user;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import jvm.pablohdz.courseapplication.course.Course;
import jvm.pablohdz.courseapplication.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
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
    @Column(name = "user_username", unique = true)
    private String username;

    @NotNull
    @Column(name = "user_password")
    private String password;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Email
    @Column(name = "user_email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "rel_user_role",
            joinColumns = @JoinColumn(name = "FK_USER", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_ROLE", nullable = false))
    private Collection<Role> roles = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_user_course",
            joinColumns = @JoinColumn(name = "FK_USER", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_COURSE", nullable = false))
    private Collection<Course> courses = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", courses=" + courses +
                '}';
    }
}


