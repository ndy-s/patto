package io.github.ndys.patto.exercise.approvers;



import io.github.ndys.patto.exercise.model.Expense;

// Step 3: Implement Concrete Handlers
public class TeamLeadApprover extends AbstractApprover {
    private static final double APPROVAL_LIMIT = 1000.0;

    public TeamLeadApprover(String name) {
        super(name);
        // TODO: Call super constructor with the approver's name
    }

    @Override
    public void processRequest(Expense expense) {
        // TODO: Implement the approval logic for TeamLeadApprover.
        // Check if the expense amount is within this approver's limit.
        // If yes, print an approval message including the approver's name and expense details.
        // If no and there is a next approver, pass the request to the next approver.
        // If no and there is no next approver, print a message indicating the request cannot be approved by anyone.
    }
}