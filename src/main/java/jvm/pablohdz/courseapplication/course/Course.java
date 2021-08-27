package jvm.pablohdz.courseapplication.course;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Course {
    @Id
    private Long id;
}