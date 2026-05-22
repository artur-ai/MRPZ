import java.util.ArrayList;
import java.util.Scanner;

public class Lab6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Лабораторна робота №6. Колекції Java (ArrayList) ===\n");

        System.out.print("Введіть розмірність списку: ");
        int size = Integer.parseInt(scanner.nextLine().trim());

        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("Введіть " + size + " цілих чисел:");
        for (int i = 0; i < size; i++) {
            System.out.print("  Елемент [" + i + "]: ");
            int val = Integer.parseInt(scanner.nextLine().trim());
            list.add(val);
        }

        System.out.println("\n[ІНФО] Список створено. Розмір: " + list.size());
        System.out.println("[ІНФО] Початковий список: " + list);

        int n = -1;
        while (n < 0 || n >= size) {
            System.out.print("\nВведіть N для зсуву вправо (0 < N < " + size + "): ");
            n = Integer.parseInt(scanner.nextLine().trim());
            if (n < 0 || n >= size) {
                System.out.println("[ПОМИЛКА] N повинно бути в межах від 0 до " + (size - 1));
            }
        }

        System.out.println("[ІНФО] Зсув на N = " + n + " позицій вправо");

        ArrayList<Integer> shifted = cyclicShiftRight(list, n);

        System.out.println("\n=== РЕЗУЛЬТАТ ===");
        System.out.println("Початковий список: " + list);
        System.out.println("Після зсуву вправо на " + n + ": " + shifted);

        System.out.println("\n=== Покрокова демонстрація зсуву ===");
        ArrayList<Integer> step = new ArrayList<>(list);
        for (int i = 0; i < n; i++) {
            int last = step.remove(step.size() - 1);
            step.add(0, last);
            System.out.println("  Крок " + (i + 1) + ": " + step);
        }

        System.out.println("\n=== Роботу завершено ===");
        scanner.close();
    }

    static ArrayList<Integer> cyclicShiftRight(ArrayList<Integer> list, int n) {
        int size = list.size();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = size - n; i < size; i++) {
            result.add(list.get(i));
        }
        for (int i = 0; i < size - n; i++) {
            result.add(list.get(i));
        }

        return result;
    }
}
