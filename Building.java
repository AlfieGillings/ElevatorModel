
// Imported libraries
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Class for a building.
 * Contains floors and elevators.
 * Holds values for the total time passed, and lists of the wait and journey times of departed passengers.
 */
class Building {

   private Floor[] floors;
   private Elevator[] elevators;
   private int time;
   private ArrayList<Integer> waitTimes;
   private ArrayList<Integer> journeyTimes;
   
   /**
    * Constructor that initialises the attributes.
    * @param floorCount The total number of floors.
    * @param elevatorCount The total number of elevators.
    */
   public Building(int floorCount, int elevatorCount) {
   
      this.generateFloors(floorCount);
      this.generateElevators(elevatorCount);
      this.time = 0;
      this.waitTimes = new ArrayList<Integer>();
      this.journeyTimes = new ArrayList<Integer>();
   
   }
   
   /**
    * toString method.
    * @return A concatenation of all the current attribute values.
    */
   public String toString() {
   
      return Arrays.toString(this.floors) + " " + Arrays.toString(this.elevators) + " " + Integer.toString(this.time) + " " + this.waitTimes.toString() + " " + this.journeyTimes.toString();
   
   }
   
   /**
    * Generates the floors.
    * @param The total amount of floors.
    */
   private void generateFloors(int floorCount) {
      
      this.floors = new Floor[floorCount];
      for (int i = 0; i < floorCount; i++) {
         this.floors[i] = new Floor(i, floorCount);
      }
   
   }
   
   /**
    * Generates the elevators.
    * @param The total amount of elevators.
    */
   private void generateElevators(int elevatorCount) {
   
      this.elevators = new Elevator[elevatorCount];
      for (int i = 0; i < elevatorCount; i++) {
         this.elevators[i] = new Elevator();
      }
   
   }
   
   /**
    * Getter for the floors attribute.
    * @return The current floors value.
    */
   public Floor[] getFloors() {
   
      return this.floors;
   
   }
   
   /**
    * Getter for the elevators attribute.
    * @return The current elevators value.
    */
   public Elevator[] getElevators() {
   
      return this.elevators;
   
   }
   
   /**
    * Increments the time attribute.
    * @param increment The amount to increment by.
    */
   public void incrementTime(int increment) {
   
      this.time = this.time + increment;
   
   }
   
   /**
    * Getter for the time attribute.
    * @retun The current time value.
    */
   public int getTime() {
   
      return this.time;
   
   }
   
   /**
    * Getter for the waitTimes attribute.
    * @return The current waitTimes value.
    */
   public ArrayList<Integer> getWaitTimes() {
   
      return this.waitTimes;
   
   }
   
   /**
    * Getter for the journeyTimes attribute.
    * @return The current journeyTimes value.
    */
   public ArrayList<Integer> getJourneyTimes() {
   
      return this.journeyTimes;
   
   }
   
   /**
    * Recieves and processes an instruction.
    * Splits the function into Person objects, each with a single destination.
    * Adds the Person objects to the start floor and presses the relevant button.
    * @param instruction The instruction to be processed.
    */
   public void sendInstruction(Instruction instruction) {
      
      if (instruction.getAction().equals("NOCALL")) {
         return;
      }
      
      for (int i = 0; i < instruction.getDestinations().length; i++) {
      
         Person person = new Person(instruction.getDestinations()[i]);
         this.floors[instruction.getStart()].addToQueue(instruction.getAction(), person);
         this.floors[instruction.getStart()].getButton(instruction.getAction()).press();
      
      }
   
   }
   
   /**
    * Attempts to transfer people to and from a specified elevator.
    * Moves queueing passengers from floor to elevator.
    * Removes passengers at destination from elevator and records wait and journey times.
    * Adds waiting time to elevator if embarking or disembarking has occurred.
    * @param elevatorNumber The index number of the elevator.
    */
   public void embarkDisembarkElevator(int elevatorNumber) {
   
      // Gets elevator and current floor
      Elevator elevator = this.elevators[elevatorNumber];
      Floor floor = this.floors[elevator.getPosition() / 3];
      
      ArrayList<Person> queue = floor.getQueue(elevator.getDirection());
      int size = queue.size();
      
      // Checks for people to embark from floor to elevator.
      for (int i = 0; i < size; i++) {
      
         elevator.addPassenger(queue.get(0));
         floor.removeFromQueue(elevator.getDirection(), 0);
      
      }
      
      // Checks for people to disembark from the elevator.
      ArrayList<Person> passengers = elevator.getPassengers();
      boolean changed = true;
      while (changed) {
      
         changed = false;
         
         for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getDestination() == elevator.getPosition() / 3) {
               this.waitTimes.add(passengers.get(i).getWaitTime());
               this.journeyTimes.add(passengers.get(i).getJourneyTime());
               elevator.removePassenger(i);
               changed = true;
               size = 1;
               break;
            }
         }
         
      }
      
      // Makes elevator wait if embarking/disembarking.
      if (size > 0) {
         elevator.setWaiting(2);
      }
   
   }

}
