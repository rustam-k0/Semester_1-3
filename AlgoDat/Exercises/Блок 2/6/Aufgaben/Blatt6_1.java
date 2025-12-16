import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Blatt6_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("N: ");
        int n = scanner.nextInt();
        solve(n);
        scanner.close();
    }
    
    public static void solve(int n) {
        // Standard FIFO queue
        Queue<Long> queue = new ArrayDeque<>();
        
        // Start value
        queue.add(9L);

        while (!queue.isEmpty()) {
            long current = queue.remove(); // take head (FIFO)

            if (current % n == 0) {
                System.out.println("Result: " + current);
                return;
            }

            // Append candidates
            queue.add(current * 10);      // add 0
            queue.add(current * 10 + 9);  // add 9
        }
    }
}
