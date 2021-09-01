package jvm.pablohdz.courseapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jvm.pablohdz.courseapplication.course.CourseNotFoundException;
import jvm.pablohdz.courseapplication.user.EmailUserDuplicatedException;
import jvm.pablohdz.courseapplication.user.RoleNotFoundException;
import jvm.pablohdz.courseapplication.user.UserNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailUserDuplicatedException.class)
    protected ResponseEntity<Object> handleEmailDuplicated(EmailUserDuplicatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    protected ResponseEntity<Object> handleCourseNotFound(CourseNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    protected ResponseEntity<Object> handleRoleNotFound(RoleNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
