import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scannerName = new Scanner(System.in);
        System.out.println("Name: ");
        String name = scannerName.nextLine();
        System.out.printf("Welcome, %s! Here are your options: \n", name);
        System.out.println( "   1. Register for the event\n"+
                "   2. See all participants\n"+
                "   3. See participants from your program\n"+
                "   4. Search for participant\n"+
                "   5. See overall program\n"+
                "   6. Exit");
        scannerName.close();
    }
}
