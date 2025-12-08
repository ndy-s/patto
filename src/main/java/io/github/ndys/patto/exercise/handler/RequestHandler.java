package handler;

import model.Request;

public abstract class RequestHandler {
    protected RequestHandler nextHandler;

    /**
     * Sets the next handler in the chain. This handler will be responsible for passing requests
     * it cannot handle to its successor.
     *
     * @param nextHandler The successor handler in the chain.
     */
    public void setNextHandler(RequestHandler nextHandler) {
        // TODO: Implement the logic to set the next handler.
    }

    /**
     * Handles the given request or passes it to the next handler in the chain.
     * Concrete subclasses must implement this method to define their specific handling logic.
     *
     * @param request The request to be handled.
     */
    public abstract void handleRequest(Request request);
}
