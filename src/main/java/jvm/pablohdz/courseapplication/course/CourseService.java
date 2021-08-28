package jvm.pablohdz.courseapplication.course;

public interface CourseService {

    /**
     * Stores a {@link Course} in the implemented persistence service
     *
     * @param course The course to persist
     * @return The course stored in the persistence service
     */
    Course saveCourse(Course course);
}
