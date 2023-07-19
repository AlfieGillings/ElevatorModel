
// Imported libraries
import java.util.Arrays;

/**
 * Class for the instructions that are read from the call lists.
 * Splits the instruction into the start, action, and destinations when instantiated.
 */
class Instruction {

   private int start;
   private String action;
   private int[] destinations;
   
   /**
    * Constructor that initialises the attributes of the object using an instruction from the call list.
    * @param instruction An instruction from the call list.
    */
   public Instruction(String instruction) {
   
      if (instruction.equals("NOCALL")) {
         this.action = "NOCALL";
      }
      else {
         String[] splitInstruction = instruction.split(",");
         
         this.start = Integer.parseInt(splitInstruction[0]);
         this.action = splitInstruction[1];
         this.destinations = new int[splitInstruction.length - 2];
         
         for (int i = 0; i < this.destinations.length; i++) {
            this.destinations[i] = Integer.parseInt(splitInstruction[i + 2]);
         }
      }
   
   }
   
   /**
    * toString method.
    * @return A concatenation of all the current attribute values.
    */
   public String toString() {
   
      return Integer.toString(this.start) + " " + this.action + " " + Arrays.toString(this.destinations);
   
   }
   
   /**
    * Getter for the start attribute.
    * @return Current start value.
    */
   public int getStart() {
   
      return this.start;
   
   }
   
   /**
    * Getter for the action attribute.
    * @return Current action value.
    */
   public String getAction() {
   
      return this.action;
   
   }
   
   /**
    * Getter for the destinations attribute.
    * @return Current destinations value.
    */
   public int[] getDestinations() {
   
      return this.destinations;
   
   }

}
