
/**
 * Class for a person to track wait and journey times.
 * Has a destination and incrementable wait and journey times.
 */
class Person {

   private int destination;
   private int waitTime;
   private int journeyTime;
   
   /**
    * Constructor that initialises the attributes.
    * @param destination The destination of the person.
    */
   public Person(int destination) {
   
      this.destination = destination;
      this.waitTime = 0;
      this.journeyTime = 0;
   
   }
   
   /**
    * toString method.
    * @return A concatenation of all the current attribute values.
    */
   public String toString() {
   
      return Integer.toString(this.destination) + " " + Integer.toString(this.waitTime) + " " + Integer.toString(this.journeyTime);
   
   }
   
   /**
    * Getter for the destination attribute.
    * @return The current destination value.
    */
   public int getDestination() {
   
      return this.destination;
   
   }
   
   /**
    * Increments the waitTime attribute.
    * @param increment Amount to increment by.
    */
   public void incrementWaitTime(int increment) {
   
      this.waitTime = this.waitTime + increment;
   
   }
   
   /**
    * Getter for the waitTime attribute.
    * @return The current waitTime value.
    */
   public int getWaitTime() {
   
      return this.waitTime;
   
   }
   
   /**
    * Increments the journeyTime attribute.
    * @param increment Amount to increment by.
    */
   public void incrementJourneyTime(int increment) {
   
      this.journeyTime = this.journeyTime + increment;
   
   }
   
   /**
    * Getter for the journeyTime attribute.
    * @return The current journeyTime value.
    */
   public int getJourneyTime() {
   
      return this.journeyTime;
   
   }

}
