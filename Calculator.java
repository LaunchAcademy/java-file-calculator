import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Calculator {

  // this is for the first part
  //  public static final String PROBLEM_PATH = "./problem.txt";
  public static final String PROBLEM_PATH = "./problems.txt";
  public static final String RESULT_PATH = "./results.txt";

  public static void main(String[] args) throws IOException {
    File problemFile = new File(PROBLEM_PATH);
    File fileToWriteTo = new File(RESULT_PATH);
    FileWriter fileWriter = new FileWriter(fileToWriteTo);

    Scanner scanner = new Scanner(problemFile);

    // this is to track the result for each individual operation.
    // It will need to be reset to a correct starting value based on
    // the operation that is about to be performed
    int result = 0;

    // Here we are using a boolean to track which operation I am currently performing.
    // To start all operations are false and only when we read the correct symbol from a file,
    // should that specific boolean be reassigned a value of true.
    // This will be used so that we only execute the correct logic in our conditional below.
    boolean addition = false;
    boolean multiplication = false;

    while (scanner.hasNextLine()) {
      //This is to avoid calling scanner.nextLine() multiple times and advancing the scanner.
      // Now we have access to the current line and can reference this variable throughout the while loop
      String currentLine = scanner.nextLine();

      if (currentLine.equals("+")) {
        //When we come across a + symbol, we know that we will need to perform addition,
        // so we turn the addition boolean to true and ensure any other booleans are false,
        // so that there will be no conflicts below with which conditional to enter.
        addition = true;
        multiplication = false;
      } else if (currentLine.equals("*")) {
        //In addition to setting the correct boolean to true, we also assign result with a starting value of 1.
        // Since we will be multiplying the current value of result by each number we are about read,
        // if we left result as 0, we would only ever get 0 back
        result = 1;
        multiplication = true;
        addition = false;
      } else if (currentLine.equals("=")) {
        // by adding "\n" it is turning `result` into a string and allowing us to add the integer into the file
        fileWriter.write(result + "\n");
        System.out.println("The result is: " + result);
        // We reset result back to 0 so it is ready for the next operation.
        // We can see that this will only work if we are just dealing with addition.
        // With multiplication we need to make sure it is 1 and not 0.
        result = 0;
      } else {
        //we will hit the `else` whenever the `currentLine` that we are reading
        // is not one of the symbols that we are accounting for on lines 36, 42 and 49.
        // The next set of conditionals will tell us what operation we are currently in.
        // This is why it's important to only have one set to true at a time
        if (addition) {
          //At this point we know that we are dealing with a number like "2" that will be safe to parse out of a string,
          // so we are able to call Integer.parseInt(currentLine) below.
          result += Integer.parseInt(currentLine);
        } else if (multiplication) {
          result *= Integer.parseInt(currentLine);
        }
      }
    }
    //We must make sure to also close our file when we are done writing to it!
    // A common issue can be when you are writing to a file but nothing gets added,
    // ensure it is being closed like below.
    fileWriter.close();
  }
}
