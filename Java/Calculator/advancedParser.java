
public class advancedParser {
	private char[] operator = {'/','*','+','-'};

    advancedParser() {
    }
    
    public double calculate(String string) {
        return evaluate(string,'+',"0");
    }
    /* First round 
     * 
     * if string = 1+2*3-4/5 + 0
     * leftStr = 1+2*3-4/5
     * left = evalute(1,+,2*3-4/5)
     * op = +
     * rightStr = 0
     * right = 0
     * total = left + right(0)
     * 
     * Second round
     * 
     * leftStr = 1
     * left = 1
     * op = +
     * rightStr = 2*3-4/5
     * right = evalute(2,*,3-4/5)
     * total = right + left(1)
     * 
     * and so on
     */

    private double evaluate(String leftStr, char op, String rightStr) {
        //System.out.println("Evaluating: " + leftStr +  " (" + op + ") " + rightStr);
        double total = 0;
        double left = 0;
        double right = 0;

        int opIndex = findopIndexation(leftStr);
        if( opIndex > 0 && opIndex < leftStr.length()-1 ) {
         
        	left = evaluate(leftStr.substring(0,opIndex),
        			leftStr.charAt(opIndex),
        			leftStr.substring(opIndex+1,leftStr.length()));
        }
        else {
        	 left = Double.parseDouble(leftStr);
        }

        opIndex = findopIndexation(rightStr);
        if( opIndex > 0 && opIndex < rightStr.length()-1 ) {
        	right = evaluate(rightStr.substring(0,opIndex),
        			rightStr.charAt(opIndex),
        			rightStr.substring(opIndex+1,rightStr.length()));
        }
      else {
        	 right = Double.parseDouble(rightStr);
        }

        //System.out.println("Getting result of: " + left + " " + op + " " + right);
        switch(op)
        {
            case '/':
             total = left / right; break;
            case '*':
             total = left * right; break;
            case '+':
             total = left + right; break;
            case '-':
             total = left - right; break;
        }
        //System.out.println("Returning a result of: " + total);
        return total;
    }

    private int findopIndexation(String string)
    {
        int index = -1;
        for(int i = operator.length-1; i >= 0; i--)
        {
            index = string.indexOf(operator[i]);
            if(index >= 0)
             return index;
        }
        return index;
    }
}
