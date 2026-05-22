import java.io.UnsupportedEncodingException;
import java.util.*;

class Train {
    private String destination;
    private int trainNumber;
    private String departureTime;
    private int generalSeats;
    private int coupeSeats;
    private int platzkartSeats;
    private int svSeats;

    public Train(String destination, int trainNumber, String departureTime,
                 int generalSeats, int coupeSeats, int platzkartSeats, int svSeats) {
        if (trainNumber <= 0)
            throw new IllegalArgumentException("Номер поїзда має бути додатнім числом!");
        if (!departureTime.matches("\\d{2}:\\d{2}"))
            throw new IllegalArgumentException("Час має бути у форматі HH:MM!");
        String[] parts = departureTime.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        if (h < 0 || h > 23 || m < 0 || m > 59)
            throw new IllegalArgumentException("Недійсний час: " + departureTime);
        if (generalSeats < 0 || coupeSeats < 0 || platzkartSeats < 0 || svSeats < 0)
            throw new IllegalArgumentException("Кількість місць не може бути від'ємною!");

        this.destination = destination;
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.generalSeats = generalSeats;
        this.coupeSeats = coupeSeats;
        this.platzkartSeats = platzkartSeats;
        this.svSeats = svSeats;
    }

    public String getDestination() {
        return destination;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getGeneralSeats() {
        return generalSeats;
    }

    public int getCoupeSeats() {
        return coupeSeats;
    }

    public int getPlatzkartSeats() {
        return platzkartSeats;
    }

    public int getSvSeats() {
        return svSeats;
    }

    public boolean departsAfter(String time) {
        return departureTime.compareTo(time) > 0;
    }

    public boolean hasGeneralSeats() {
        return generalSeats > 0;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %6d | %8s | %8d | %6d | %9d | %4d",
                destination, trainNumber, departureTime,
                generalSeats, coupeSeats, platzkartSeats, svSeats);
    }
}

class TrainStation {
    private Train[] trains;
    private int count;

    public TrainStation(int capacity) {
        trains = new Train[capacity];
        count = 0;
    }

    public void addTrain(Train t) {
        if (count < trains.length) {
            trains[count++] = t;
        }
    }

    public Train[] getTrainsWithGeneralSeats() {
        List<Train> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (trains[i].hasGeneralSeats()) {
                result.add(trains[i]);
            }
        }
        return result.toArray(new Train[0]);
    }

    public Train[] getTrainsByDestinationAndTime(String destination, String time) {
        List<Train> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (trains[i].getDestination().equalsIgnoreCase(destination)
                    && trains[i].departsAfter(time)) {
                result.add(trains[i]);
            }
        }
        return result.toArray(new Train[0]);
    }

    public void printAll() {
        String sep = "-".repeat(75);
        String header = String.format("%-20s | %6s | %8s | %8s | %6s | %9s | %4s",
                "Пункт призначення", "№поїзда", "Час відпр.", "Загальні", "Купе", "Плацкарт", "СВ");
        System.out.println(sep);
        System.out.println(header);
        System.out.println(sep);
        for (int i = 0; i < count; i++) {
            System.out.println(trains[i]);
        }
        System.out.println(sep);
    }
}

public class Lab3 {

    static final String HEADER =
            String.format("%-20s | %6s | %8s | %8s | %6s | %9s | %4s",
                    "Пункт призначення", "№поїзда", "Час відпр.", "Загальні", "Купе", "Плацкарт", "СВ");
    static final String SEP = "-".repeat(75);

    static void printHeader() {
        System.out.println(SEP);
        System.out.println(HEADER);
        System.out.println(SEP);
    }

    static void printSeparator() {
        System.out.println(SEP);
    }

    static void printResults(Train[] results) {
        if (results.length == 0) {
            System.out.println("  Результат пошуку: поїздів за заданим критерієм не знайдено.");
        } else {
            printHeader();
            for (Train t : results) System.out.println(t);
            printSeparator();
            System.out.println("  Знайдено поїздів: " + results.length);
        }
    }

