package common.collections;

import common.exceptions.InvalidParameterException;

import java.io.Serializable;

public enum Difficulty implements Serializable {
    VERY_HARD("Very Hard"),
    IMPOSSIBLE("Impossible"),
    INSANE("Insane"),
    TERRIBLE("Terrible");

    private final String difficulty;

    Difficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static Difficulty parseDifficulty(String input) throws InvalidParameterException {
        if (input.equalsIgnoreCase(VERY_HARD.difficulty)) {
            return Difficulty.VERY_HARD;
        } if (input.equalsIgnoreCase(IMPOSSIBLE.difficulty)) {
            return Difficulty.IMPOSSIBLE;
        } if (input.equalsIgnoreCase(INSANE.difficulty)) {
            return Difficulty.INSANE;
        } if (input.equalsIgnoreCase(TERRIBLE.difficulty)) {
            return Difficulty.TERRIBLE;
        } else {
            throw new InvalidParameterException("Invalid type of difficulty");
        }
    }

    @Override
    public String toString() {
        return this.difficulty;
    }
}
