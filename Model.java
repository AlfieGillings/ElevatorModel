
// Imported libraries
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class for the elevator algorithm model.
 */
class Model {

   private static Building building;
   private static ArrayList<Instruction> instructions;
   private static String callList;
   
   /**
    * Main method.
    * Creates a building object, reads specified call list, and starts the model.
    */
   public static void main(String[] args) {
   
      // Instantiate building object
      building = new Building(7, 3);
      
      // List available call lists
      String[] files = ReadWriteFile.getFileNames("Call Lists");
      System.out.println("Available call lists:");
      for (int i = 0; i < files.length; i++) {
         System.out.println(files[i]);
      }
      System.out.println("");
      
      // Get user input to select call list
      Scanner scanner = new Scanner(System.in);
      boolean valid = false;
      while (valid == false) {
      
         System.out.println("Enter the call list name: ");
         callList = scanner.nextLine();
         for (int i = 0; i < files.length; i++) {
            if (callList.equals(files[i])) {
               valid = true;
            }
         }
      
      }
      instructions = getInstructions("Call Lists/" + callList);
      
      // Start the model
      start();
      
   }
   
   /**
    * Reads instructions from a call list.
    * Creates an Instruction object for each line and adds it to the array list of instructions.
    */
   private static ArrayList<Instruction> getInstructions(String file) {
   
      ArrayList<String> lines = ReadWriteFile.readFile(file);
      ArrayList<Instruction> instructions = new ArrayList<Instruction>();
      for (int i = 0; i < lines.size(); i++) {
         instructions.add(new Instruction(lines.get(i)));
      }
      
      return instructions;
   
   }
   
   /**
    * Starts the main loop.
    * Main loop sends instructions every 60 seconds and advances elevators.
    * Generates a log file once finished.
    */
   private static void start() {
   
      int start = 0;
      int counter = 5;
      
      // Main loop
      while (start < 2) {
         
         start = 0;
         
         // Print building each tick for testing
         System.out.println(building);
         
         // Send instruction every 6 ticks
         if (instructions.size() > 0) {
            counter = counter + 1;
            if (counter % 6 == 0) {
               building.sendInstruction(instructions.get(0));
               instructions.remove(0);
            }
         }
         else {
            start = start + 1;
         }
         
         // Advance elevators
         advance();
         start = start + incrementTime();
      
      }
      
      // Generate log once main loop has finished
      generateLog();
   
   }
   
   /**
    * Increments all the time counters.
    * Adds 10 seconds to the overall time, the wait times, and the journey times.
    * @return Exit status used as a stop condition for the main loop.
    */
   private static int incrementTime() {
   
      Floor[] floors = building.getFloors();
      Elevator[] elevators = building.getElevators();
      int exitStatus = 1;
      
      // Increments overall time.
      building.incrementTime(10);
      
      // Increments wait times for all Person objects on any floor.
      for (int i = 0; i < floors.length; i++) {
      
         ArrayList<Person> queue = new ArrayList<Person>();
         queue.addAll(floors[i].getQueue("UP"));
         queue.addAll(floors[i].getQueue("DOWN"));
         
         for (int n = 0; n < queue.size(); n++) {
            queue.get(n).incrementWaitTime(10);
            exitStatus = 0;
         }
      
      }
      
      // Increments journey times for all Person objects in any elevator.
      for (int i = 0; i < elevators.length; i++) {
      
         ArrayList<Person> passengers = elevators[i].getPassengers();
         
         for (int n = 0; n < passengers.size(); n++) {
            passengers.get(n).incrementJourneyTime(10);
            exitStatus = 0;
         }
      
      }
      
      return exitStatus;
   
   }
   
   /**
    * Advances all the elevators.
    * Sets a pickup destination if the elevator has none.
    * Moves towards the pickup destination if not reached it yet.
    * Moves in current direction until all passengers have departed, then sets direction back to NONE.
    */
   private static void advance() {
      
      Elevator[] elevators = building.getElevators();
      
      // For each elevator
      for (int i = 0; i < elevators.length; i++) {
      
         Elevator currentElevator = elevators[i];
         if (currentElevator.getDirection().equals("NONE")) {
            // Set pickup destination
            setElevatorPickup(currentElevator);
         }
         else if (currentElevator.getPickupFloor() != -1) {
            // Move towards pickup destination
            moveTowardsPickup(currentElevator, i);
         }
         else {
            // Moves in current direction untill no passengers left
            currentElevator.move();
            building.embarkDisembarkElevator(i);
            if (currentElevator.getPassengers().size() == 0) {
               currentElevator.setDirection("NONE");
            }
         }
      
      }
   
   }
   
