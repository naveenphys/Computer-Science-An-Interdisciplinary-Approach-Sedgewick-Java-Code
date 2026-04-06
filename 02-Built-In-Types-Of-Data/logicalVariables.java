/* Program: logicalVariables
 * Description: Demonstration of De Morgan's laws, and the distributive
 *              properties of OR and AND.
 */
public class logicalVariables {
    public static void main(String[] args) {

        boolean[] a = new boolean[2];
        boolean[] b = new boolean[2];
        boolean[] c = new boolean[2];
        a[0] = b[0] = c[0] = false;
        a[1] = b[1] = c[1] = true;

        System.out.println("De Morgam law: Negation of a union");
        System.out.println("a    \tb    \t!(a||b)\t(!a&&!b)");
        for (boolean av : a) {
            for (boolean bv : b) {
                System.out.println(av + "\t" + bv + "\t" +
                        !(av || bv) + "\t" + (!av && !bv));

            }
        }

        System.out.println();
        System.out.println("De Morgam law: Negation of an intersection");
        System.out.println("a    \tb    \t!(a&&b)\t(!a||!b)");
        for (boolean av : a) {
            for (boolean bv : b) {
                System.out.println(av + "\t" + bv + "\t" +
                        !(av && bv) + "\t" + (!av || !bv));

            }
        }

        System.out.println();
        System.out.println("a    \tb    " +
                "\t(!(a && b) && (a || b)) || ((a && b) || !(a || b))");
        for (boolean av : a) {
            for (boolean bv : b) {
                System.out.println(av + "\t" + bv + "\t" +
                        ((!(av && bv) && (av || bv)) ||
                                ((av && bv) || !(av || bv))));

            }
        }

        System.out.println();
        System.out.println("AND operation is distributive over OR operation");
        System.out.println("a    \tb    \tc    \ta && (b || c)" +
                "\t(a && b) || (a && c)");
        for (boolean av : a) {
            for (boolean bv : b) {
                for (boolean cv : c) {
                    System.out.println(av + "\t" + bv + "\t" + cv + "\t" +
                            (av && (bv || cv)) + "\t\t" +
                            ((av && bv) || (av && cv)));
                }
            }
        }

        System.out.println();
        System.out.println("OR operation is distributive over AND operation");
        System.out.println("a    \tb    \tc    \ta || (b && c)" +
                "\t(a || b) && (a || c)");
        for (boolean av : a) {
            for (boolean bv : b) {
                for (boolean cv : c) {
                    System.out.println(av + "\t" + bv + "\t" + cv + "\t" +
                            (av || (bv && cv)) + "\t\t" +
                            ((av || bv) && (av || cv)));
                }
            }
        }
    }
}