package web.itmo.lab2.validator;

public class ValidParameter {
    private String message = null;
    private boolean valid = false;

    public ValidParameter(String message, boolean valid) {
        this.valid = valid;
        this.message = message;
    }

    public boolean getValid() {
        return this.valid;
    }

    public String getMessage() {
        return this.message;
    }
}