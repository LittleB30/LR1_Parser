import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * A class defining an LR(1), shift-reduce, parser containing a parse table, a token queue, and a parse stack.
 * @author Alex Smith (alsmi14)
 */
public class LR1Parser {
    private Table parseTable;
    private Queue<QueueNode> tokenQueue;
    private Stack<StackNode> parseStack;

    /**
     * Constructs an LR(1) parser with a given LR(1) parse table.
     * @param tab a string array specifying an LR(1) parse table in the form "row column entry" where entry is either a row to shift to or a CFRule.
     */
    public LR1Parser(String[] tab) {
		parseTable = new Table(tab);
		tokenQueue = new LinkedList<>(); //use anonomous class here if toString does not work
		parseStack = new Stack<>();
    }

    /**
     * 
     * @param exp
     */
    public void evaluate(String exp) { //TODO implement and comment

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
			token = tok;
			try {
				value = Integer.parseInt(tok);
				isNum = true;
			} catch (NumberFormatException e) {
				isNum = false;
			}
        }

        /**
         * Gets the token variable. 
         * @return this.token
         */
        public String getToken() { 
            return new String(token);
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
		 * Sets the token by a given string.
		 * @param tok the string to set token to
		 */
		public void setToken(String tok) {
			token = tok;
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
			return getToken();
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
		 * Gets the current state variable.
		 * @return this.curState
		 */
        public int getCurState() {
			return curState;
        }

		/**
		 * Sets the current state by a given integer.
		 * @param state an integer to set the current state to
		 */
		public void setCurState(int state) {
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
     * Prints the current state of the LR(1) parser.
     */
    private void printIteration() { //TODO test, may not work fully
		System.out.println("Stack:" + parseStack.toString() + "    Input Queue:" + tokenQueue.toString());
    }
}
