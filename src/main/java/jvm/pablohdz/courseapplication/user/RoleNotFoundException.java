package jvm.pablohdz.courseapplication.user;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String name) {
        super("The role: " + name + " not found");
    }
}
