import java.util.Scanner;

public class EventManagement {
    public static void main(String[] args) {
        String name;
        System.out.println("Welcome! Here are your options:");
        System.out.println("1. Sign in\n2. See overall program\n3. Exit");
        Scanner scannerMain = new Scanner(System.in);
        System.out.println("Enter your response: ");
        int option = Integer.parseInt(scannerMain.nextLine());

        if (option == 1) {signIn();}
        if (option == 2) {seeOverallProgram();}
        if (option == 3) {exit();}
        scannerMain.close();
    }

    private static void exit() {
    }

    private static void seeOverallProgram() {

        if (management) {
            System.out.printf("Start time: 13.00\n" +
                    "Introduction: 30 minutes\n"+
                    "Management – Resp1 – 1 minute\n" +
                    "== Your registered as part of this program ==\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Engineering – Resp2 – 1 minute\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Medicine – Resp3 – 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Art – Resp4 – 1 minute\n" +
                    "Closing remarks – 15 minutes.");
        }
        if (Engineering) {
            System.out.printf("Start time: 13.00\n" +
                    "Introduction: 30 minutes\n"+
                    "Management – Resp1 – 1 minute\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Engineering – Resp2 – 1 minute\n" +
                    "== Your registered as part of this program ==\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Medicine – Resp3 – 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Art – Resp4 – 1 minute\n" +
                    "Closing remarks – 15 minutes.");
        }
        if (Medicine) {
            System.out.printf("Start time: 13.00\n" +
                    "Introduction: 30 minutes\n"+
                    "Management – Resp1 – 1 minute\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Engineering – Resp2 – 1 minute\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Medicine – Resp3 – 1 minute\n" +
                    "== Your registered as part of this program ==\n" +
                    "Break – 5 minutes\n" +
                    "Art – Resp4 – 1 minute\n" +
                    "Closing remarks – 15 minutes.");
        }
        if (Arts) {
            System.out.printf("Start time: 13.00\n" +
                    "Introduction: 30 minutes\n"+
                    "Management – Resp1 – 1 minute\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Engineering – Resp2 – 1 minute\n" +
                    "- 1 student - 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Medicine – Resp3 – 1 minute\n" +
                    "Break – 5 minutes\n" +
                    "Art – Resp4 – 1 minute\n" +
                    "== Your registered as part of this program ==\n" +
                    "Closing remarks – 15 minutes.");
        }


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
        boolean state = true;
        Scanner scanner = new Scanner(System.in);
        while (state) {
            int option1_1 = scanner.nextInt();
            if (option1_1 <= 4) {
                for (int i = 0; i < option1_1+1; i++) {
                    String guestName = scanner.nextLine();
                    if (i < option1_1) {
                        System.out.printf("Guest %d name:\n", i+1);
                    }
                }
                state =false;
            } else {
                System.out.println("Sorry! Maximum limit of guests is four guests for each student\n"+
                        "How many guests do you wish to invite?");
            }
        }
        System.out.println("Created Entry:");
        //Blank: From database or the above????

        afterRegistration();
        scanner.close();
    }

    private static void afterRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Welcome, %s! Here are your options: \n", name);
        System.out.println( "   1. Edit registration for the event\n"+
                "   2. See all participants\n"+
                "   3. See participants from your program\n"+
                "   4. Search for participant\n"+
                "   5. See overall program\n"+
                "   6. Exit");
        int option2 = scanner.nextInt();
        if (option2 == 1) {editRegisterForTheEvent();}
        if (option2 == 2) {seeAllParticipants();}
        if (option2 == 3) {seeParticipantsFromYourProgram();}
        if (option2 == 4) {searchForParticipants();}
        if (option2 == 5) {seeOverallProgram();}
        if (option2 == 6) {exit();}
    }

    private static void editRegisterForTheEvent() {

    }


}
