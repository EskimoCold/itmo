package neznayka;

public interface Talker {
    default String think(String phrase) {
        return "Говорит: " + phrase;
    }
}
