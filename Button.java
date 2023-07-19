
/**
 * Class for a button to call the elevators.
 * Can be pressed or depressed.
 */
class Button {

   private boolean pressed;
   
   /**
    * Constructor that initialises the attributes.
    */
   public Button() {
   
      this.pressed = false;
   
   }
   
   /**
    * toString method.
    * @return A concatenation of all the current attribute values.
    */
   public String toString() {
   
      return Boolean.toString(this.pressed);
   
   }
   
   /**
    * Presses the button.
    */
   public void press() {
   
      this.pressed = true;
   
   }
   
   /**
    * Depresses the button.
    */
   public void depress() {
   
      this.pressed = false;
   
   }
   
   /**
    * Getter for the pressed attribute.
    * @return The current pressed value.
    */
   public boolean getPressed() {
   
      return this.pressed;
   
   }

}
