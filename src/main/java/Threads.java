public class Threads {
    static class NumberPrinter extends Thread {
        private final int number;
        private final int delaySeconds;
        private final int repeatCount;

        public NumberPrinter(int number, int delaySeconds, int repeatCount) {
            this.number = number;
            this.delaySeconds = delaySeconds;
            this.repeatCount = repeatCount;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < repeatCount; i++) {
                    Thread.sleep(delaySeconds * 1000L);
                    System.out.println("Потік-" + number + " → " + number
                            + "  [час: " + (System.currentTimeMillis() / 1000 % 1000) + "с]");
                }
            } catch (InterruptedException e) {
                System.out.println("Потік-" + number + " перервано: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=".repeat(50));
        System.out.println("  Багатопотокова програма запущена");
        System.out.println("=".repeat(50));
        System.out.println("  Потік 1: виводить «1» кожну 1 секунду");
        System.out.println("  Потік 2: виводить «2» кожні 2 секунди");
        System.out.println("  Потік 3: виводить «3» кожні 3 секунди");
        System.out.println("=".repeat(50) + "\n");

        Thread t1 = new NumberPrinter(1, 1, 6);
        Thread t2 = new NumberPrinter(2, 2, 3);
        Thread t3 = new NumberPrinter(3, 3, 2);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("  Усі потоки завершили роботу.");
        System.out.println("=".repeat(50));
    }
}