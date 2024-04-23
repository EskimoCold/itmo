package common.network;

import lombok.Getter;
import java.io.Serializable;

public class Response implements Serializable {
    @Getter
    private Object obj;

    public Response(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return this.obj.toString();
    }
}
