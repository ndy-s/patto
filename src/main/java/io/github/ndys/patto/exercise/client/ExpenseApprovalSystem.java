package io.github.ndys.patto.exercise.client;



import io.github.ndys.patto.exercise.model.Expense;
import io.github.ndys.patto.exercise.approvers.AbstractApprover;
import io.github.ndys.patto.exercise.approvers.TeamLeadApprover;
import io.github.ndys.patto.exercise.approvers.ManagerApprover;
import io.github.ndys.patto.exercise.approvers.DirectorApprover;

// Step 4 & 5: Construct the Chain and Test the System
public class ExpenseApprovalSystem {

    public static void main(String[] args) {
        // TODO: Construct the chain of approvers.
        // 1. Create instances of TeamLeadApprover, ManagerApprover, and DirectorApprover, providing unique names.
        AbstractApprover teamLead = null; // Example: new TeamLeadApprover("Alice - Team Lead");
        AbstractApprover manager = null;  // Example: new ManagerApprover("Bob - Manager");
        AbstractApprover director = null; // Example: new DirectorApprover("Charlie - Director");

        // 2. Link the approvers to form the chain: TeamLead -> Manager -> Director.
        // Example: teamLead.setNextApprover(manager);
        // Example: manager.setNextApprover(director);

        // TODO: Create several Expense objects with varying amounts and descriptions.
        System.out.println("\n--- Creating Expenses ---");
        Expense expense1 = null; // Example: new Expense(500, "Office Supplies");
        Expense expense2 = null; // Example: new Expense(2500, "Software License");
        Expense expense3 = null; // Example: new Expense(8000, "Project Equipment");
        Expense expense4 = null; // Example: new Expense(20000, "New Server Rack");
        Expense expense5 = null; // Example: new Expense(15000, "Consulting Fees"); (Exact limit for Director)

        System.out.println("\n--- Processing Expenses ---");

        // TODO: Pass each expense through the chain starting with the first approver (TeamLead).
        // Observe how each expense is processed by the appropriate approver.
        // Example: teamLead.processRequest(expense1);
        // Example: teamLead.processRequest(expense2);
        // Example: teamLead.processRequest(expense3);
        // Example: teamLead.processRequest(expense4);
        // Example: teamLead.processRequest(expense5);
    }
}