package otus.class5;

public class HealthResponse {
    private final Status status;

    public HealthResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        OK,
    }
}

