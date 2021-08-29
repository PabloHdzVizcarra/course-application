package jvm.pablohdz.courseapplication.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    /**
     * Gets a course from the database by name
     *
     * @param courseName The name to find course
     * @return A course
     */
    Course getCourseByName(String courseName);
}
