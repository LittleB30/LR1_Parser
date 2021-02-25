import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class defining an LR(1), shift-reduce, parser containing a parse table, a token queue, and a parse stack.
 * @author Alex Smith (alsmi14)
 */
public class LR1Parser {
    private Table parseTable;
    private Queue<QueueNode> tokenQueue;
    private Stack<StackNode> parseStack;

    /**
     * Constructs an LR(1) parser.
     */
    public LR1Parser() {
        String[] tab = {
            "0 n 5", "0 ( 4", "0 E 1", "0 T 2", "0 F 3", 
            "1 + 6", "1 - 6", "1 $ accept", 
            "2 + E ::= T", "2 - E ::= T", "2 * 7", "2 / 7", "2 ) E ::= T", "2 $ E ::= T", 
            "3 + T ::= F", "3 - T ::= F", "3 * T ::= F", "3 / T ::= F", "3 ) T ::= F", "3 $ T ::= F", 
            "4 n 5", "4 ( 4", "4 E 8", "4 T 2", "4 F 3", 
            "5 + F ::= n", "5 - F ::= n", "5 * F ::= n", "5 / F ::= n", "5 ) F ::= n", "5 $ F ::= n", 
            "6 n 5", "6 ( 4", "6 T 9", "6 F 3", 
            "7 n 5", "7 ( 4", "7 F 10", 
            "8 + 6", "8 - 6", "8 ) 11", 
            "9 + E ::= E + T | E ::= E - T", "9 - E ::= E + T | E ::= E - T", "9 * 7", "9 / 7", "9 ) E ::= E + T | E ::= E - T", "9 $ E ::= E + T | E ::= E - T", 
            "10 + T ::= T * F | T ::= T / F", "10 - T ::= T * F | T ::= T / F", "10 * T ::= T * F | T ::= T / F", "10 / T ::= T * F | T ::= T / F", "10 ) T ::= T * F | T ::= T / F", "10 $ T ::= T * F | T ::= T / F", 
            "11 + F ::= ( E )", "11 - F ::= ( E )", "11 * F ::= ( E )", "11 / F ::= ( E )", "11 ) F ::= ( E )", "11 $ F ::= ( E )"
        };
		parseTable = new Table(tab);
    }

    /**
     * Evaluates a string expression as valid or invalid and outputs the result if valid.
     * @param exp the expression to be evaluated
     */
    public void evaluate(String exp) {
        parseStack = initStack();
        tokenQueue = initQueue(exp);
        System.out.println();
        printIteration();

        while (!tokenQueue.isEmpty() && parseIteration()) {
            printIteration();
        }
        System.out.println();

        if (tokenQueue.isEmpty()) {
            System.out.printf("Valid Expression, value = %d\n", parseStack.peek().getValue());
        } else {
            System.out.println("Invalid Expression");
        }
    }

    /**
     * An inner class defining a node used for the token queue of the LR1Parser class.
     */
    private class QueueNode {
        private String token;
        private boolean isNum;
        private int value;

        /**
         * Constructs a queue node with a given token.
         * @param tok a token from one of the columns of the LR(1) parse table
         */
        public QueueNode(String tok) { 
			try {
				value = Integer.parseInt(tok);
                token = "n";
				isNum = true;
			} catch (NumberFormatException e) {
				isNum = false;
                token = tok;
			}
        }

        /**
         * Gets the token variable. 
         * @return this.token
         */
        public String getToken() { 
            return String.valueOf(token);
        }

        /**
         * Gets the isNum variable.
         * @return this.isNum
         */
        public boolean getIsNum() {
            return isNum;
        }

        /**
         * Gets the value variable.
         * @return this.value
         */
        public int getValue() {
			return value;
        }

		/**
		 * Sets the value by a given integer.
		 * @param val the integer to set value to
		 */
		public void setValue(int val) {
			value = val;
			isNum = true;
		}

        /**
		 * Returns a string representation of the QueueNode.
		 */
		@Override
        public String toString() { 
			return (getIsNum())? String.valueOf(getValue()):getToken();
        }
    }
    
	/**
	 * An inner class defining a node used for the parsing stack of the LR1Parser class.
	 */
    private class StackNode extends QueueNode {
        private int curState;

        /**
         * Constructs a stack node with a given token and current state.
         * @param tok a token from one of the columns of the LR(1) parse table
         * @param state the current state of the parser
         */
        public StackNode(String tok, int state) {
            super(tok);
			curState = state;
        }

