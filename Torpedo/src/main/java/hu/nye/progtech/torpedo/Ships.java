package hu.nye.progtech.torpedo;

public class Ships {
  /**
   * Ships length.
   */
  private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
  /**
   * Number of ships value is 5.
   */
  private static final int NUM_OF_SHIPS = 5;
  /**
   * Ship placement.
   */
  private ShipPlacement[] ships;
  /**
   * Player Grid.
   */
  private Grid playerGrid;
  /**
   * Opponent(AI) grid.
   */
  private Grid oppGrid;

  /**
   * Ships numbers must be 5.
   */
  public Ships() {
    if (NUM_OF_SHIPS != 5) {
      throw new IllegalArgumentException("Hiba! Muszaj 5 hajonak lenni");
    }

    ships = new ShipPlacement[NUM_OF_SHIPS];
    for (int i = 0; i < NUM_OF_SHIPS; i++) {
      ShipPlacement tempShip = new ShipPlacement(SHIP_LENGTHS[i]);
      ships[i] = tempShip;
    }

    playerGrid = new Grid();
    oppGrid = new Grid();
  }

  /**
   * get ships value.
   @return ships.
   */
  public ShipPlacement[] getShips() {
    return ships;
  }

  /**
   * Ship placement.
   @param ships ships
   */
  public void setShips(final ShipPlacement[] ships) {
    this.ships = ships;
  }

  /**
   * get player grid.
   @return player grid
   */
  public Grid getPlayerGrid() {
    return playerGrid;
  }

  /**
   * set the player grid.
   @param playerGrid player grid
   */
  public void setPlayerGrid(final Grid playerGrid) {
    this.playerGrid = playerGrid;
  }

  /**
   get opponent grid.
   @return opponent grid
   */
  public Grid getOppGrid() {
    return oppGrid;
  }

  /**
   set opponent grid.
   @param oppGrid opponent grid
   */
  public void setOppGrid(final Grid oppGrid) {
    this.oppGrid = oppGrid;
  }

  /**
   * if counter 0, all of the ships placed down.
   @return counter
   */
  public int numOfShipsLeft() {
    int counter = 5;
    for (ShipPlacement s : ships) {
      if (s.isLocationSet() && s.isDirectionSet()) {
        counter--;
      }
    }

    return counter;

  }


}
