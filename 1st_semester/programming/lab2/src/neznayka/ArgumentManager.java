package neznayka;

import neznayka.enums.ArgumentStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class ArgumentManager {
    Character leader = null;
    ArrayList <Character> participants;
    HashMap<Character, ArrayList<Argument>> args = new HashMap<Character, ArrayList<Argument>>();
    ArgumentStatus status = ArgumentStatus.NOT_STARTED;

    public ArgumentManager(ArrayList <Character> participants) {
        this.participants = participants;

        for (Character obj: participants) {
            this.args.put(obj, new ArrayList<Argument>());
        }
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

            return str + "закончили спор. Победил: " + ((Character) this.getLeader()[0]).name;
        } else {
            return "Спор уже закончен";
        }
    }

    public void addArgument(Character obj, Argument arg) {
        this.args.get(obj).add(arg);
    }

    public Object[] getLeader() {
        int maxPower = 0;
        Character leader = null;

        for (Character obj: this.participants) {
            int power = 0;

            for (Argument arg: this.args.get(obj)) {
                power += arg.getPower();
            }

            if (power > maxPower) {
                maxPower = power;
                leader = obj;
            }
        }

        Object[] res = {leader, maxPower};

        return res;
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

        ArgumentManager m = (ArgumentManager) o;

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
