package Item.Exceptions;

public class ExceptionItemAlreadyExists extends RuntimeException {
    public ExceptionItemAlreadyExists(String message) {
        super(message);
    }
}
