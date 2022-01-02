package hu.nye.progtech.torpedo;

import java.util.Scanner;

public class Battleship {
  /**
   * Scanner reader to get the value from the player.
   */
  private final static Scanner reader = new Scanner(System.in);

  /**
   * main method of the Battleships game.
   @param args args
   */
  public static void main(final String... args) {
    System.out.println("\nJatekos leteszi a hajoit...:");
    Ships userShips = new Ships();
    setup(userShips);

    System.out.println("AI leteszi hajoit...Nyomj entert a jatek folytatasahoz...");
    reader.nextLine();
    reader.nextLine();
    Ships computer = new Ships();
    setupComputer(computer);
    System.out.println("\nAI tabla (FOR DEBUG)...");
    computer.getPlayerGrid().printShips();

    String result = "";
    while (true) {
      System.out.println(result);
      System.out.println("\nA jatekos kovetkezik:");
      result = askForGuess(userShips, computer);

      if (userShips.getPlayerGrid().hasLost()) {
        System.out.println("AI talalat!...Jatekos vesztett");
        break;
      } else if (computer.getPlayerGrid().hasLost()) {
        System.out.println("Talalat!...AI vesztett");
        break;
      }

      System.out.println("\nAI eppen lo...");


      compMakeGuess(computer, userShips);
    }
  }

  private static void compMakeGuess(final Ships comp, final Ships user) {
    int row = Randomizer.nextInt(0, 9);
    int col = Randomizer.nextInt(0, 9);

    while (comp.getOppGrid().alreadyGuessed(row, col)) {
      row = Randomizer.nextInt(0, 9);
      col = Randomizer.nextInt(0, 9);
    }

    if (user.getPlayerGrid().hasShip(row, col)) {
      comp.getOppGrid().markHit(row, col);
      user.getPlayerGrid().markHit(row, col);
      System.out.println("AI talalat: " + convertIntToLetter(row) + convertCompColToRegular(col));
    } else {
      comp.getOppGrid().markMiss(row, col);
      user.getPlayerGrid().markMiss(row, col);
      System.out.println("AI tevesztett " + convertIntToLetter(row) + convertCompColToRegular(col));
    }


    System.out.println("\nJelenlegi tabla allasod...Nyomj entert a folyatashoz...");
    reader.nextLine();
    user.getPlayerGrid().printCombined();
    System.out.println("Nyomj entert a folytatashoz...");
    reader.nextLine();
  }

  private static String askForGuess(final Ships p, final Ships opp) {
    System.out.println("Tippeles:");
    p.getOppGrid().printStatus();

    int row;
    int col;

    String oldRow;
    int oldCol;

    while (true) {
      System.out.print("Adjon meg egy sort: (A-J): ");
      String userInputRow = reader.next();
      userInputRow = userInputRow.toUpperCase();
      oldRow = userInputRow;
      row = convertLetterToInt(userInputRow);

      System.out.print("Adjon meg egy oszlopot: (1-10): ");
      col = reader.nextInt();
      oldCol = col;
      col = convertUserColToProCol(col);


      if (col >= 0 && col <= 9 && row != -1) {
        break;
      }

      System.out.println("Helytelen pozicio!");
    }

    if (opp.getPlayerGrid().hasShip(row, col)) {
      p.getOppGrid().markHit(row, col);
      opp.getPlayerGrid().markHit(row, col);
      return "** Talalat! szep munka! " + oldRow + oldCol + " **";
    } else {
      p.getOppGrid().markMiss(row, col);
      opp.getPlayerGrid().markMiss(row, col);
      return "** Melle lottel... " + oldRow + oldCol + " **";
    }
  }

  private static void setup(final Ships p) {
    p.getPlayerGrid().printShips();
    System.out.println();
    int counter = 1;
    int normCounter = 0;
    while (p.numOfShipsLeft() > 0) {
      for (ShipPlacement s : p.getShips()) {
        System.out.println("\nHajo: #" + counter + ": Hossz:" + s.getLength() + "egyseg");
        int row;
        int col;
        int dir;
        while (true) {
          System.out.print("Adjon meg egy sort: (A-J): ");
          String userInputRow = reader.next();
          userInputRow = userInputRow.toUpperCase();
          row = convertLetterToInt(userInputRow);

          System.out.print("Adjon meg egy oszlopot (1-10): ");
          col = reader.nextInt();
          col = convertUserColToProCol(col);

          System.out.print("Milyen iranyba alljon a hajo? (0-H, 1-V): ");
          dir = reader.nextInt();


          if (col >= 0 && col <= 9 && row != -1 && dir != -1) {
            if (!hasErrors(row, col, dir, p, normCounter)) {
              break;
            }
          }

          System.out.println("Helytelen pozicio!");
        }


        p.getShips()[normCounter].setLocation(row, col);
        p.getShips()[normCounter].setDirection(dir);
        p.getPlayerGrid().addShip(p.getShips()[normCounter]);
        p.getPlayerGrid().printShips();
        System.out.println();
        System.out.println("Mar csak " + p.numOfShipsLeft() + " hajot kell letenned.");

        normCounter++;
        counter++;
      }
    }
  }

