import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BarcelonaMetroTimetable {
    private static Map<String, String[]> metroTimetable;
    private static final String[] OFF_PEAK_TIMES = {"06:00", "06:20", "06:40", "07:00", "07:20", "07:40", "08:00"};
    private static final String[] PEAK_TIMES = {"06:00", "06:10", "06:20", "06:30", "06:40", "06:50", "07:00", "07:10", "07:20"};

    public static void main(String[] args) {
        initializeTimetable();
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nWelcome to the Barcelona Metro Timetable!");
            System.out.println("Available lines: L1, L2, L3, L4, L5");
            System.out.println("1. View Timetable");
            System.out.println("2. Find Next Train");
            System.out.println("3. Get Real-Time Updates");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewTimetable(scanner);
                    break;
                case "2":
                    findNextTrain(scanner);
                    break;
                case "3":
                    getRealTimeUpdate(scanner);
                    break;
                case "4":
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (!choice.equals("4"));

        scanner.close();
    }

    private static void viewTimetable(Scanner scanner) {
        System.out.print("Enter the metro line (e.g., L1): ");
        String line = scanner.nextLine().toUpperCase();

        if (metroTimetable.containsKey(line)) {
            System.out.println("Timetable for Line " + line + ":");
            for (String time : metroTimetable.get(line)) {
                System.out.println(time);
            }
        } else {
            System.out.println("Invalid metro line. Please try again.");
        }
    }

    private static void findNextTrain(Scanner scanner) {
        System.out.print("Enter the metro line (e.g., L1): ");
        String line = scanner.nextLine().toUpperCase();

        if (metroTimetable.containsKey(line)) {
            System.out.print("Enter the current time (HH:MM): ");
            String currentTime = scanner.nextLine();
            String nextTrain = getNextTrainTime(metroTimetable.get(line), currentTime);
            if (nextTrain != null) {
                System.out.println("The next train for Line " + line + " is at: " + nextTrain);
            } else {
                System.out.println("No more trains for Line " + line + " today.");
            }
        } else {
            System.out.println("Invalid metro line. Please try again.");
        }
    }

    private static String getNextTrainTime(String[] times, String currentTime) {
        for (String time : times) {
            if (time.compareTo(currentTime) > 0) {
                return time;
            }
        }
        return null; // No more trains today
    }

    private static void getRealTimeUpdate(Scanner scanner) {
        System.out.print("Enter the metro line (e.g., L1): ");
        String line = scanner.nextLine().toUpperCase();

        if (metroTimetable.containsKey(line)) {
            // Simulating real-time updates by providing a random interval update
            System.out.println("Real-time update for Line " + line + ":");
            String[] currentTimes = metroTimetable.get(line);
            System.out.println("The last updated train time is: " + currentTimes[currentTimes.length - 1]);
        } else {
            System.out.println("Invalid metro line. Please try again.");
        }
    }

    private static void initializeTimetable() {
        metroTimetable = new HashMap<>();
        metroTimetable.put("L1", PEAK_TIMES);  // Use PEAK_TIMES for the morning rush
        metroTimetable.put("L2", OFF_PEAK_TIMES);  // Use OFF_PEAK_TIMES for lighter traffic
        metroTimetable.put("L3", PEAK_TIMES);
        metroTimetable.put("L4", OFF_PEAK_TIMES);
        metroTimetable.put("L5", PEAK_TIMES);
    }
}
