package neznayka;

import java.util.Objects;

public class Clothes {
    String type;

    public Clothes(String type) {
        this.type = type;
    }

    public String getClothes() {
        return this.type;
    }

    public void setClothes(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.type;
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

        Clothes m = (Clothes) o;

        return this.type.equals(m.type);
    }

    @Override
    public String toString() {
        return this.type;
    }
}
