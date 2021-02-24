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
        LR1Parser parser = new LR1Parser();
        parser.evaluate(exp);
    }
}
