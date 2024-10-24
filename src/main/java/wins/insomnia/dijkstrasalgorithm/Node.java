package wins.insomnia.dijkstrasalgorithm;

import java.util.HashMap;
import java.util.LinkedList;

public class Node implements Comparable<Node> {

	public final String NAME;
	public int distance = Integer.MAX_VALUE;
	public LinkedList<Node> shortestPath = new LinkedList<>();
	public HashMap<Node, Integer> adjacentNodes = new HashMap<>();

	public Node(String name) {
		this.NAME = name;
	}

	// make two-way connection
	public void connectWithNode(Node connectingNode, int cost) {
		adjacentNodes.put(connectingNode, cost);
		connectingNode.adjacentNodes.put(this, cost);
	}

	// make one-way connection
	public void connectToNode(Node connectingNode, int cost) {
		adjacentNodes.put(connectingNode, cost);
	}

	public String getName() {
		return NAME;
	}

	@Override
	public String toString() {
		return "Node " + getName();
	}

	@Override
	public int compareTo(Node node) {
		return Integer.compare(this.distance, node.distance);
	}
}
