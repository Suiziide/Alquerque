import java.util.Scanner;
public class Alquerque {
    public static void main(String[] args) {
        String playerOneName;
        String playerTwoName;
        boolean isOneAi;
        boolean isTwoAi;



    }
    private static void init() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Alquerque, Master");
        int option;
        do {
            printOptions();
            option = reader.nextInt();
            switch (option) {
                case 0:
                    System.out.println("You have chosen option " + option + " : Player vs Player");
                    System.out.print(" ");
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Invalid option, " + option + " is not a valid option");
            }
        } while (option > 3 || option < 0);
    }
    public static void printOptions() {
        System.out.println("What do you wish to do?");
        System.out.println();
        System.out.println("Option 1: Player vs Player");
        System.out.println("Option 2: Player vs CPU");
        System.out.println("Option 3: CPU vs CPU");
        System.out.println();
        System.out.println("Please enter the number corresponding to the option you want executed");
    }
}
