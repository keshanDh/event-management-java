package Menu;

import DB.Database;

import java.util.*;

public class MainMenu {
    Database db = new Database();
    Map<Integer, String> programs;

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
    }

    private void start() {
        programs = db.getAllPrograms();

        System.out.println("Welcome! Here are your options:");
        System.out.println("1. Sign in\n2. See overall program\n3. Exit");
        Scanner scannerMain = new Scanner(System.in);
        System.out.println("Enter your response: ");
        int option = Integer.parseInt(scannerMain.nextLine());
        
        while (option < 1 || option > 3) {
            System.out.println("Invalid option. Please try again. Enter your response:");
            option = Integer.parseInt(scannerMain.nextLine());
        }

        if (option == 1) {
            signIn();
        } else if (option == 2) {
            seeOverallProgram(null, false);
        } else if (option == 3) {
            exit();
        }
        scannerMain.close();
    }

    private void exit() {
        System.exit(0);
    }

    private void displayMainMenu(Map<String, Object> student, boolean regStatus) {
        Scanner scanner = new Scanner(System.in);

        String notRegistered = "   1. Register for the event\n";
        String registered = "   1. Edit registration for the event\n";

        String registrationMsg = regStatus ? registered : notRegistered;

        System.out.printf("\nWelcome, %s! Here are your options: \n", student.get("student_name"));
        System.out.println(registrationMsg +
                "   2. See all participants\n" +
                "   3. See participants from your program\n" +
                "   4. Search for participant\n" +
                "   5. See overall program\n" +
                "   6. Exit");
        int option = Integer.parseInt(scanner.nextLine());

        while (option < 1 || option > 6) {
            System.out.println("Invalid option. Please try again.");
            option = Integer.parseInt(scanner.nextLine());
        }

        switch (option) {
            case 1:
                if (regStatus) {
                    editRegisterForTheEvent(student);
                } else {
                    registerForTheEvent(student);
                }
                break;
            case 2:
                seeAllParticipants(student, regStatus);
                break;
            case 3:
                seeParticipantsFromYourProgram(student, regStatus);
                break;
            case 4:
                searchForParticipants(student, regStatus);
                break;
            case 5:
                seeOverallProgram(student, regStatus);
                break;
            case 6:
                exit();
                break;
        }
        scanner.close();
    }

    public void signIn() {
        boolean registered = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scanner.nextLine().toLowerCase();

        Map<String, Object> student = db.findStudentIdByName(name);

        if (Objects.isNull(student)) {
            System.out.println("Student not found. Please try again.");
            signIn();
        } else {
            student.put("student_name", name);
            registered = db.checkStudentAlreadyRegistered((Integer) student.get("student_id"));
            displayMainMenu(student, registered);
        }
        scanner.close();
    }

    private void searchForParticipants(Map<String, Object> student, boolean regStatus) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search participant name:\n");
        String name = scanner.nextLine().toLowerCase();
        Map<String, Object> participant = db.searchParticipant(name);
        if (!participant.isEmpty()) {
            System.out.println("Participant " + participant.get("guest_name") + " will attend, as a Guest of " + participant.get("student_name") + ", from the " + programs.get(Integer.parseInt((String) participant.get("program_id"))) + " program.");
        } else {
            System.out.println("Participant not found. Please try again.");
        }
        
        displayMainMenu(student, regStatus);
    }

    private void seeParticipantsFromYourProgram(Map<String, Object> student, boolean regStatus) {
        System.out.println("Here are all the participants from your program: \n");
        List<String> participants = db.getStudentsByProgramFromEvent((Integer) student.get("program_id"));
        for (String participant : participants) {
            System.out.println(participant);
        }

        displayMainMenu(student, regStatus);
    }

    private void seeAllParticipants(Map<String, Object> student, boolean regStatus) {
        System.out.println("Here are all the participants: \n");
        List<String> participants = db.getAllParticipants();
        for (String participant : participants) {
            System.out.println(participant);
        }

        displayMainMenu(student, regStatus);
    }

    private void registerForTheEvent(Map<String, Object> student) {
        System.out.println("Excellent! How many guests do you wish to invite?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> guests = new ArrayList<>();
        int numGuests = Integer.parseInt(scanner.nextLine());

        while (numGuests > 4) {
            System.out.println("Sorry! Maximum limit of guests is four guests for each student\n" +
                    "How many guests do you wish to invite?");
            numGuests = Integer.parseInt(scanner.nextLine());
        }

        for (int i = 0; i < numGuests; i++) {
            System.out.printf("Guest %d name:\n", i + 1);
            guests.add(scanner.nextLine().toLowerCase());
        }

        for (String guest : guests) {
            db.insertEventParticipant((Integer) student.get("student_id"), guest, (Integer) student.get("program_id"));
        }

        System.out.println("Created Entry:");

        System.out.printf("Student: " + student.get("student_name") + " , Program: " + programs.get(student.get("program_id")) + ", Guests: " + guests);

        displayMainMenu(student, true);

        scanner.close();
    }

    private void editRegisterForTheEvent(Map<String, Object> student) {
        db.deleteEventByStudentId((Integer) student.get("student_id"));

        registerForTheEvent(student);
    }

    private void seeOverallProgram(Map<String, Object> student, boolean regStatus) {
        Map<Integer, Integer> programLengths = calculateProgramLength();

        System.out.println("\nStart time: 13.00\n" +
                "Introduction: 30 minutes\n");
        
        for (Map.Entry<Integer, Integer> entry : programLengths.entrySet()) {
            System.out.println(programs.get(entry.getKey()) + " – Resp" + entry.getKey() + " – 1 minute");
            
            if (student!= null && student.get("program_id") == entry.getKey()) {
                System.out.println("== Your registered as part of this program ==");
            }
            
            System.out.println("- " + entry.getValue() + " students - " + entry.getValue()/5 + " minute");
            System.out.println("Break – 5 minutes\n");
        }

        System.out.println("Closing remarks – 15 minutes.\n");
        
        if (student != null) {
            displayMainMenu(student, regStatus);
        } else {
            start();
        }
    }
    
    private Map<Integer, Integer> calculateProgramLength() {
        Map<Integer, Integer> programLengths = new HashMap<>();;
        for (int programId : programs.keySet()) {
            programLengths.put(programId, db.getStudentsByProgramFromEvent(programId).size());
        }
        return programLengths;
    }
}
