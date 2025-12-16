package redBlackTree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RBTree<T extends Comparable<T>> {

	// Konstanten für die Farben der Red-Black Tree-Knoten
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	// Die Wurzel des Red-Black Baums
	private Node root;

	// Innere Klasse, die einen Baumknoten repräsentiert
	private class Node {
		T data;
		Node left, right, parent;
		boolean color;

		// Konstruktor für einen Baumknoten
		public Node(T data) {
			this.data = data;
			this.color = RED;
		}
	}

    // Methode zum Einfügen eines neuen Schlüssels in den Red-Black Baum
	public void insert(T key) {
		Node node = root;
		Node parent = null;

        // Traverse den Baum nach links oder rechts, abhängig vom Schlüssel
		while (node != null) {
			parent = node;
			int cmp = key.compareTo(node.data);
			if (cmp <= 0) {
                // Erlaube doppelte Schlüssel auf der linken Seite
				node = node.left;
			} else {
				node = node.right;
			}
		}

        // Füge neuen Knoten ein
		Node newNode = new Node(key);
		newNode.color = RED;

		if (parent == null) {
			root = newNode;
		} else {
			int cmp = key.compareTo(parent.data);
			if (cmp <= 0) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
		}

		newNode.parent = parent;
		fixRedBlackPropertiesAfterInsert(newNode);
	}

	// Mache die Wurzel des Baums schwarz    Fall 1 und 2
	private void makeRootBlack() {
		if (root != null && root.color != BLACK) {
			root.color = BLACK;
		}
	}

	// Regeln für die Red-Black Tree-Eigenschaften nach dem Einfügen eines neuen Knotens
	private void fixRedBlackPropertiesAfterInsert(Node insertedNode) {
		insertedNode.color = RED; // Setze die Farbe des eingefügten Knotens auf ROT

		while (insertedNode != null && insertedNode.parent != null && insertedNode.parent.color == RED) {
			Node parent = insertedNode.parent;
			Node grandparent = parent.parent;

			if (parent == grandparent.left) {
				Node uncle = grandparent.right;

				if (uncle != null && uncle.color == RED) {
					// Fall 3: Vater und Onkelknoten sind rot
					parent.color = BLACK;
					uncle.color = BLACK;
					grandparent.color = RED;
					insertedNode = grandparent; // Gehe zum Großvater-Knoten hoch
				} else {
					// Fall 4: Vater ist rot, Onkel ist schwarz, Knoten ist "innerer Enkel"
					if (insertedNode == parent.right) {
						rotateLeft(parent);
						insertedNode = parent;
						parent = insertedNode.parent;
					}

					 // Fall 5: Vater ist rot, Onkel ist schwarz, Knoten ist "äußerer Enkel"
					parent.color = BLACK;
					grandparent.color = RED;
					rotateRight(grandparent);
				}
			} else {
				// Spiegelbildliche Fälle für den rechten Teilbaum
				Node uncle = grandparent.left;

				if (uncle != null && uncle.color == RED) {
					 // Fall 3: Vater und Onkelknoten sind rot
					parent.color = BLACK;
					uncle.color = BLACK;
					grandparent.color = RED;
					insertedNode = grandparent; // Gehe zum Großvater-Knoten hoch
				} else {
					// Fall 4: Vater ist rot, Onkel ist schwarz, Knoten ist "innerer Enkel"
					if (insertedNode == parent.left) {
						rotateRight(parent);
						insertedNode = parent;
						parent = insertedNode.parent;
					}

					 // Fall 5: Vater ist rot, Onkel ist schwarz, Knoten ist "äußerer Enkel"
					parent.color = BLACK;
					grandparent.color = RED;
					rotateLeft(grandparent);
				}
			}
		}
		makeRootBlack();
	}

	// Rotiere nach links um den gegebenen Knoten
	private void rotateLeft(Node x) {
		if (x != null) {
			Node y = x.right;
			x.right = y.left;

			if (y.left != null) {
				y.left.parent = x;
			}

			y.parent = x.parent;

			if (x.parent == null) {
				root = y;
			} else if (x == x.parent.left) {
				x.parent.left = y;
			} else {
				x.parent.right = y;
			}

			y.left = x;
			x.parent = y;
		}
	}

	// Rotiere nach rechts um den gegebenen Knoten
	private void rotateRight(Node y) {
		if (y != null) {
			Node x = y.left;
			y.left = x.right;

			if (x.right != null) {
				x.right.parent = y;
			}

			x.parent = y.parent;

			if (y.parent == null) {
				root = x;
			} else if (y == y.parent.right) {
				y.parent.right = x;
			} else {
				y.parent.left = x;
			}

			x.right = y;
			y.parent = x;
		}
	}

	
	// Erzeuge die DOT-Datei für die Visualisierung des Red-Black Baums
	public void printDOT(String filePath) {								
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write("digraph {\n");
			writer.write("graph [ratio=.48];\n");
			writer.write("node [style=filled, color=black, shape=circle, width=.6\n");
			writer.write("fontname=Helvetica, fontweight=bold, fontcolor=white,\n");
			writer.write("fontsize=24, fixedsize=true];\n");
			writer.write("{rank = same; " +  listNilNodes() + "};\n");

			writer.write(listNilNodes() + "\n");
			writer.write("[label=\"NIL\", shape=record, width=.4,height=.25, fontsize=16];\n");
			
			writer.write(listConnections() + "\n");
			
			writer.write(listRedNodes() + "\n");
			writer.write("[fillcolor=red];\n");

			writer.write("}\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String listNilNodes() {
		List<String> nilNodesList = new ArrayList<>();
		listNilNodes(root, nilNodesList);
		return String.join(", ", nilNodesList);
	}

	private void listNilNodes(Node node, List<String> nilNodesList) {
		if (node == null) {
			nilNodesList.add("n" + nilNodesList.size());
		} else {
			listNilNodes(node.left, nilNodesList);
			listNilNodes(node.right, nilNodesList);
		}
	}

	public String listRedNodes() {
		List<String> redNodesList = new ArrayList<>();
		listRedNodes(root, redNodesList);
		return String.join(", ", redNodesList);
	}

	private void listRedNodes(Node node, List<String> redNodesList) {
		if (node != null && node.color == RED) {
			redNodesList.add(node.data.toString());
		}

		if (node != null) {
			listRedNodes(node.left, redNodesList);
			listRedNodes(node.right, redNodesList);
		}
	}

	public String listConnections() {
		List<String> connectionsList = new ArrayList<>();
		List<String> nilNodesList = new ArrayList<>();
		listNilNodes(root, nilNodesList);

		listConnections(root, nilNodesList, connectionsList);
		return String.join("\n", connectionsList);
	}

	private void listConnections(Node node, List<String> nilNodesList, List<String> connectionsList) {
		if (node != null) {
			String nodeLabel = node.data.toString();
			String leftConnection = nodeLabel + " -> " + (node.left != null ? node.left.data.toString() : nilNodesList.remove(0));
			String rightConnection = nodeLabel + " -> " + (node.right != null ? node.right.data.toString() : nilNodesList.remove(0));

			connectionsList.add(leftConnection);
			connectionsList.add(rightConnection);

			listConnections(node.left, nilNodesList, connectionsList);
			listConnections(node.right, nilNodesList, connectionsList);
		}
	}
}
