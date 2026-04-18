import java.util.Random;

public class Lab2 {
    public static String processNumber(String numStr) {
        char[] digits = numStr.toCharArray();

        for (int i = 0; i < digits.length; i++) {
            int digit = Character.getNumericValue(digits[i]);
            if (digit % 2 == 0) {
                digits[i] = Character.forDigit(digit / 2, 10);
            }
        }

        char temp = digits[0];
        digits[0] = digits[1];
        digits[1] = temp;

        return new String(digits);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int number = 10000 + random.nextInt(90000);

        String original = String.valueOf(number);

        System.out.println("Вихідне число:        " + original);

        char[] afterEven = original.toCharArray();
        StringBuilder stepInfo = new StringBuilder();

        System.out.println("\n--- Крок 1: зменшення парних цифр вдвічі ---");
        for (int i = 0; i < afterEven.length; i++) {
            int digit = Character.getNumericValue(afterEven[i]);
            if (digit % 2 == 0) {
                int newDigit = digit / 2;
                System.out.printf("  Позиція %d: %d (парна) → %d%n", i + 1, digit, newDigit);
                afterEven[i] = Character.forDigit(newDigit, 10);
            } else {
                System.out.printf("  Позиція %d: %d (непарна) → без змін%n", i + 1, digit);
            }
        }
        String afterStep1 = new String(afterEven);
        System.out.println("Після кроку 1:        " + afterStep1);

        System.out.println("\n--- Крок 2: обмін 1-ї та 2-ї цифр ---");
        System.out.printf("  Позиція 1 (%c) ↔ Позиція 2 (%c)%n", afterEven[0], afterEven[1]);
        char temp = afterEven[0];
        afterEven[0] = afterEven[1];
        afterEven[1] = temp;
        String result = new String(afterEven);
        System.out.println("Після кроку 2:        " + result);

        System.out.println("\n============================================");
        System.out.println("  РЕЗУЛЬТАТ:");
        System.out.println("  Вихідне число:  " + original);
        System.out.println("  Оброблене число: " + result);
        System.out.println("============================================");

        System.out.println("\n--- Перевірка на прикладі з умови: 64583 ---");
        String test = "64583";
        String testResult = processNumber(test);
        System.out.println("  Вхід:  " + test);
        System.out.println("  Вихід: " + testResult);
        System.out.println("  Очікується: 23543");
        System.out.println("  " + (testResult.equals("23543") ? "✓ Правильно!" : "✗ Помилка!"));
    }
}