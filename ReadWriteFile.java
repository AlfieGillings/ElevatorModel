
// Imported libraries
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Static class containing the methods for manipulating files.
 */
final class ReadWriteFile {
   
   /**
    * Private empty constructor to prevent instantiation.
    * This class should not be instantiated as all the methods are static and it has no attributes.
    */
   private ReadWriteFile() {
   
   }
   
   /**
    * Reads a file from a specified directory.
    * @param path The directory of the file.
    * @return Lines of the file.
    */
   public static ArrayList<String> readFile(String path) {
   
      ArrayList<String> contents = new ArrayList<String>();
   
      try {
      
         File file = new File(path);
         Scanner scanner = new Scanner(file);
         
         while (scanner.hasNextLine()) {
            contents.add(scanner.nextLine());
         }
      
      } catch (FileNotFoundException e) {
      
         System.out.println("'" + path + "' could not be found.");
         e.printStackTrace();
         return null;
           
      }
      
      return contents;
   
   }
   
   /**
    * Writes to a file in a specified directory.
    * @param path The directory of the file.
    * @param contents The lines to be written to the file.
    */
   public static void writeFile(String path, ArrayList<String> contents) {
   
      try {
      
         FileWriter fileWriter = new FileWriter(path);
         for (int i = 0; i < contents.size(); i++) {
            fileWriter.write(contents.get(i) + "\n");
         }
         fileWriter.close();
      
      } catch (IOException e) {
      
         System.out.println("An error occurred while attempting to write to '" + path + "'");
         e.printStackTrace();
      
      }
   
   }
   
   /**
    * Gets the names of all files in a specified directory.
    * @param directory The directory of the file.
    * @return The names of the files.
    */
   public static String[] getFileNames(String directory) {
   
      File file = new File(directory);
      return file.list();
   
   }

}
