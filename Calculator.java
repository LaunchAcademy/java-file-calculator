import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Calculator {
  private static final String PROBLEM_PATH = "./problem.txt";
  private static final String PROBLEMS_PATH = "./problems.txt";
  private static final String RESULTS_PATH = "./results.txt";

  public static void main(String[] args) throws IOException {
    File problemsFile = new File(PROBLEMS_PATH);
    Scanner problemsScanner = new Scanner(problemsFile);
    FileWriter resultsWriter = new FileWriter(RESULTS_PATH);

//    String contents = problemScanner.useDelimiter("\\z").next();
//    System.out.println(contents);
    int result = 0;
    String operator = "";
    while (problemsScanner.hasNextLine()) {
      String currentLine = problemsScanner.nextLine();

      if (currentLine.equals("+")) {
        System.out.println("I'm a plus sign!");
        operator = currentLine;
        result = 0;
      } else if (currentLine.equals("*")) {
        System.out.println("I do multiplication!");
        operator = currentLine;
        result = 1;
      } else if (currentLine.equals("=")) {
        resultsWriter.write(result + "\n");
        // String.valueOf(int )
        // Integer.toString(int )
        System.out.println("Final Result: " + result);
      } else {
        System.out.println(currentLine);
        int currentInt = Integer.parseInt(currentLine);

        if (operator.equals("+")) {
          result += currentInt;
        } else if (operator.equals("*")) {
          result *= currentInt;
        }
      }
    }
    problemsScanner.close();
    resultsWriter.close();
  }
}
