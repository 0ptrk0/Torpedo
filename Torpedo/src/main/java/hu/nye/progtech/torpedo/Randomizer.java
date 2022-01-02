package hu.nye.progtech.torpedo;

import java.util.Random;

public class Randomizer {
  /**
   * instance get a value.
   */
  private static Random theInstance = null;

  /**
   * Randomizer for the AI.
   */
  public Randomizer() {

  }

  /**
   * get the instance.
   @return instance
   */
  public static Random getTheInstance() {
    return theInstance;
  }

  /**
   * Set the instance.
   @param theInstance instance
   */
  public static void setTheInstance(final Random theInstance) {
    Randomizer.theInstance = theInstance;
  }

  /**
   * End of the Randomizer.
   @return instance
   */
  public static Random getInstance() {
    if (theInstance == null) {
      theInstance = new Random();
    }
    return theInstance;
  }

  /**
   * Making a random number.
   @param n value for the randomizer.
   @return random number
   */
  public static int nextInt(final int n) {
    return Randomizer.getInstance().nextInt(n);
  }

  /**
   * Making a random number.
   @param min minimum value
   @param max maximum value
   @return random number
   */
  public static int nextInt(final int min, final int max) {
    return min + Randomizer.nextInt(max - min + 1);
  }


}
