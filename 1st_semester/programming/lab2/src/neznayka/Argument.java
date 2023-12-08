package neznayka;

import java.util.ArrayList;

public class Argument {
    Character leader = null;
    ArrayList <Character> participants;
    ArgumentStatus status = ArgumentStatus.NOT_STARTED;

    public Argument(ArrayList <Character> participants) {
        this.participants = participants;
    }

    public String start() {
        if (status != ArgumentStatus.STARTED) {
            status = ArgumentStatus.STARTED;
            String str = "";
            for (Character obj : participants) {
                str += obj.name + ", ";
            }
            return str + "начали спор";
        } else {
            return "Спор уже идет";
        }
    }

    public String finish() {
        if (status != ArgumentStatus.FINISHED) {
            status = ArgumentStatus.FINISHED;
            String str = "";
            for (Character obj : participants) {
                str += obj.name + ", ";
            }
            return str + "закончили спор. Победил: " + this.getLeader().name;
        } else {
            return "Спор уже закончен";
        }
    }

    public Character getLeader() {
        return leader;
    }

    public void setLeader(Character leader) {
        this.leader = leader;
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.participants.hashCode();
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this.getClass() != o.getClass() || o == null){
            return false;
        }

        Argument m = (Argument) o;

        return this.participants.equals(m.participants);
    }

    @Override
    public String toString(){
        String str = "";
        for (Character obj : participants) {
            str += obj.name + ", ";
        }

        return "Спор " + str;
    }
}
