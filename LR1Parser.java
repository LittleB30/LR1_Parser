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

    /**************
      Constructors
     **************/

    /**
     * 
     * @param t
     */
    public LR1Parser(String[] t) { //TODO implement and comment

    }

    /****************
      Public Methods
     ****************/

    /**
     * 
     * @param exp
     */
    public void evaluate(String exp) { //TODO implement and comment

    }
    
    private class QueueNode {
        String token;
        boolean isNum;
        int value;

        /**************
          Constructors
         **************/

        /**
         * 
         * @param tok
         */
        public QueueNode(String tok) { //TODO implement and comment

        }

        /****************
          Public Methods
         ****************/

        /**
         * 
         * @return
         */
        public String getToken() { //TODO implement and comment
            
        }

        /**
         * 
         * @return
         */
        public boolean getIsNum() { //TODO implement and comment
            
        }

        /**
         * 
         * @return
         */
        public int getValue() { //TODO implement and comment

        }

        /**
         * 
         */
        public String toString() { //TODO implement and comment

        }
    }
    
    private class StackNode extends QueueNode {
        private int curState;

        /**************
          Constructors
         **************/

        /**
         * 
         * @param tok
         * @param state
         */
        public StackNode(String tok, int state) { //TODO implement and comment
            super(tok);
        }

        /****************
          Public Methods
         ****************/

        public int getCurState() { //TODO implement and comment

        }

        public String toString() { //TODO implement and comment

        }
    }

    /*****************
      Private Methods
     *****************/

    /**
     * 
     */
    private void printIteration() { //TODO implement and comment

    }
}
