package jvm.pablohdz.courseapplication.course;


import java.util.List;

public interface CourseService {

    /**
     * Stores a {@link Course} in the implemented persistence service
     *
     * @param course The course to persist
     * @return The course stored in the persistence service
     */
    Course saveCourse(Course course);

    /**
     * Get all data stored in the data persistence service
     *
     * @return A list of {@link Course}
     */
    List<Course> getAllCourses();
}
