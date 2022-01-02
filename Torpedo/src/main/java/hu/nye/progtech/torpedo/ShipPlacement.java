package hu.nye.progtech.torpedo;


public class ShipPlacement {
  /**
   * unset get a value.
   */
  public static final int UNSET = -1;
  /**
   *Horizontal get a value.
   */
  public static final int HORIZONTAL = 0;
  /**
   Vertical get a value.
   */
  public static final int VERTICAL = 1;
  /**
   * lenght of the ships.
   */
  private final int length;
  /**
   * number of rows.
   */
  private int row;
  /**
   *Number of col.
   */
  private int col;
  /**
   *  get ships direction.
   */
  private int direction;

  /**
   * Ship can be placed?
   *@param length length of ships
   */
  public ShipPlacement(final int length) {
    this.length = length;
    this.row = -1;
    this.col = -1;
    this.direction = UNSET;
  }

  /**
   * The field is empty or occupied.
   @return 2 number
   */
  public boolean isLocationSet() {
    return row != -1 && col != -1;
  }

  /**
   * Direction of the ships.
   @return direction.
   */
  public boolean isDirectionSet() {
    return direction != UNSET;
  }

  /**
   * set the ship location on the board.
   @param row row
   @param col col
   */
  public void setLocation(final int row, final int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * het the row number.
   @return row.
   */
  public int getRow() {
    return row;
  }

  /**
   *get the col number.
   @return col.
   */
  public int getCol() {
    return col;
  }

  /**
   *get the length of the ships.
   @return length
   */
  public int getLength() {
    return length;
  }

  /**
   * get the direction.
   @return direction
   */
  public int getDirection() {
    return direction;
  }

  /**
   * how u want to be place the ship.
   @param direction direction.
   */
  public void setDirection(final int direction) {
    if (direction != UNSET && direction != HORIZONTAL && direction != VERTICAL) {
      throw new IllegalArgumentException("Helytelen irany megadas. Legyen -1, 0, vagy 1");
    }
    this.direction = direction;
  }

  /**
   * Convert direction to String.
   @return the verical or the horizontal value.
   */
  private String directionToString() {
    if (direction == UNSET) {
      return "UNSET";
    } else if (direction == HORIZONTAL) {
      return "HORIZONTAL";
    } else {
      return "VERTICAL";
    }
  }

  /**
   * Override the values.
   @return Values.
   */
  public String toString() {
    return "Ship: " + getRow() + ", " + getCol() + " with length " + getLength() + " and direction " + directionToString();
  }
}
