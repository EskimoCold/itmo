package lab3.interfaces;

public interface Thinker {
    default String think(String thought) {
        return null;
    }
}