    static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("  [Помилка] Введення не може бути порожнім. Спробуйте ще раз.");
        }
    }

    static String readTime(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                if (!s.matches("\\d{2}:\\d{2}"))
                    throw new IllegalArgumentException("Формат має бути HH:MM (наприклад 08:30)");
                String[] p = s.split(":");
                int h = Integer.parseInt(p[0]), m = Integer.parseInt(p[1]);
                if (h < 0 || h > 23 || m < 0 || m > 59)
                    throw new IllegalArgumentException("Недійсний час: " + s);
                return s;
            } catch (IllegalArgumentException e) {
                System.out.println("  [Помилка] " + e.getMessage() + ". Спробуйте ще раз.");
            }
        }
    }

    static int readNonNegativeInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val < 0) throw new IllegalArgumentException("Значення не може бути від'ємним!");
                return val;
            } catch (NumberFormatException e) {
                System.out.println("  [Помилка] Введіть ціле число. Спробуйте ще раз.");
            } catch (IllegalArgumentException e) {
                System.out.println("  [Помилка] " + e.getMessage() + " Спробуйте ще раз.");
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner sc = new Scanner(System.in);

        System.out.println("=".repeat(75));
        System.out.println("  Розробник: Іванов І. І.");
        System.out.println("  Лабораторна робота №3, Варіант №8 — Залізничне розкладання");
        System.out.println("=".repeat(75));

        TrainStation station = new TrainStation(10);

        station.addTrain(new Train("Київ", 101, "06:30", 60, 36, 54, 2));
        station.addTrain(new Train("Львів", 203, "08:15", 0, 36, 54, 4));
        station.addTrain(new Train("Харків", 315, "11:00", 80, 0, 54, 0));
        station.addTrain(new Train("Одеса", 422, "14:45", 0, 36, 0, 2));
        station.addTrain(new Train("Дніпро", 518, "17:20", 40, 36, 54, 0));

        System.out.println("\nЗагружено 5 поїздів за замовчуванням.");

        System.out.print("\nБажаєте додати поїзд вручну? (так/ні): ");
        if (sc.nextLine().trim().equalsIgnoreCase("так")) {
            while (true) {
                System.out.println("\n--- Введення нового поїзда ---");
                try {
                    String dest = readNonEmpty(sc, "  Пункт призначення: ");
                    int num = readNonNegativeInt(sc, "  Номер поїзда: ");
                    String time = readTime(sc, "  Час відправлення (HH:MM): ");
                    int gen = readNonNegativeInt(sc, "  Загальні місця: ");
                    int coupe = readNonNegativeInt(sc, "  Купе: ");
                    int platz = readNonNegativeInt(sc, "  Плацкарт: ");
                    int sv = readNonNegativeInt(sc, "  СВ: ");
                    station.addTrain(new Train(dest, num, time, gen, coupe, platz, sv));
                    System.out.println("  Поїзд успішно додано!");
                } catch (IllegalArgumentException e) {
                    System.out.println("  [Помилка при створенні] " + e.getMessage());
                }
                System.out.print("  Додати ще один поїзд? (так/ні): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("так")) break;
            }
        }

        System.out.println("\n=== УВЕСЬ РОЗКЛАД ПОЇЗДІВ ===");
        station.printAll();

        System.out.println("\n=== ЗАВДАННЯ 1: Поїзди із загальними місцями ===");
        Train[] withGeneral = station.getTrainsWithGeneralSeats();
        printResults(withGeneral);

        System.out.println("\n=== ЗАВДАННЯ 2: Пошук за пунктом призначення та часом ===");
        String dest = readNonEmpty(sc, "  Введіть пункт призначення: ");
        String time = readTime(sc, "  Введіть час (поїзди ПІСЛЯ нього, HH:MM): ");
        Train[] found = station.getTrainsByDestinationAndTime(dest, time);
        System.out.printf("%nПоїзди до \"%s\" після %s:%n", dest, time);
        printResults(found);

        System.out.println("\n" + "=".repeat(75));
        System.out.println("  Програму завершено.");
        sc.close();
    }
}