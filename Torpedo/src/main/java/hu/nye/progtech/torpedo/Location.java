package hu.nye.progtech.torpedo;

public class Location {
  /**
   * UNGUESSED get a value.
   */
  public static final int UNGUESSED = 0;
  /**
   *Hit get a value.
   */
  public static final int HIT = 1;
  /**
   *Missed get a value.
   */
  public static final int MISSED = 2;
  /**
   * Hasship boolean value.
   */
  private boolean hasShip;
  /**
   * status integer.
   */
  private int status;
  /**
   length of ship integer.
   */
  private int lengthOfShip;
  /**
   *direction of ship integer.
   */
  private int directionOfShip;

  /**
   * Fix the location value.
   */
  public Location() {

    status = 0;
    hasShip = false;
    lengthOfShip = -1;
    directionOfShip = -1;
  }

  /**
   *Hit checking.
   @return Hit
   */
  public boolean checkHit() {
    return status == HIT;
  }

  /**
   * Miss checking.
   @return MISSED
   */
  public boolean checkMiss() {
    return status == MISSED;
  }

  /**
   * Is it unguessed boolean value.
   @return UNGUESSED
   */
  public boolean isUnguessed() {
    return status == UNGUESSED;
  }

  /**
   * Marking the hit.
   */
  public void markHit() {
    setStatus(HIT);
  }

  /**
   *Marking the miss.
   */
  public void markMiss() {
    setStatus(MISSED);
  }

  /**
   * Boolean value of has ship.
   @return True or false
   */
  public boolean hasShip() {
    return hasShip;
  }

  /**
   * set the ship in the field.
   @param val boolean value
   */
  public void setShip(final boolean val) {
    this.hasShip = val;
  }

  /**
   *get the status.
   @return Status
   */
  public int getStatus() {
    return status;
  }

  /**
   * set the Status.
   @param status status of ship.
   */
  public void setStatus(final int status) {
    this.status = status;
  }

  /**
   * get length of ship.
   @return lengthofship
   */
  public int getLengthOfShip() {
    return lengthOfShip;
  }

  /**
   * set length of ship to value.
   @param val value of length of ship
   */
  public void setLengthOfShip(final int val) {
    lengthOfShip = val;
  }

  /**
   * get the direction of ship.
   @return ship direction
   */
  public int getDirectionOfShip() {
    return directionOfShip;
  }

  /**
   * the direction of ship change to value.
   @param val value
   */
  public void setDirectionOfShip(final int val) {
    directionOfShip = val;
  }
}
