package web.network;

import web.Calculator;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.DoubleStream;

public class Response {
    public double x;
    public double y;
    public double r;
    public boolean in;
    public String currentTime;
    public float execTime;

    private static final HashSet<Double> VALID_X = new HashSet<>(Arrays.asList(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0));

    public Response(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public static Response handleRequest(Request request) throws IllegalArgumentException {
        double x = request.x;
        double y = request.y;
        double r = request.r;
        Response response = new Response(x, y, r);
        if (!VALID_X.contains(x)) {
            throw new IllegalArgumentException("X is out of range (expected from -2.0 to 2.0 with step 0.5)");
        }
        if (!(y > -3 && y < 5)) {
            throw new IllegalArgumentException("Y is out of range (expected from -3.0 to 5.0)");
        }
        if (!(r > 1 && r < 4)) {
            throw new IllegalArgumentException("R is out of range (expected from 1 to 4)");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        response.currentTime = formatter.format(LocalDateTime.now());
        Instant execStart = Instant.now();

        response.in = Calculator.calculatePoint(x, y, r);

        Instant execEnd = Instant.now();
        response.execTime = (float) (Duration.between(execStart, execEnd).toNanos() / Math.pow(10, 6));
        return response;
    }

    @Override
    public String toString() {
        return "network.Response{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", in=" + in +
                ", currentTime=" + currentTime +
                ", execTime=" + execTime +
                '}';
    }

    public static String httpResponse(Integer length, String msgBody) {
        return """
                HTTP/1.1 400 Bad Request
                Content-Type: application/json
                Content-Length: %d

                %s
                """.formatted(length, msgBody);
    }
}
