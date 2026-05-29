public class stringClass {
    public static void main(String[] args) {
        // Declare a string. This statement does not create anything. It says
        // that we will use the name s1 to rfer to a string object. In jshell
        // it would show s1 ==> null
        String s1;

        // Each data-type (rvalue) is stored in an object. When client calls
        // a constructor, the java system creates (or instantiates) an
        // individual object (or instance). To invoke a constructor use the
        // keyword new.
        s1 = new String();
        System.err.println("s1:" + s1);

        // Strings are common in use, therefore, the following shortcut.
        String s2 = "";
        System.err.println("s2:" + s2);

        s2 = "Hello world, will I be able to run on a quantum computer!";
        System.out.println("s2: " + s2);
        // Methods in strings.
        // https://docs.oracle.com/javase/8/docs/api/java/lang/String.html.
        System.out.println("Check https://docs.oracle.com/javase/8/"
                + "docs/api/java/lang/String.html");

    }
}
