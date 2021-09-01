package jvm.pablohdz.courseapplication.course;

/**
 * The course  does not exist in the persistence service
 */
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String courseName) {
        super("The course: " + courseName + " is not exist");
    }
}
