package progettose_gruppo16.trigger;

/**
 * Represents a composite trigger that combines two triggers with a logical operator.
 * The composite trigger can be negated, and the logical operator can be set dynamically.
 * Implements the Trigger interface.
 *
 * This class allows users to create complex triggers by combining two triggers with
 * logical operators such as AND and OR.
 */
public class CompositeTrigger implements Trigger{

    private Trigger left;
    private Trigger right;
    private boolean not;
    private String logicalOp;

    /**
     * Constructs a CompositeTrigger with the specified left and right triggers,
     * negation status, and logical operator.
     *
     * @param left The left trigger to be combined.
     * @param right The right trigger to be combined.
     * @param not True if the trigger should be negated, false otherwise.
     * @param logicalOp The logical operator (AND or OR) to combine the triggers.
     */
    public CompositeTrigger(Trigger left, Trigger right, boolean not, String logicalOp){
        this.left = left;
        this.right = right;
        this.not = not;
        this.logicalOp = logicalOp;
    }

    /**
     * Checks the condition of the composite trigger based on the logical operator
     * and negation status.
     *
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition(){
        boolean cond;
        if(logicalOp.equalsIgnoreCase("and")){
            cond = left.checkCondition() && right.checkCondition();
        }
        else{
            cond = left.checkCondition() || right.checkCondition();
        }
        if(not) 
            return !cond;
        return cond;
    }

    /**
     * Returns a string representation of the composite trigger, including the
     * logical operator and negation status.
     *
     * @return The string representation of the composite trigger.
     */
    @Override
    public String toString(){
        if (not)
            return "(NOT (" + left.toString() + " " + logicalOp.toUpperCase() + "\n" + right.toString() + ")";
        return "(" + left.toString() + " " + logicalOp.toUpperCase() + "\n" + right.toString() + ")";
    }
}