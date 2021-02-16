/**
 * Driver class to run the LR1Parser.
 * @author Alex Smith (alsmi14)
 */
public class LR1 {
    public static void main(String[] args) {
        // if (args.length < 1) {
        //     System.out.println("Usage: LR1 \"Expression\"");
        //     return;
        // }
        // String exp = args[0];
        String exp = "17+20*15";          //Valid Expression, value = 317
        String[] table = {
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
        LR1Parser parser = new LR1Parser(table);
        parser.evaluate(exp);
    }
}
