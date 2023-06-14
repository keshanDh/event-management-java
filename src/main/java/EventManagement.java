import java.util.Scanner;

public class EventManagement {
    public static void main(String[] args) {
        System.out.println("Welcome! Here are your options:");
        System.out.println("1. Sign in\n2. See overall program\n3. Exit");
        Scanner scannerMain = new Scanner(System.in);
        System.out.println("Enter your response: ");
        int option = Integer.parseInt(scannerMain.nextLine());
        scannerMain.close();

        if (option == 1) {signIn();}
        if (option == 2) {seeOverallProgram();}
        if (option == 3) {exit();}
    }

    private static void exit() {
    }

    private static void seeOverallProgram() {

    }

    public static void signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.printf("Welcome, %s! Here are your options: \n", name);
        System.out.println( "   1. Register for the event\n"+
                "   2. See all participants\n"+
                "   3. See participants from your program\n"+
                "   4. Search for participant\n"+
                "   5. See overall program\n"+
                "   6. Exit");
        int option1 = scanner.nextInt();
        if (option1 == 1) {registerForTheEvent();}
        if (option1 == 2) {seeAllParticipants();}
        if (option1 == 3) {seeParticipantsFromYourProgram();}
        if (option1 == 4) {searchForParticipants();}
        if (option1 == 5) {seeOverallProgram();}
        if (option1 == 6) {exit();}
        scanner.close();

    }

    private static void searchForParticipants() {
    }

    private static void seeParticipantsFromYourProgram() {

    }

    private static void seeAllParticipants() {

    }

    private static void registerForTheEvent() {
        System.out.println("Excellent! How many guests do you wish to invite?");
        Scanner scanner = new Scanner(System.in);
        int option1_1 = scanner.nextInt();
        if (option1_1 <= 4) {
            for (int i = 0; i < option1_1; i++) {
                System.out.printf("Guest %d name\n", option1_1);
            }
        } else {
            System.out.println("Sorry! Maximum limit of guests is four guests for each student");
        }

    }

}
