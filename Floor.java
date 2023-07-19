
// Imported libraries
import java.util.ArrayList;

/**
 * Class for a floor.
 * Contains two queues. One for passengers going up, one for passengers going down.
 * Contains two buttons. One for requesting to go up, one for requesting to go down.
 */
class Floor {

   private int floorNumber;
   private ArrayList<Person> upQueue;
   private ArrayList<Person> downQueue;
   private Button upButton;
   private Button downButton;
   
   /**
    * Constructor that initialises the attributes.
    * @param floorNumber The floor number.
    * @param floorCount The total number of floors in the building.
    */
   public Floor(int floorNumber, int floorCount) {
   
      this.floorNumber = floorNumber;
      this.upQueue = new ArrayList<Person>();
      this.downQueue = new ArrayList<Person>();
      this.generateButtons(floorCount);
   
   }
   
   /**
    * toString method.
    * @return A concatenation of all the current attribute values.
    */
   public String toString() {
   
      return Integer.toString(this.floorNumber) + " " + this.upQueue.toString() + " " + this.downQueue.toString() + " " + this.upButton + " " + this.downButton;
    
   }
   
   /**
    * Generates the up and down buttons for the floor.
    * Only creates up button if bottom floor.
    * Only creates down button if top floor.
    * @param floorCount The total number of floors in the building.
    */
   private void generateButtons(int floorCount) {
   
      if (floorNumber < floorCount - 1) {
         this.upButton = new Button();
      }
      if (floorNumber > 0) {
         this.downButton = new Button();
      }
   
   }
   
   /**
    * Getter for the floorNumber attribute.
    * @return The current floorNumber value.
    */
   public int getFloorNumber() {
   
      return this.floorNumber;
   
   }
   
   /**
    * Getter for the button attributes.
    * @param The type of button (UP or DOWN).
    * @return The current button value.
    */
   public Button getButton(String type) {
   
      if (type.equals("UP")) {
         return upButton;
      }
      else if (type.equals("DOWN")) {
         return downButton;
      }
      
      return null;
   
   }
   
   /**
    * Adds a person to the queue.
    * @param type The type of queue (UP or DOWN).
    * @param person The person to be added.
    */
   public void addToQueue(String type, Person person) {
   
      if (type.equals("UP")) {
         this.upQueue.add(person);
      }
      else if (type.equals("DOWN")) {
         this.downQueue.add(person);
      }
   
   }
   
   /**
    * Removes a person from the queue.
    * @param type The type of queue (UP or DOWN).
    * @param index The index of the person to be removed.
    */
   public void removeFromQueue(String type, int index) {
   
      if (type.equals("UP")) {
         this.upQueue.remove(index);
      }
      else if (type.equals("DOWN")) {
         this.downQueue.remove(index);
      }
   
   }
   
   /**
    * Getter for the queue attributes.
    * @param type The type of queue (UP or DOWN).
    * @return The current queue value.
    */
   public ArrayList<Person> getQueue(String type) {
   
      if (type.equals("UP")) {
         return this.upQueue;
      }
      else if (type.equals("DOWN")) {
         return this.downQueue;
      }
      
      return null;
   
   }

}
