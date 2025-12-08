package model;

public class Request {
    private RequestLevel level;
    private String message;

    /**
     * Constructs a new Request.
     *
     * @param level   The severity level of the request.
     * @param message A descriptive message for the request.
     */
    public Request(RequestLevel level, String message) {
        // TODO: Initialize the request's level and message.
    }

    /**
     * Returns the level of this request.
     * @return The RequestLevel.
     */
    public RequestLevel getLevel() {
        // TODO: Return the request level.
        return null; // Placeholder for implementation
    }

    /**
     * Returns the message of this request.
     * @return The request message string.
     */
    public String getMessage() {
        // TODO: Return the request message.
        return null; // Placeholder for implementation
    }
}
