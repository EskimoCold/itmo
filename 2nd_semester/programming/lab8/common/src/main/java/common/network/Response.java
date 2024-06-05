package common.network;

import lombok.Getter;

import java.io.Serializable;

public class Response implements Serializable {
    @Getter
    private Object obj;
    @Getter
    private User user;

    public Response(User user, Object obj) {
        this.user = user;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return this.obj.toString();
    }
}
