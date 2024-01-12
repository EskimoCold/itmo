package lab3.interfaces;

public interface CanBeThrown {
    default String thrown() {
        return "Полетела " + this.toString();
    }
}