   /**
    * Sets elevator pickup destination.
    * Checks for any buttons pressed and assigns a pickup destination to the specified elevator.
    * @param elevator The elevator that needs a pickup destination.
    */
   private static void setElevatorPickup(Elevator elevator) {
   
      Floor[] floors = building.getFloors();
      // For each floor
      for (int n = 0; n < floors.length; n++) {
            
         // Check for up buttons pressed
         Floor currentFloor = floors[n];
         if (currentFloor.getButton("UP") != null) {
            if (currentFloor.getButton("UP").getPressed() == true) {
               elevator.setPickupFloor(n);
               elevator.setDirection("UP");
               currentFloor.getButton("UP").depress();
               break;
            }
         }
         // Check for down buttons pressed
         if (currentFloor.getButton("DOWN") != null) {
            if (currentFloor.getButton("DOWN").getPressed() == true) {
               elevator.setPickupFloor(n);
               elevator.setDirection("DOWN");
               currentFloor.getButton("DOWN").depress();
               break;
            }
         }
      
      }
   
   }
   
   /**
    * Moves elevator towards its pickup destination.
    * Picks up passengers when destination is reached.
    * @param elevator Elevator to be moved.
    * @param elevatorNumber Elevator index number.
    */
   private static void moveTowardsPickup(Elevator elevator, int elevatorNumber) {
   
      if (elevator.getPickupFloor() > elevator.getPosition() / 3) {
         elevator.move("UP");
      }
      else if (elevator.getPickupFloor() < elevator.getPosition() / 3) {
         elevator.move("DOWN");
      }
      else {
         building.embarkDisembarkElevator(elevatorNumber);
         elevator.setPickupFloor(-1);
      }
         
   }
   
   /**
    * Generates a log file containing all necessary information.
    * Calculates the minimum, maximum, and mean wait times for passengers.
    * Calculates the minimum, maximum, and mean journey times for passengers.
    * Calculates the number of floor to floor moves made by each elevator.
    * Calculates the total time required to complete all passenger journeys.
    * Writes all results to a log file.
    */
   private static void generateLog() {
      
      ArrayList<String> results = new ArrayList<String>();
      
      Elevator[] elevators = building.getElevators();
      ArrayList<Integer> waitTimes = building.getWaitTimes();
      ArrayList<Integer> journeyTimes = building.getJourneyTimes();
      
      // Calculate and add wait times
      int min = 0;
      int max = 0;
      int mean = 0;
      int amount = 0;
      for (int i = 0; i < waitTimes.size(); i++) {
      
         if (waitTimes.get(i) < min) {
            min = waitTimes.get(i);
         }
         if (waitTimes.get(i) > max) {
            max = waitTimes.get(i);
         }
         mean = mean + waitTimes.get(i);
         amount = amount + 1;
      
      }
      mean = mean / amount;
      
      results.add("Minimum wait time for a passenger: " + Integer.toString(min));
      results.add("Maximum wait time for a passenger: " + Integer.toString(max));
      results.add("Mean wait time for a passenger: " + Integer.toString(mean));
      results.add("");
      
      // Calculate and add journey times
      min = 0;
      max = 0;
      mean = 0;
      amount = 0;
      for (int i = 0; i < journeyTimes.size(); i++) {
      
         if (journeyTimes.get(i) < min) {
            min = journeyTimes.get(i);
         }
         if (journeyTimes.get(i) > max) {
            max = journeyTimes.get(i);
         }
         mean = mean + journeyTimes.get(i);
         amount = amount + 1;
      
      }
      mean = mean / amount;
      
      results.add("Minimum journey time for a passenger: " + Integer.toString(min));
      results.add("Maximum journey time for a passenger: " + Integer.toString(max));
      results.add("Mean journey time for a passenger: " + Integer.toString(mean));
      results.add("");
      
      // Add elevator moves
      for (int i = 0; i < elevators.length; i++) {
      
         results.add("Elevator " + Integer.toString(i) + " floor to floor moves: " + Integer.toString((int) Math.floor(elevators[i].getMoves() / 3)));
      
      }
      results.add("");
      
      // Add total time
      results.add("Total time required to complete all passenger journeys: " + Integer.toString(building.getTime()) + " seconds");
      
      // Write to log file
      ReadWriteFile.writeFile("Log Files/" + callList.split("\\.")[0] + "_Log.txt", results);
   
   }

}
