package neznayka;

public interface Thinker {
    default String think(String thought) {
        return "Подумал: " + thought;
    }
}
