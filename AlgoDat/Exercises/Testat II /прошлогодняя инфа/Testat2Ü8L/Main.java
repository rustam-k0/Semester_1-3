package redBlackTree;

import java.util.Random;
import java.io.*;

class IntComparable implements Comparable<IntComparable> {
	private final int value;

	public IntComparable(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(IntComparable other) {
		return Integer.compare(this.value, other.value);
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	// Statische Methode zum Ausführen des Graphviz-Befehls
	private static void executeGraphvizCommand(String cmd) {
		try {
			// Führe den Graphviz-Befehl aus
			Process pr = Runtime.getRuntime().exec(cmd);

			// Erfasse und drucke den Ausgabestrom
			BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}

			// Erfasse und drucke den Fehlerstrom
			BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
			String lineError;
			while ((lineError = error.readLine()) != null) {
				System.out.println(lineError);
			}

			// Warte auf das Ende des Prozesses
			int exitCode = pr.waitFor();
			System.out.println("Graphviz process exited with code " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public class Main {

		public static void main(String[] args) throws IOException {
			RBTree<IntComparable> rbTree = new RBTree<>();
			boolean[] usedNumbers = new boolean[100]; // Annahme: Bereich von 0 bis 99

			// Generiere und füge 15 eindeutige Zufallszahlen in den RB-Baum ein
			Random random = new Random();
			for (int i = 0; i < 15; i++) {
				int randomNumber;
				do {
					// Generiere eine Zufallszahl, bis sie eindeutig ist
					randomNumber = random.nextInt(100); // Bereich nach Bedarf anpassen
				} while (usedNumbers[randomNumber]);

				// Füge die eindeutige Zufallszahl in den RB-Baum ein
				usedNumbers[randomNumber] = true;
				//System.out.println(randomNumber);
				rbTree.insert(new IntComparable(randomNumber));	
			}
			
			// Generiere DOT-Datei
			rbTree.printDOT("RBTree.dot");
			// Führe den Graphviz-Befehl aus, um DOT in SVG zu konvertieren
			String cmd = "D:\\Eclipse\\eclipse-workspace\\Graphviz\\bin\\dot.exe -Tsvg RBTree.dot -o output.svg";
			executeGraphvizCommand(cmd);

			// Öffne den erstellten RB-Baum
			String command = "cmd /c start \"\" \"D:\\Eclipse\\eclipse-workspace\\Übung8\\output.svg\"";
			Process process = Runtime.getRuntime().exec(command);
		}
	}
}
