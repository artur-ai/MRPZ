import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab5 {
    static class City {
        private String name;
        private String country;
        private int population;
        private List<InfraElement> infrastructure;

        public City(String name, String country, int population) {
            this.name = name;
            this.country = country;
            this.population = population;
            this.infrastructure = new ArrayList<>();
            System.out.println("[СТВОРЕНО] Місто: " + name + " (" + country + ")");
        }

        public void addElement(String type, String elementName) {
            InfraElement element = new InfraElement(type, elementName);
            infrastructure.add(element);
            System.out.println("[ДОДАНО] " + type + ": \"" + elementName + "\" → місто " + name);
        }

        public String getName() {
            return name;
        }

        public List<InfraElement> getInfrastructure() {
            return infrastructure;
        }

        public void printInfo() {
            System.out.println("  Місто:      " + name);
            System.out.println("  Країна:     " + country);
            System.out.println("  Населення:  " + population + " осіб");
            System.out.println("  Елементів:  " + infrastructure.size());
        }

        public List<InfraElement> searchByType(String type) {
            List<InfraElement> result = new ArrayList<>();
            for (InfraElement e : infrastructure)
                if (e.getType().equalsIgnoreCase(type)) result.add(e);
            return result;
        }

        public List<InfraElement> searchByKeyword(String keyword) {
            List<InfraElement> result = new ArrayList<>();
            for (InfraElement e : infrastructure)
                if (e.getElementName().toLowerCase().contains(keyword.toLowerCase())) result.add(e);
            return result;
        }

        class InfraElement {
            private String type;
            private String elementName;

            public InfraElement(String type, String elementName) {
                this.type = type;
                this.elementName = elementName;
            }

            public String getType() {
                return type;
            }

            public String getElementName() {
                return elementName;
            }

            public void printInfo() {
                System.out.printf("  %-12s | %-30s | місто: %s%n", type, elementName, City.this.name);
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Лабораторна робота №5. Вкладені класи ===");
        System.out.println("    Зовнішній клас: Місто");
        System.out.println("    Внутрішній клас: Елемент_Інфраструктури\n");

        System.out.print("Скільки міст додати? ");
        int cityCount = Integer.parseInt(scanner.nextLine().trim());

        List<City> cities = new ArrayList<>();

        for (int i = 0; i < cityCount; i++) {
            System.out.println("\n--- Місто " + (i + 1) + " ---");
            System.out.print("Назва міста: ");
            String name = scanner.nextLine().trim();
            System.out.print("Країна: ");
            String country = scanner.nextLine().trim();
            System.out.print("Населення: ");
            int population = Integer.parseInt(scanner.nextLine().trim());

            City city = new City(name, country, population);

            System.out.print("Кількість елементів інфраструктури: ");
            int elCount = Integer.parseInt(scanner.nextLine().trim());

            for (int j = 0; j < elCount; j++) {
                System.out.println("  Елемент " + (j + 1) + ":");
                System.out.print("  Тип (проспект/вулиця/площа): ");
                String type = scanner.nextLine().trim();
                System.out.print("  Назва: ");
                String elName = scanner.nextLine().trim();
                city.addElement(type, elName);
            }

            cities.add(city);
        }

        System.out.println("\n\n=== УСІ МІСТА ТА ЇХ ІНФРАСТРУКТУРА ===");
        for (City c : cities) {
            c.printInfo();
            System.out.println("  Елементи інфраструктури:");
            if (c.getInfrastructure().isEmpty()) {
                System.out.println("  (немає)");
            } else {
                for (City.InfraElement e : c.getInfrastructure()) {
                    e.printInfo();
                }
            }
        }

        System.out.println("\n=== ПОШУК ===");
        System.out.println("1 - За типом (проспект/вулиця/площа)");
        System.out.println("2 - За ключовим словом у назві");
        System.out.print("Ваш вибір: ");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        if (choice == 1) {
            System.out.print("Введіть тип: ");
            String type = scanner.nextLine().trim();
            System.out.println("\n[ПОШУК] Елементи типу \"" + type + "\":");
            boolean found = false;
            for (City c : cities) {
                for (City.InfraElement e : c.searchByType(type)) {
                    e.printInfo();
                    found = true;
                }
            }
            if (!found) System.out.println("  Нічого не знайдено.");

        } else {
            System.out.print("Введіть ключове слово: ");
            String keyword = scanner.nextLine().trim();
            System.out.println("\n[ПОШУК] Елементи з назвою що містить \"" + keyword + "\":");
            boolean found = false;
            for (City c : cities) {
                for (City.InfraElement e : c.searchByKeyword(keyword)) {
                    e.printInfo();
                    found = true;
                }
            }
            if (!found) System.out.println("  Нічого не знайдено.");
        }

        System.out.println("\n=== Роботу завершено ===");
        scanner.close();
    }
}
