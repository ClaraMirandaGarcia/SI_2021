package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.*;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;

import java.util.List;
import java.util.Properties;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */

public class EightPuzzleDemo {
	
	private static EightPuzzleBoard initial =
			new EightPuzzleBoard(new int[] 
//					{3,4,8,5,7,1,6,0,2}
//					{ 6, 7, 4, 0, 5, 1, 3, 2, 8 }
//					{ 1, 4, 2, 7, 5, 8, 3, 0, 6 }
			// 5 movimientos
//			{1, 0, 3, 8, 2, 5, 7, 4, 6}			
//			{1, 4, 2, 0, 8, 3, 7, 6, 5} 
			
//			{1, 3, 4, 8, 6, 2, 7, 0, 5}
//			{1, 2, 3, 7, 8, 0, 6, 5, 4}
//			{1, 4, 2, 8, 3, 0, 7, 6, 5}
//			{1, 2, 3, 0, 8, 6, 7, 5, 4}
//			{1, 2, 3, 0, 4, 5, 8, 7, 6}
//			{1, 0, 2, 8, 6, 3, 7, 5, 4}
//			{2, 8, 3, 1, 6, 4, 7, 0, 5}
					
		// 10 movimientos	
//			{8, 2, 1, 7, 0, 3, 6, 5, 4}
//			{1, 4, 0, 8, 5, 2, 7, 3, 6}
					
//			{8, 1, 3, 7, 0, 5, 4, 2, 6}
//			{8, 1, 2, 4, 0, 6, 7, 5, 3}
//			{0, 1, 3, 7, 2, 5, 4, 8, 6}
//			{1, 4, 0, 7, 8, 2, 6, 5, 3}
//			{3, 8, 4, 1, 6, 2, 0, 7, 5}
//			{1, 3, 4, 6, 7, 2, 0, 8, 5}
//			{3, 2, 4, 1, 0, 5, 8, 7, 6}
//			{8, 1, 0, 2, 5, 3, 7, 4, 6}
		// 15 movimientos
//			{4, 8, 2, 6, 3, 5, 1, 0, 7}
//			{1, 4, 5, 2, 7, 0, 8, 6, 3}
			
//			{1, 3, 8, 6, 7, 4, 2, 0, 5}
//			{2, 0, 8, 7, 5, 3, 4, 1, 6}
//			{7, 1, 3, 4, 5, 0, 8, 2, 6}
//			{1, 3, 6, 7, 2, 0, 4, 5, 8}
			
			// 20 movimientos
//			{6, 2, 7, 4, 5, 1, 0, 8, 3}
//			{4, 7, 2, 1, 0, 6, 3, 5, 8}
			
//			{7, 1, 5, 4, 0, 8, 2, 6, 3}
//			{5, 1, 6, 4, 0, 3, 8, 7, 2}
//			{7, 1, 4, 5, 0, 6, 3, 2, 8}
//			{2, 4, 0, 6, 3, 1, 7, 8, 5}
//			{3, 5, 6, 2, 4, 7, 0, 1, 8}
//			{1, 4, 7, 6, 8, 5, 0, 3, 2}
//			{6, 4, 0, 2, 8, 1, 7, 3, 5}
//			{4, 1, 3, 7, 2, 8, 5, 6, 0}
					
		// 25 movimientos
//			{6, 7, 4, 0, 5, 1, 3, 2, 8}
			{6, 0, 7, 5, 4, 1, 3, 8, 2}
			
//			{3, 4, 8, 5, 7, 1, 6, 0, 2}
//			{4, 5, 3, 7, 6, 2, 8, 0, 1}
//			{2, 7, 8, 5, 4, 0, 3, 1, 6}


					);

	public static void main(String[] args) {
		System.out.println("Initial State:\n" + initial);

//		eightPuzzleAStarNullHeuristicDemo();				  // h0
//		eightPuzzleAStarWeightedMisplacedDemo();			  // h1
//		eightPuzzleAStarManhattanDemo();					  // h2
		
//		eightPuzzleAStarWeightedNonConsistentHeuristicDemo(); // h3
		eightPuzzleAStarEpsilonWeightedManhattanDemo(); 	  // (1+epsilon)*h2

	}


	private static void eightPuzzleAStarWeightedMisplacedDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void eightPuzzleAStarNullHeuristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Null Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::nullHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void eightPuzzleAStarWeightedNonConsistentHeuristicDemo() {
		System.out.println("\nEightPuzzleDemo AStar Non consistent heuristic(MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::nonConsistentHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void eightPuzzleAStarEpsilonWeightedManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Null Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::peaHeuristic);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing Search");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>
					(EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			System.out.println("Final State:\n" + search.getLastState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initial);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		properties.keySet().stream().map(key -> key + "=" + properties.get(key)).forEach(System.out::println);
	}

	private static void printActions(List<Action> actions) {
		actions.forEach(System.out::println);
	}
}