  private static void setupComputer(final Ships p) {
    System.out.println();
    int normCounter = 0;

    while (p.numOfShipsLeft() > 0) {
      for (ShipPlacement ignored : p.getShips()) {
        int row = Randomizer.nextInt(0, 9);
        int col = Randomizer.nextInt(0, 9);
        int dir = Randomizer.nextInt(0, 1);


        while (hasErrorsComp(row, col, dir, p, normCounter)) {
          row = Randomizer.nextInt(0, 9);
          col = Randomizer.nextInt(0, 9);
          dir = Randomizer.nextInt(0, 1);
        }

        p.getShips()[normCounter].setLocation(row, col);
        p.getShips()[normCounter].setDirection(dir);
        p.getPlayerGrid().addShip(p.getShips()[normCounter]);

        normCounter++;

      }
    }
  }

  private static boolean hasErrors(final int row, final int col, final int dir, final Ships p, final int count) {


    int length = p.getShips()[count].getLength();

    if (dir == 0) {
      int checker = length + col;
      if (checker > 10) {
        System.out.println("Nem fer be a hajo!");
        return true;
      }
    }

    if (dir == 1) {
      int checker = length + row;
      if (checker > 10) {
        System.out.println("Nem fer be a hajo!");
        return true;
      }
    }


    if (dir == 0) {
      for (int i = col; i < col + length; i++) {

        if (p.getPlayerGrid().hasShip(row, i)) {
          System.out.println("Erre a helyre mar adtal meg hajot!");
          return true;
        }
      }
    } else if (dir == 1) {
      for (int i = row; i < row + length; i++) {
        if (p.getPlayerGrid().hasShip(i, col)) {
          System.out.println("Erre a helyre mar adtal meg hajot");
          return true;
        }
      }
    }

    return false;
  }

  private static boolean hasErrorsComp(final int row, final int col, final int dir, final Ships p, final int count) {

    int length = p.getShips()[count].getLength();

    if (dir == 0) {
      int checker = length + col;

      if (checker > 10) {
        return true;
      }
    }

    if (dir == 1) {
      int checker = length + row;
      if (checker > 10) {
        return true;
      }
    }

    if (dir == 0) {
      for (int i = col; i < col + length; i++) {
        if (p.getPlayerGrid().hasShip(row, i)) {
          return true;
        }
      }
    } else if (dir == 1) {

      for (int i = row; i < row + length; i++) {
        if (p.getPlayerGrid().hasShip(i, col)) {
          return true;
        }
      }
    }

    return false;
  }


  private static int convertLetterToInt(final String val) {
    int toReturn;
    switch (val) {
      case "A":
        toReturn = 0;
        break;
      case "B":
        toReturn = 1;
        break;
      case "C":
        toReturn = 2;
        break;
      case "D":
        toReturn = 3;
        break;
      case "E":
        toReturn = 4;
        break;
      case "F":
        toReturn = 5;
        break;
      case "G":
        toReturn = 6;
        break;
      case "H":
        toReturn = 7;
        break;
      case "I":
        toReturn = 8;
        break;
      case "J":
        toReturn = 9;
        break;
      default:
        toReturn = -1;
        break;
    }

    return toReturn;
  }

  private static String convertIntToLetter(final int val) {
    String toReturn;
    switch (val) {
      case 0:
        toReturn = "A";
        break;
      case 1:
        toReturn = "B";
        break;
      case 2:
        toReturn = "C";
        break;
      case 3:
        toReturn = "D";
        break;
      case 4:
        toReturn = "E";
        break;
      case 5:
        toReturn = "F";
        break;
      case 6:
        toReturn = "G";
        break;
      case 7:
        toReturn = "H";
        break;
      case 8:
        toReturn = "I";
        break;
      case 9:
        toReturn = "J";
        break;
      default:
        toReturn = "Z";
        break;
    }

    return toReturn;
  }

  private static int convertUserColToProCol(final int val) {
    int toReturn;
    switch (val) {
      case 1:
        toReturn = 0;
        break;
      case 2:
        toReturn = 1;
        break;
      case 3:
        toReturn = 2;
        break;
      case 4:
        toReturn = 3;
        break;
      case 5:
        toReturn = 4;
        break;
      case 6:
        toReturn = 5;
        break;
      case 7:
        toReturn = 6;
        break;
      case 8:
        toReturn = 7;
        break;
      case 9:
        toReturn = 8;
        break;
      case 10:
        toReturn = 9;
        break;
      default:
        toReturn = -1;
        break;
    }

    return toReturn;
  }

  private static int convertCompColToRegular(final int val) {
    int toReturn;
    switch (val) {
      case 0:
        toReturn = 1;
        break;
      case 1:
        toReturn = 2;
        break;
      case 2:
        toReturn = 3;
        break;
      case 3:
        toReturn = 4;
        break;
      case 4:
        toReturn = 5;
        break;
      case 5:
        toReturn = 6;
        break;
      case 6:
        toReturn = 7;
        break;
      case 7:
        toReturn = 8;
        break;
      case 8:
        toReturn = 9;
        break;
      case 9:
        toReturn = 10;
        break;
      default:
        toReturn = -1;
        break;
    }

    return toReturn;
  }
}
