import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Blatt6_2{

    public static void main(String[] args) {
        // Input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N (which Fibonacci number you want): ");
        int n = scanner.nextInt();

        // Base case
        if (n <= 2) {
            System.out.println("Result: 1");
            return;
        }

        // Queue for sliding window of two numbers
        Queue<Long> fibQueue = new LinkedList<>();

        // Init
        fibQueue.add(1L);
        fibQueue.add(1L);

        // Core loop
        for (int i = 3; i <= n; i++) {
            long first = fibQueue.poll();   // remove old head
            long second = fibQueue.peek();  // view current head
            long newNumber = first + second;
            fibQueue.add(newNumber);        // push new tail
        }

        // Extract result
        fibQueue.poll();            // drop f(n-1)
        long result = fibQueue.poll(); // get f(n)

        System.out.println("Fibonacci number: " + result);
    }
}
