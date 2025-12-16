import java.util.Arrays;

public class Blatt6_4 {

    public static void main(String[] args) {
        // Input data: keys and table size
        int[] keys = {1001, 1542, 429, 1320, 17, 900, 417, 2302, 1920};
        int N = 10;

        // Initialize tables with -1 (empty)
        int[] tableLinear = new int[N];
        int[] tableQuadratic = new int[N];
        Arrays.fill(tableLinear, -1);
        Arrays.fill(tableQuadratic, -1);

        System.out.println("--- START ---");

        // --- PART 1: Linear Probing ---
        System.out.println("\n1. Linear Probing:");
        for (int key : keys) {
            int hashIndex = (key / 100) % N;
            int attempt = 0;
            boolean inserted = false;

            while (attempt < N) {
                int currentIndex = (hashIndex + attempt) % N;
                if (tableLinear[currentIndex] == -1) {
                    tableLinear[currentIndex] = key;
                    inserted = true;
                    break;
                }
                attempt++;
            }

            if (!inserted) {
                System.out.println("Failed to insert key " + key + " (table full)");
            }
        }
        System.out.println("Linear Result: " + Arrays.toString(tableLinear));

        // --- PART 2: Quadratic Probing ---
        System.out.println("\n2. Quadratic Probing:");
        for (int key : keys) {
            int hashIndex = (key / 100) % N;
            int attempt = 0;
            boolean inserted = false;

            while (attempt < 20) { // limit attempts to prevent infinite loop
                int currentIndex = (hashIndex + attempt * attempt) % N;
                if (tableQuadratic[currentIndex] == -1) {
                    tableQuadratic[currentIndex] = key;
                    inserted = true;
                    break;
                }
                attempt++;
            }

            if (!inserted) {
                System.out.println("WARNING: Failed to insert key " + key + " (loop or table full)");
            }
        }
        System.out.println("Quadratic Result: " + Arrays.toString(tableQuadratic));
    }
}
