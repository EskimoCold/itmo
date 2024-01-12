package lab3.fight;

import lab3.Character;
import lab3.items.Item;

import java.util.Objects;

public class LeaderPowerDTO {
    private final Character leader;
    private final Integer power;

    public LeaderPowerDTO(Character leader, Integer power) {
        this.leader = leader;
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public Character getLeader() {
        return leader;
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.leader + this.power;
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()){
            return false;
        }

        LeaderPowerDTO m = (LeaderPowerDTO) o;

        return this.leader.equals(m.leader) && this.power.equals(m.power);
    }

    @Override
    public String toString(){
        return this.leader.getName() + " " + this.power;
    }
}