package lab3.fight;

import lab3.Character;
import lab3.enums.FightStatus;
import lab3.exceptions.CustomUncheckedException;

import java.util.ArrayList;
import java.util.HashMap;

public class FightManager {
    ArrayList <Character> participants;
    HashMap<Character, ArrayList<FightImpact>> args = new HashMap<Character, ArrayList<FightImpact>>();
    FightStatus status = FightStatus.NOT_STARTED;

    // Нестатичный вложенный класс
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
        public String toString(){
            return this.leader.getName() + " " + this.power;
        }
    }

    public FightManager(ArrayList <Character> participants) {
        this.participants = participants;

        for (Character obj: participants) {
            this.args.put(obj, new ArrayList<FightImpact>());
        }
    }

    public String start() {
        if (this.status == FightStatus.STARTED) {
            throw new CustomUncheckedException("Драка уже идет");
        }

        this.status = FightStatus.STARTED;
        StringBuilder str = new StringBuilder();

        for (Character obj : participants) {
            str.append(obj.getName()).append(", ");
        }

        return str + "начали драку";
    }

    public String finish() {
        if (this.status == FightStatus.FINISHED) {
            throw new CustomUncheckedException("Драка уже закончена");
        }

        this.status = FightStatus.FINISHED;
        StringBuilder str = new StringBuilder();

        for (Character obj : participants) {
            str.append(obj.getName()).append(", ");
        }

        LeaderPowerDTO leader = this.getLeader();

        return str + "закончили драку. Победил: " +leader.getLeader().getName() + ". Сила: " + leader.getPower();
    }

    public void addImpact(Character obj, FightImpact arg) {
        this.args.get(obj).add(arg);
    }

    public void addParticipant(Character obj) {
        this.participants.add(obj);
        this.args.put(obj, new ArrayList<FightImpact>());
    }

    public ArrayList<Character> getParticipants() {
        return this.participants;
    }

    public LeaderPowerDTO getLeader() {
        int maxPower = 0;
        Character leader = null;

        for (Character obj: this.participants) {
            int power = 0;

            for (FightImpact arg: this.args.get(obj)) {
                power += arg.getPower();
            }

            if (power > maxPower) {
                maxPower = power;
                leader = obj;
            }
        }

        return new LeaderPowerDTO(leader, maxPower);
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.participants.hashCode();
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

        FightManager m = (FightManager) o;

        return this.participants.equals(m.participants);
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Character obj : participants) {
            str.append(obj.getName()).append(", ");
        }

        return "Драка " + str;
    }
}
