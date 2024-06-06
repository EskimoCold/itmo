package client.data;

import common.collections.Coordinates;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import lombok.Getter;

public class DisplayCoordinates extends Coordinates {
    @Getter
    private final Long x;
    @Getter
    private final Long y;

    @Getter
    private transient LongProperty xProperty = new SimpleLongProperty();
    @Getter
    private transient LongProperty yProperty = new SimpleLongProperty();

    public DisplayCoordinates(Coordinates coordinates){
        this.x = coordinates.getX();
        this.y = coordinates.getY();

        xProperty.bindBidirectional(new SimpleLongProperty(x));
        yProperty.bindBidirectional(new SimpleLongProperty(y));

    }

    public void bindProperties(){
        xProperty.bindBidirectional(new SimpleLongProperty(x));
        yProperty.bindBidirectional(new SimpleLongProperty(y));
    }
}
