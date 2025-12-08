## Java Coding Exercise: Chain of Responsibility Pattern

This exercise will guide you through implementing the Chain of Responsibility design pattern in Java. You are expected to design, implement, and test a system where different types of requests are processed by a sequence of handlers.

---

### Objectives:

Upon successful completion of this exercise, you will be able to:

1.  **Understand the Chain of Responsibility Pattern**: Grasp the core principles and benefits of the Chain of Responsibility pattern, including decoupling senders from receivers.
2.  **Implement the Pattern in Java**: Translate the abstract concepts of the pattern into concrete Java classes and interfaces.
3.  **Design Flexible Request Handling**: Create an extensible system where new request types or handlers can be added without modifying existing handler code.
4.  **Practice Object-Oriented Design**: Apply principles like abstraction, inheritance, and composition to build a robust solution.

---

### What You Should Implement:

You will create a simple "Request Processing System" where requests of varying "levels" are passed along a chain of handlers until one handler decides to process it.

1.  **Define a Request Type:**
    *   Create a class named `Request`.
    *   This class should encapsulate information about a request. It needs at least:
        *   A `RequestLevel` (e.g., an enum like `INFO`, `WARNING`, `ERROR`, or numeric levels like `LEVEL_ONE`, `LEVEL_TWO`, `LEVEL_THREE`) to categorize the request.
        *   A `String message` describing the request.
    *   Provide a constructor to initialize these fields and appropriate getter methods.

2.  **Define the Abstract/Interface Handler:**
    *   Create an abstract class or an interface, for example, `RequestHandler`.
    *   This handler definition must declare:
        *   A method (e.g., `handleRequest(Request request)`) that all concrete handlers will implement to process or pass on a request.
        *   A mechanism (e.g., a method `setNextHandler(RequestHandler nextHandler)`) to establish the successor in the chain.
    *   Consider how the decision to *process* or *pass on* the request will be structured. The abstract handler could define a common structure for this, leaving the specific processing logic to concrete implementations.

3.  **Implement Concrete Handlers:**
    *   Create at least three concrete handler classes (e.g., `InfoHandler`, `WarningHandler`, `ErrorHandler`, or `LevelOneHandler`, `LevelTwoHandler`, `LevelThreeHandler`).
    *   Each concrete handler must extend or implement your `RequestHandler` definition.
    *   Each handler should be responsible for processing requests of a specific `RequestLevel`.
    *   When a concrete handler successfully processes a request, it should indicate this (e.g., by printing a message to the console that includes the handler's name and the request's message).
    *   If a concrete handler *cannot* process a given `Request` (i.e., the request's `RequestLevel` does not match its responsibility), it **must** pass the request to its `nextHandler` in the chain.

4.  **Create a Client Application:**
    *   Develop a `main` method (e.g., within a `Client` or `Application` class).
    *   Inside this `main` method:
        *   Instantiate your concrete handler objects.
        *   Assemble the chain by linking the handlers together using the `setNextHandler` mechanism.
        *   Create several `Request` objects with different `RequestLevel` values and messages.
        *   Send these requests to the *first* handler in your assembled chain.
        *   Observe the output to ensure requests are handled by the appropriate handler or passed along correctly.

---

### Constraints and Guidance:

*   **No Global State**: Handlers should be largely independent of each other, communicating only through the `nextHandler` link. Avoid shared mutable state that is not managed by the chain itself.
*   **Decoupling**: The client code (where `main` is located) should only interact with the *first* handler in the chain. It should not need to know the specific types of concrete handlers or their internal order.
*   **Extensibility**: Your design should make it easy to:
    *   Add new types of requests and corresponding handlers.
    *   Change the order of handlers in the chain.
    *   Introduce or remove handlers from the chain.
    *   All without modifying existing handler code.
*   **Handling Unhandled Requests**: Consider what should happen if a request reaches the end of the chain and no handler has processed it. A common approach is for the *last* handler in the chain, if it cannot handle the request, to simply do nothing, or print a message indicating the request was unhandled.
*   **Clear Output**: Ensure your console output clearly demonstrates which handler processes which request, or if a request is passed on.
*   **No Code Submission**: Your final deliverable should be the instructions and requirements as requested, *not* any Java code. The goal is to set up the exercise clearly without providing the solution.