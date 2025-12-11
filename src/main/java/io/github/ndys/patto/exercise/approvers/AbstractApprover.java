package io.github.ndys.patto.exercise.approvers;



import io.github.ndys.patto.exercise.model.Expense;

// Step 2: Define the Handler Abstract Class
public abstract class AbstractApprover {
    protected AbstractApprover nextApprover;
    protected String name;

    public AbstractApprover(String name) {
        // TODO: Implement constructor to initialize the approver's name
    }

    public void setNextApprover(AbstractApprover nextApprover) {
        // TODO: Implement method to set the next approver in the chain
    }

    public abstract void processRequest(Expense expense);
}