package lab3.interfaces;

public interface Talker {
    default String talk(String phrase) {
        return null;
    }
}
