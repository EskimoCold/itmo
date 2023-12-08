package neznayka;

public interface Actor {
    default String performAction(String action) {
        return "Сделал: " + action;
    }
}
