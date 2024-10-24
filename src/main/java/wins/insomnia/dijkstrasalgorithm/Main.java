package wins.insomnia.dijkstrasalgorithm;

import java.util.*;
import java.util.stream.Stream;

public class Main {

	private static void testAlgorithm() {

		// make nodes
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");

		// node A connections
		nodeA.connectWithNode(nodeC, 5);
		nodeA.connectWithNode(nodeB, 2);
		nodeA.connectWithNode(nodeD, 1);

		// node B connections
		nodeB.connectWithNode(nodeD, 2);
		nodeB.connectWithNode(nodeC, 3);

		// node C connections
		nodeC.connectWithNode(nodeD, 3);
		nodeC.connectWithNode(nodeE, 1);
		nodeC.connectWithNode(nodeF, 5);

		// node D connections
		nodeD.connectWithNode(nodeE, 1);

		// node E connections
		nodeE.connectWithNode(nodeF, 2);

		// node F connections
		// NONE, because previous connections to F have already been established through two-way connections


		// calculate
		calculateShortestPaths(nodeA);


		// print
		printShortestPathToNode(nodeA, false);
		printShortestPathToNode(nodeB, false);
		printShortestPathToNode(nodeC, false);
		printShortestPathToNode(nodeD, false);
		printShortestPathToNode(nodeE, false);
		printShortestPathToNode(nodeF, false);



	}



	public static void printShortestPathToNode(Node destinationNode, boolean showCostBetweenArrows) {
		int totalCost = 0;
		System.out.print("The shortest path to " + destinationNode + " is: ");

		for (int i = 0; i < destinationNode.shortestPath.size(); i++) {
			Node currentNode = destinationNode.shortestPath.get(i);

			// print current node in path
			System.out.print(currentNode.getName());

			// print arrow (and cost) for next node in path
			if (i < destinationNode.shortestPath.size() - 1) {

				Node nextNode = destinationNode.shortestPath.get(i + 1);
				int cost = currentNode.adjacentNodes.get(nextNode);
				totalCost += cost;

				if (showCostBetweenArrows) {
					System.out.print(" -(" + cost + ")-> ");
				} else {
					System.out.print(" -> ");
				}

				// end of path, print destination node
			} else {

				int cost = currentNode.adjacentNodes.get(destinationNode);
				totalCost += cost;

				if (showCostBetweenArrows) {
					System.out.print(" -(" + cost + ")-> " + destinationNode.getName() + "\n");
				} else {
					System.out.print(" -> " + destinationNode.getName() + "\n");
				}
			}
		}
		System.out.println("With a total cost of: " + totalCost);
	}

	public static void calculateShortestPaths(Node sourceNode) {

		// set distance
		sourceNode.distance = 0;


		// make sets for settled and unsettled nodes
		Set<Node> settledNodes = new HashSet<>();

		Queue<Node> unsettledNodes = new PriorityQueue<>();
		unsettledNodes.add(sourceNode);


		// loop through unsettled nodes
		while (!unsettledNodes.isEmpty()) {

			Node currentNode = unsettledNodes.poll();

			// for each connection of the unsettled node
			for (Map.Entry<Node, Integer> connection : currentNode.adjacentNodes.entrySet()) {

				// get node connected through the connection to the unsettled node
				Node adjacentNode = connection.getKey();

				// if connection is to already settled node, continue to another connection
				if (settledNodes.contains(adjacentNode)) continue;

				// check distance
				int distanceCheck = currentNode.distance + connection.getValue();
				if (distanceCheck < adjacentNode.distance) {

					// set new distance
					adjacentNode.distance = distanceCheck;

					// add unsettled node to the shortest path of the connected node
					adjacentNode.shortestPath = new LinkedList<>(currentNode.shortestPath);
					adjacentNode.shortestPath.add(currentNode);
				}

				// connected node must be checked, so add it to queue
				unsettledNodes.add(adjacentNode);
			}

			// finished checking the current unsettled node, so mark it as settled
			settledNodes.add(currentNode);

		}
	}




	public static void main(String[] args) {
		testAlgorithm();
	}

}
