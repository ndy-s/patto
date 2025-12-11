Implement an expense approval system in Java using the Chain of Responsibility design pattern. The system should process expense requests through a chain of approvers, where each approver has a specific approval limit.

**Steps:**
1.  **Define the Request**: Create a simple `class Expense` that encapsulates an `amount` (e.g., `double`) and a `description` (e.g., `String`).
2.  **Define the Handler Interface/Abstract Class**: Create an `interface Approver` or an `abstract class AbstractApprover`. This should define a method `setNextApprover(Approver next)` to build the chain and an abstract method `processRequest(Expense expense)` that concrete approvers will implement.
3.  **Implement Concrete Handlers**: Create at least three concrete approver classes that extend `AbstractApprover` (or implement `Approver`):
    *   `TeamLeadApprover`: Can approve expenses up to $1000.
    *   `ManagerApprover`: Can approve expenses up to $5000.
    *   `DirectorApprover`: Can approve expenses up to $15000.
    Each concrete approver's `processRequest` method should check if it can handle the expense amount. If it can, it should print a message indicating approval. If not, it must pass the request to its `nextApprover`. If there is no `nextApprover` and the current approver cannot handle it, print a message indicating the request cannot be approved by anyone in the current chain.
4.  **Construct the Chain**: In a `main` method or a dedicated `Client` class, instantiate the concrete approvers and link them together to form a chain (e.g., TeamLead -> Manager -> Director).
5.  **Test the System**: Create several `Expense` objects with varying amounts (e.g., $500, $2500, $8000, $20000) and pass them through the initial approver in your constructed chain to observe how they are processed.