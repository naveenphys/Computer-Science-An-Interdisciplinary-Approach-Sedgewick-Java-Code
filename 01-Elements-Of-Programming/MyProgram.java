// Program name: MyProgram
// Code for MyProgram is contained in the "class MyProgram"
// According to the convention, the name of the file containing the 
// "class MyPrgram" must be "MyProgram.java"
// 
// For a class to be executable it must define a method called "main":
// public static void main(String[] args)
//
// Two steps for compilation and execution:
// javac - read Java declarations and compile them into class files
// Command "javac MyProgram.java", generates "MyProgram.class"
// If the file name and class name are different, an error is generated:
// error: "class MyProgram" is public, should be declared in a file named 
// "MyProgram.java"
// 
// java - launch a Java program
// We can now execute the program using command "java MyProgram"
// Note: If the main method is missing, then an error occurs:
// Error: Main method not found in class MyProgram, please define the main 
//        method as:
//    public static void main(String[] args)

public class MyProgram
{
	public static void main(String[] args)
	{
		System.out.println("My first java program");
	}
}
