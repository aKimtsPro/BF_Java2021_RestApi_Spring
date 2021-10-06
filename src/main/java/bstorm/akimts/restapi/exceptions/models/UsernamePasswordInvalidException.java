package bstorm.akimts.restapi.exceptions.models;

public class UsernamePasswordInvalidException extends RuntimeException{

    public UsernamePasswordInvalidException() {
        super("Username/password invalid");
    }
}
