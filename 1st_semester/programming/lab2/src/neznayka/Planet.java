package neznayka;

public enum Planet {
    EARTH("Земля"),
    MARS("Марс");

    private final String planet;

    Planet(String planet) {
        this.planet = planet;
    }

    @Override
    public String toString() {
        return this.planet;
    }
}
