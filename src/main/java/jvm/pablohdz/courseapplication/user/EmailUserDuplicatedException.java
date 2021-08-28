package jvm.pablohdz.courseapplication.user;

public class EmailUserDuplicatedException extends RuntimeException {
    public EmailUserDuplicatedException(String email) {
        super("The email: " + email + " already exists in database, the email must be unique");
    }
}
