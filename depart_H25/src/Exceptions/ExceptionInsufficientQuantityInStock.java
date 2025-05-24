package Item.Exceptions;

public class ExceptionInsufficientQuantityInStock extends RuntimeException {
    public ExceptionInsufficientQuantityInStock(String message) {
        super(message);
    }
}
