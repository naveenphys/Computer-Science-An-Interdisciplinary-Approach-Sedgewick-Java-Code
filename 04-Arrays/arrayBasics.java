/* Program: arrayBasic
 * Description: Shows basic array processing.
 * - Zero based indexing.
 * - Array lenght is fixed on creation.
 * - Default initialization of numeric types to zero.
 * - Default initialization of boolean types to false.
 * - Default initialization of String types to null.
 * - Memory representation: a[] is a pointer to array location. a[i] returns
 *   value at location (a[] + i).
 * - Memory allocation is done using new.
 * - Dont use new with primitive types as thier size is known.
 * - Bounds checking: ArrayIndexOutOfBoundsException.
 * - Setting array values at compile time (declare, create and initialize).
 * - For example. double [] a = {1.0, 2.0, 3.0};
 *   
 */
public class arrayBasics {
    public static void main(String[] args) {
        // Array size.
        final int NMAX = 8;

        // Declare an array,
        double [] a;

        // Create the array. Java automatically initializes numeric types to
        // zero, booleans to false, and String to null.
        a = new double [NMAX];

        // Initialize the array.
        for (int i = 0; i < a.length; i++)
        {
            a[i] = Math.random();
        }

        // Print the array.
        System.out.println();
        for (int i = 0; i < a.length; i++)
        {
            System.out.println(i + "\t" + a[i]);
        }

        // Find the maximum of the array.
        double max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            max = Math.max(a[i], max);
        }
        System.out.println("Max value = " + max);

        // Average of the array.
        double avg = a[0];
        for (int i = 1; i < a.length; i++)
        {
            avg += a[i];
        }
        avg /= a.length;
        System.out.println("Average value = " + avg);

        // Copy to another array.
        double [] b = new double [a.length];
        
        System.arraycopy(a, 0, b, 0, a.length);

        // Print the array.
        System.out.println();
        for (int i = 0; i < a.length; i++)
        {
            System.out.println(i + "\t" + b[i] + "\t" + a[i]);
        }

        // Reverse the array.
        int imax = (a.length % 2 == 0)? a.length/2 : a.length/2 -1;
        int iend = a.length - 1;
        for (int i = 0; i < imax; i++)
        {
            double temp = a[i];
            a[i] = a[iend - i];
            a[iend - i] = temp;
        }

        // Print the array.
        System.out.println();
        for (int i = 0; i < a.length; i++)
        {
            System.out.println(i + "\t" + a[i]);
        }



    }
    
}
