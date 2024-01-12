package lab3.fight;

import lab3.Character;

public class LeaderPowerDTO {
    private final Character character;
    private final Integer leader;

    public LeaderPowerDTO(Character character, Integer leader) {
        this.character = character;
        this.leader = leader;
    }

    public Character getCharacter() {
        return character;
    }

    public Integer getLeader() {
        return leader;
    }

    @Override
    public String toString() {
        return this.leader + this.character.toString();
    }
}