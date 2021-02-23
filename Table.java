import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple table object with rows and columns implmented with a map.
 * @author Alex Smith (alsmi14)
 */
public class Table {
    private HashMap<String[],Entry> table;

    /**
     * Constructs an table of Entries with a given string array of entries.
     * @param tab the table to be parsed
     */
    public Table(String[] tab) {
		table = new HashMap<>();
		parseTable(tab);
    }

    /**
     * Gets an entry from the table at a specified row and column.
     * @param row the desired row
     * @param col the desired column
     * @return the Entry object stored at the specified row and column
     */
    public Entry getEntry(String row, String col) { //TODO test
        return table.get(new String[]{row,col});
    }

    /**
     * An inner class defining a shift, reduction, or accepting entry for the Table class.
     */
    public class Entry {
        private boolean isShift;
		private boolean isAccepting;
        private ArrayList<CFRule> rules;
        private int shiftTo;
        
        /**
         * Constructs an entry from a given string.
         * @param entry the entry to be parsed.
         */
        public Entry(String entry) {
            parseEntry(entry);
        }

		/**
		 * Gets the is shift boolean.
		 * @return this.isShift
		 */
        public boolean getIsShift() {
			return isShift;
        }

        /**
         * Gets the rules list.
         * @return this.rules
         */
        public ArrayList<CFRule> getRules() { 
			return rules;
        }

        /**
         * Gets the shift to integer.
         * @return this.shiftTo
         */
        public int getShiftTo() {
			return shiftTo;
        }

        /**
         * Returns a string representation of the Entry.
         */
        public String toString() {
            String entry = "";
			if (isShift) {
				entry = "shift " + shiftTo;
			} else if (isAccepting) {
				entry = "accept";
			} else {
				entry = rules.toString();
			}
			return entry;
        }

        /**
         * An inner class defining a context-free production rule used for the Entry class.
         */
        public class CFRule {
            private String left;
            private String[] right;

            /**
             * Constructs a CFRule from a given string.
             * @param rule the rule to be parsed
             */
            public CFRule(String rule) {
				parseRule(rule);
            }

            /**
             * Gets a copy the left varible.
             * @return this.left
             */
            public String getLeft() { 
				return new String(left);
            }

            /**
             * Gets a copy of the right variable.
             * @return this.right
             */
            public String[] getRight() {
				return right.clone();
            }

            /**
			 * Returns a string representation of the CFRule.
			 */
            public String toString() {
				String temp = left + " ::= ";
				for (String t : right) {
					temp += t + (t.equals(right[right.length-1])? "":" ");
				}
				return temp;
            }

			/**
			 * Parses a rule from a string and initializes the left and right instance variables.
			 * @param rule the rule to be parsed
			 */
			private void parseRule(String rule) {
				String[] r = rule.split("::=");
				left = r[0].trim();
				right = r[1].trim().split("\\s+");
			}
        }

		/**
		 * Parses an entry from a string and initializes the shiftTo or rules variables.
		 * @param entry the rule to be parsed
		 */
        private void parseEntry(String entry) {
			//check if first word is shift
			try {
				shiftTo = Integer.parseInt(entry);
				isShift = true;
			} catch (NumberFormatException e) { //otherwise its a rule(s) so create a rule
				if (entry.matches("accept")) {
					isAccepting = true;
				} else {
					String[] r = entry.split("[|]");
					rules = new ArrayList<>();
					for (String str : r) {
						rules.add(new CFRule(str.trim()));
					}
				}
				isShift = false;
			}
		}
    }

    /**
	 * Parses the entries for a table from a string array that has been correctly format.
	 * @param tab the string array to be parsed into the table
	 * @return a map of the table
	 */
	private void parseTable(String[] tab) {
		for (String s : tab) {
			s = s.trim();

			// get the row
			int start = 0;
			int end = s.indexOf(" ", start);
			String row = s.substring(start, end);

			// get the column
            start = end + 1;
			end = s.indexOf(" ", start);
			String col = s.substring(start, end);

			// get the rule(s)
			start = end + 1;
			String rules = s.substring(start).trim();

			// put them into the map
			addEntry(row, col, rules);
        }
    }

    /**
     * Adds a string entry to the table at a specified row and column.
     * @param row the desired row
     * @param col the desired column
     * @param rules the entry to be added
     */
    private void addEntry(String row, String col, String rules) {
		table.put(new String[]{row, col}, new Entry(rules));
    }
}
