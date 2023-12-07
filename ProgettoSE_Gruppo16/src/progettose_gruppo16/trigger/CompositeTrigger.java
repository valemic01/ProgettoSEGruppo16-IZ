/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.trigger;

/**
 *
 * @author raffa
 */
public class CompositeTrigger implements Trigger{
    
    private Trigger left;
    private Trigger right;
    private boolean not;
    private String logicalOp;

    public CompositeTrigger(Trigger left, Trigger right, boolean not, String logicalOp) {
        this.left = left;
        this.right = right;
        this.not = not;
        this.logicalOp = logicalOp;
    }
    
    public void setLeft(Trigger t){
        left = t;
    }
    
    public void setRight(Trigger t){
        right = t;
    }
    
    public void unset(){
        left = null;
        right = null;
    }

    public void setLogicalOp(String s){
        logicalOp = s;
    }
    
    public void setNot(boolean b){
        not = b;
    }
    
    @Override
    public boolean checkCondition(){
        boolean cond;
        if(logicalOp.equalsIgnoreCase("and")){
            cond = left.checkCondition() && right.checkCondition();
        }
        else{
            cond = left.checkCondition() || right.checkCondition();
        }
        if(not) return !cond;
        else return cond;
    }

    @Override
    public String toString() {
        if(not)
            return "(NOT (" + left.toString() + " " + logicalOp.toUpperCase() + "\n" + right.toString() + ")";
        return "(" + left.toString() + " " + logicalOp.toUpperCase() + "\n" + right.toString() + ")";
    }
    
    
    
}
