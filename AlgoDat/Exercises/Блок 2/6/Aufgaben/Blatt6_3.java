import java.util.ArrayList;

public class Blatt6_3{

    public static void main(String[] args) {
        // Create hash table (array of lists)
        ArrayList<String>[] hashTable = new ArrayList[8];

        // Init buckets
        for (int i = 0; i < 8; i++) {
            hashTable[i] = new ArrayList<>();
        }

        // Input data
        String[] names = {
            "Patrizia", "Sebastian", "Maike", "Lukas",
            "Nele", "Sarah", "Matthias", "Manuel"
        };

        System.out.println("Insertion log:");
        System.out.println("----------------------");

        // Insert each name
        for (String name : names) {
            int hashValue = calculateHash(name);
            System.out.println("Name: " + name + " -> Hash: " + hashValue);
            hashTable[hashValue].add(name);
        }

        System.out.println("\nFinal Hash Table (Open Hashing):");
        System.out.println("--------------------------------");
        for (int i = 0; i < 8; i++) {
            System.out.println("Index " + i + ": " + hashTable[i]);
        }
    }

    // Hash function: (vowel count + length) % 8
    public static int calculateHash(String s) {
        int length = s.length();
        int vowels = countVowels(s);
        return (vowels + length) % 8;
    }

    // Count vowels
    public static int countVowels(String s) {
        int count = 0;
        String lower = s.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }
}
