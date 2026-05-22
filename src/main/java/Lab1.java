import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.Random;

public class Lab1 {
    public static void printMatrix(int[][] matrix, String name) {
        System.out.println("Матриця " + name + ":");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(int[] array, String name) {
        System.out.println("Масив " + name + ":");
        if (array.length == 0) {
            System.out.println("  (порожній — немає елементів менших за Y)");
        } else {
            for (int val : array) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть значення змінної Y: ");
        int Y = scanner.nextInt();
        System.out.println();

        Random random = new Random();
        int rows = 4;
        int cols = 2;
        int[][] A = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] = random.nextInt(31) - 10;
            }
        }

        System.out.println("--- До обробки ---");
        printMatrix(A, "A(4,2)");

        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A[i][j] < Y) {
                    count++;
                }
            }
        }

        int[] B = new int[count];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (A[i][j] < Y) {
                    B[index++] = A[i][j];
                }
            }
        }

        long product = 1;
        if (count > 0) {
            for (int val : B) {
                product *= val;
            }
        }

        System.out.println("--- Результати обробки (елементи < Y = " + Y + ") ---\n");
        printMatrix(A, "A (після обробки — незмінна)");
        printArray(B, "B");

        if (count > 0) {
            System.out.println("Добуток елементів масиву B: " + product);
        } else {
            System.out.println("Добуток не визначено: немає елементів менших за Y = " + Y);
        }
        scanner.close();
    }
}