		/**
		 * Returns a string representation of the StackNode.
		 */
		@Override
        public String toString() {
			String temp = "(" + getToken();
			if (getIsNum()) temp += "=" + getValue();
			temp += ":" + curState + ")";
			return temp;
        }
    }

    /**
     * Creates a stack with a start node.
     * @return the initalized stack
     */
    private Stack<StackNode> initStack() {
        Stack<StackNode> stack = new Stack<>();
        stack.add(new StackNode("", 0));
        return stack;
    }

    /**
     * Tokenizes a string expression and adds it to the token queue.
     * @param exp the expression to be tokenized
     * @return a queue of tokens
     */
	private Queue<QueueNode> initQueue(String exp) {
        String temp = exp + "$";
        Queue<QueueNode> tokens = new LinkedList<>();
        Pattern regex = Pattern.compile("\\d+|\\p{Punct}");
        // \\d+ catches integers
        // \\p{Punct} catches all operator symbols
        Matcher matcher = regex.matcher(temp.replace("\\s+", ""));

        while(matcher.find()){
            tokens.add(new QueueNode(matcher.group()));
        }

        return tokens;
    }

    /**
     * Completes one iteration of the LR(1) parsing process.
     * @return true if successful, false otherwise
     */
    private boolean parseIteration() { 
        boolean isValid = true;

        try {
            Table.Entry entry = nextEntry();
            if (entry.getIsAccepting()) {
                tokenQueue.poll();
            } else if (entry.getIsShift()) {
                QueueNode temp = tokenQueue.poll();
                String token = (temp.getIsNum())? String.valueOf(temp.getValue()):temp.getToken();
                parseStack.add(new StackNode(token, entry.getShiftTo()));
            } else { //otherwise it is a CFRule so perform a reduction
                isValid = tryReduce(entry);
            }
        } catch (NullPointerException e) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * Attempts to perform the reduction specified by the entry.
     * @param entry the entry containing the reduction
     * @return true if successful, false otherwise
     */
    private boolean tryReduce(Table.Entry entry) {
        boolean reduced = false;

        StackNode[] stackArr = new StackNode[entry.getRules().get(0).getRight().length]; // assuming all rules have the same number of tokens
        for (int i = stackArr.length - 1; i >= 0; --i) {
            stackArr[i] = parseStack.pop();
        }
        for (Table.Entry.CFRule rule : entry.getRules()) {
            boolean reduceable = true;
            for (int i = 0; i < stackArr.length; i++) {
                if (!stackArr[i].getToken().equals(rule.getRight()[i])) {
                    reduceable = false;
                }
            }
            if (reduceable) {
                reduce(rule, stackArr);
                reduced = true;
            }
        }

        return reduced;
    }

    /**
     * Performs a reduction.
     * @param rule the reduction rule
     * @param stackArr the array of stack nodes that will be operated on
     */
    private void reduce(Table.Entry.CFRule rule, StackNode[] stackArr) {
        boolean reduced = false;
        int result = 0;
        int shiftState = nextState(rule);

        if (stackArr.length == 1) {
            result = stackArr[0].getValue();
        } else {
            switch (stackArr[1].getToken()) {
                case "+":
                    result = stackArr[0].getValue() + stackArr[2].getValue();
                    reduced = true;
                    break;
                case "-":
                    result = stackArr[0].getValue() - stackArr[2].getValue();
                    reduced = true;
                    break;
                case "*":
                    result = stackArr[0].getValue() * stackArr[2].getValue();
                    reduced = true;
                    break;
                case "/":
                    result = stackArr[0].getValue() / stackArr[2].getValue();
                    reduced = true;
                    break;
            }
            if (!reduced) {
                result = stackArr[1].getValue();
                reduced = true;
            }
        }

        parseStack.add(new StackNode(rule.getLeft(), shiftState));
        parseStack.peek().setValue(result);
    }

    /**
     * Gets the next state to shift to after completing a reduction.
     * @param rule the reduction rule
     * @return the next state
     */
    private int nextState(Table.Entry.CFRule rule) {
        return parseTable.getEntry(String.valueOf(parseStack.peek().curState), rule.getLeft()).getShiftTo();
    }

    /**
     * Gets the next entry from the parse table at (curState, curToken)
     * @return the table entry, null if not found
     */
    private Table.Entry nextEntry() {
        return parseTable.getEntry(String.valueOf(parseStack.peek().curState), tokenQueue.peek().token);
    }

    /**
     * Prints the current state of the LR(1) parser.
     */
    private void printIteration() {
		System.out.println("Stack:" + parseStack.toString() + "    Input Queue:" + tokenQueue.toString());
    }
}
