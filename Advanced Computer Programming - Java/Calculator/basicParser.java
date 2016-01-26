
public class basicParser {
	private char[] operator = {'+', '-', '*', '/'};
	private double result=-1;
	private int OP;
	char o;
	public basicParser() {
	}
	
	
	/* First round
	 * 
	 * if string = 1+2+3-5*3
	 * result = 1
	 * o = +
	 * substring = 2+3-5*3
	 * 
	 * Second round
	 * left = 2
	 * o = +
	 * result = 1 + 2
	 * substring = 3-5*3
	 * 
	 * and so on....
	 */
	
	public double calculate(String string){
		int opIndex;
		String substring = null;
		double left = 0;
		
		
		opIndex = findOpIndex(string); 
		if(opIndex > 0 && opIndex < string.length()-1)
			left = Double.parseDouble(string.substring(0,opIndex)); 
		else
			left = Double.parseDouble(string);
		if(result == -1){
			result =  left;
		}
		else {
			switch (o) {
			case '+':
				result = result + left; 
				break;
			case '-':
				result = result - left;
				break;
			case '*':
				result = result * left;
				break;
			case '/':
				result = result / left;
				break;
			}
		}
		o = operator[OP];
		if(opIndex > 0 && opIndex < string.length()-1){
			substring = string.substring(opIndex+1);
			calculate(substring);
		}
		return result;
	}
	
	//find each operator index and return
	public int findOpIndex(String str){
		int index = -1;
		for(int i = 0;i<str.length();i++){
			switch (str.charAt(i)) {
			case '+':
				OP = 0;
				index = i;
				return index;
			case '-':
				OP = 1;
				index = i;
				return index;
			case '*':
				OP = 2;
				index = i;
				return index;
			case '/':
				OP = 3;
				index = i;
				return index;
			}
		}
		return index;
	}
	
}
