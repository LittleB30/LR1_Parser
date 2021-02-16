import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple table object with rows and columns implmented with a map.
 * @author Alex Smith (alsmi14)
 */
public class Table {
    private HashMap<String[],Entry> table;

    /**************
      Constructors
     **************/

    /**
     * 
     * @param t
     */
    public Table(String[] t) { //TODO implement and comment

    }

    /****************
      Public Methods
     ****************/

    /**
     * 
     * @param row
     * @param col
     * @return
     */
    public Entry getEntry(String row, String col) { //TODO implement and comment

    }

    /**
     * 
     */
    public class Entry {
        private boolean isShift;
        private ArrayList<CFRule> rules;
        private int shiftTo;

        /**************
          Constructors
         **************/

        /**
         * 
         * @param shift
         */
        public Entry(int shift) { //TODO implement and comment

        }
        
        /**
         * 
         * @param rules
         */
        public Entry(String rules) { //TODO implement and comment
            
        }

        /****************
          Public Methods
         ****************/

        /**
         * 
         * @return
         */
        public boolean getIsShift() { //TODO implement and comment

        }

        /**
         * 
         * @return
         */
        public ArrayList<CFRule> getRules() { //TODO implement and comment

        }

        /**
         * 
         * @return
         */
        public int getShiftTo() { //TODO implement and comment

        }

        /**
         * 
         */
        public String toString() { //TODO implement and comment
            
        }

        /**
         * 
         */
        public class CFRule {
            String left;
            String[] right;

            /**************
              Constructors
             **************/

            /**
             * 
             * @param l
             * @param r
             */
            public CFRule(String l, String[] r) { //TODO implement and comment

            }

            /****************
              Public Methods
             ****************/

            /**
             * 
             * @return
             */
            public String getLeft() { //TODO implement and comment

            }

            /**
             * 
             * @return
             */
            public String[] getRight() { //TODO implement and comment

            }

            /**
             * 
             */
            public String toString() { //TODO implement and comment

            }
        }
    }

    /*****************
      Private Methods
     *****************/

    /**
     * 
     * @param t
     */
    private void parseTable(String[] t) { //TODO implement and comment

    }

    /**
     * 
     * @param row
     * @param col
     * @param shift
     */
    private void addEntry(String row, String col, int shift) { //TODO implement and comment

    }

    /**
     * 
     * @param row
     * @param col
     * @param rules
     */
    private void addEntry(String row, String col, String rules) { //TODO implement and comment

    }
}
