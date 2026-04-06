/* Program: typeConversion
 * Description: Demonstration of type conversion.
 * Implicit type conversion: We can use an int value where a double is 
 * expected. For example, 11 * 0.25 = 2.75, because both operands of '*' must
 * be doubles, 11 is promoted to double. It is called 'automatic promotion' or
 * 'coercion'. It is okay because the intent is clear, and it can be done with
 * no loss of information. An error occurs, if we attempt to write
 * int i = 11 * 0.25
 * error: incompatible types: possible lossy conversion from double to int
 * Note: In C and C++, this does not lead to error.
 * 
 * Explicit cast: Using some built-in type conversion methods for primitive 
 * types. Use case is when we are aware that we might lose information. We
 * make the intention clear using a cast operator. For eg.
 * (int) 1.0
 * (double) 1
 * 
 * Note: Casting has higher precdence than arithmetic operations.
 * 
 * Explicit type conversion: It is done using methods which take an argument
 * of one kind and return result of another kind. For eg.
 * Integer.parseInt(String) -> int
 * Double.parseDouble(String) -> double
 * Math.round(double) -> long
 * 
 */
public class typeConversion {
    public static void main(String[] args) {
        // Example of implicit type conversion.
        double a = 11 * 1.5;
        System.out.println("11 * 1.5 = " + a);
        double b = (1 + 2 + 3 + 4) / 4.0;
        System.out.println("(1 + 2 + 3 + 4) / 4.0 = " + b);
        
        // Explicit cast
        System.out.println("(int) 1.0 = " + (int) 1.0);
        System.out.println("(double) 1 = " + (double) 1);
        
        // Explicit type conversion.
        String c = "1";
        int d = Integer.parseInt(c);
        double e = Double.parseDouble(c);
        System.out.println("d * e = " + (d + e));

    }
    
}
