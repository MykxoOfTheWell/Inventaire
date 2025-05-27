package Item.Exceptions;

public class ExceptionInsufficientQuantityInStock extends RuntimeException {
    public ExceptionInsufficientQuantityInStock(int quantity) {
        super("Stock insuffisant pour enlever "+quantity+" unité(s)");
    }
}
