package client;

import handler.ErrorHandler;
import handler.InfoHandler;
import handler.RequestHandler;
import handler.WarningHandler;
import model.Request;
import model.RequestLevel;

public class Client {
    public static void main(String[] args) {
        // TODO:
        // 1. Instantiate your concrete handler objects (e.g., InfoHandler, WarningHandler, ErrorHandler).
        //    Example: InfoHandler infoHandler = new InfoHandler();
        //
        // 2. Assemble the chain by linking them together using the setNextHandler method.
        //    Example: infoHandler.setNextHandler(warningHandler);
        //             warningHandler.setNextHandler(errorHandler);
        //
        // 3. Create several Request objects with different RequestLevel values and messages.
        //    Example: Request infoRequest = new Request(RequestLevel.INFO, "This is an informational message.");
        //
        // 4. Send these requests to the *first* handler in your assembled chain.
        //    Example: RequestHandler firstHandler = infoHandler; // Assuming infoHandler is the start
        //             firstHandler.handleRequest(infoRequest);
        //
        // 5. Observe the console output to ensure requests are handled by the appropriate handler or passed along correctly.
    }
}
