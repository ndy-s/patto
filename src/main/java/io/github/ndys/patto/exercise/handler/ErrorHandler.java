package handler;

import model.Request;
import model.RequestLevel;

public class ErrorHandler extends RequestHandler {

    public ErrorHandler() {
        // TODO: Optional: Constructor for handler-specific initialization if needed.
    }

    @Override
    public void handleRequest(Request request) {
        // TODO:
        // 1. Check if this handler is responsible for processing the given Request based on its RequestLevel.
        //    (e.g., if request.getLevel() == RequestLevel.ERROR)
        // 2. If responsible:
        //    - "Process" the request (e.g., print a message indicating it was handled by ErrorHandler).
        // 3. If not responsible:
        //    - Check if there is a 'nextHandler' in the chain.
        //    - If yes, pass the request to 'nextHandler.handleRequest(request)'.
        //    - If no 'nextHandler' (end of the chain), print a message indicating the request was unhandled.
    }
}
