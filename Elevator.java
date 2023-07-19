
// Imported libraries
import java.util.ArrayList;

/**
 * Class for an elevator.
 * Contains passengers and can be moved up and down.
 * Holds a value for the amount of moves it has made.
 */
class Elevator {

   /**
    * Position attribute represents the position of the elevator rather than the floor number.
    * Each floor is every multiple of 3 positions to account for the 30s between floors.
    * (e.g. position 9 would be floor 3).
    */
   private int position;
   private String direction;
   private int waiting;
   private ArrayList<Person> passengers;
   private int pickupFloor;
   private int moves;
   
   /**
    * Constructor that initialises the attributes.
    */
   public Elevator() {
   
      this.position = 0;
      this.direction = "NONE";
      this.waiting = 0;
      this.passengers = new ArrayList<Person>();
      this.pickupFloor = -1;
      this.moves = 0;
   
   }
   
   /**
    * toString method.
    * @return A concatenation of all the current attribute values.
    */
   public String toString() {
   
      return Integer.toString(this.position) + " " + this.direction + " " + Integer.toString(waiting) + " " + this.passengers.toString() + " " + Integer.toString(this.pickupFloor) + " " + Integer.toString(this.moves);
   
   }
   
   /**
    * Getter for the direction attribute.
    * @return The current direction value.
    */
   public String getDirection() {
   
      return this.direction;
   
   }
   
   /**
    * Setter for the direction attribute.
    * @param direction The new direction value.
    */
   public void setDirection(String direction) {
   
      this.direction = direction;
   
   }
   
   /**
    * Setter for the waiting attribute.
    * @param waiting The new waiting value.
    */
   public void setWaiting(int waiting) {
   
      this.waiting = waiting;
   
   }
   
   /**
    * Getter for the position attribute.
    * @return The current position value.
    */
   public int getPosition() {
   
      return this.position;
   
   }
   
   /**
    * Setter for the pickupFloor attribute.
    * @param floor The new pickupFloor value.
    */
   public void setPickupFloor(int floor) {
   
      this.pickupFloor = floor;
   
   }
   
   /**
    * Getter for the pickupFloor attribute.
    * @return The current pickupFloor value.
    */
   public int getPickupFloor() {
   
      return this.pickupFloor;
   
   }
   
   /**
    * Adds a passenger to the passengers array list.
    * @param passenger The passenger to be added.
    */
   public void addPassenger(Person passenger) {
   
      this.passengers.add(passenger);
   
   }
   
   /**
    * Removes a passenger from the passengers array list.
    * @param index The index of the passenger to be removed.
    */
   public void removePassenger(int index) {
   
      this.passengers.remove(index);
   
   }
   
   /**
    * Getter for the passengers attribute.
    * @return The current passengers value.
    */
   public ArrayList<Person> getPassengers() {
   
      return this.passengers;
   
   }
   
   /**
    * Getter for the moves attribute.
    * @return The current moves value.
    */
   public int getMoves() {
   
      return this.moves;
   
   }
   
   /**
    * Moves the elevator up/down based on current direction unless waiting.
    * Decrements waiting value if it is waiting.
    */
   public void move() {
   
      if (this.waiting > 0) {
         this.waiting = this.waiting - 1;
      }
      else {
         if (this.direction.equals("UP")) {
            this.position = this.position + 1;
            this.moves = this.moves + 1;
         }
         else if (this.direction.equals("DOWN")) {
            this.position = this.position - 1;
            this.moves = this.moves + 1;
         }
      }
   
   }
   
   /**
    * Overloaded method to move the elevator up/down manually, irrespective of current direction.
    * @param direction Direction to move the elevator.
    */
   public void move(String direction) {
   
      if (direction.equals("UP")) {
         this.position = this.position + 1;
         this.moves = this.moves + 1;
      }
      else if (direction.equals("DOWN")) {
         this.position = this.position - 1;
         this.moves = this.moves + 1;
      }
   
   }

}
