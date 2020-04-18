package kz.itstep.exception;

public class NullSessionAttributeException extends Exception {
    private String attribute;

    public NullSessionAttributeException(String message, String attribute) {
        super(message);
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
