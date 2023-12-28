package neznayka.enums;

public enum MentalState {
    SANE("Нормальный"),
    INSANE("Сумашедший");

    private final String state;

    MentalState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return this.state;
    }
}
