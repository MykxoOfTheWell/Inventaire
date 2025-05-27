package Item.Exceptions;

public class ExceptionItemAlreadyExists extends RuntimeException {
    public ExceptionItemAlreadyExists(int ID) {
        super("Item "+ID+" est déjà dans la base de données");
    }
}
