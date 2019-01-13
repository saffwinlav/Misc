import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;

public class PasscodeCrack {

	public static void main(String[] args) {

		//input passcode attempts from file and create map of attempts
		String fileName = "keylog.txt";
		Graph<Integer, String> g = new DirectedSparseGraph<>();
		
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String[] attempts = line.split("\\s+");
				for(String attempt: attempts) {
					int[] nums = Stream.of(attempt.split("")).mapToInt(Integer::parseInt).toArray();
					for(int i=1;i<3;i++) {
						String name = Arrays.toString(nums).replaceAll("[^0-9]", "");;
						g.addEdge(name.substring(i-1, i+1) , nums[i-1], nums[i]);
						
					}
					
					}
				}
				
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(g);
		
		//finds possible beginnings based on
		//a beginning number will have no directed edges pointing towards it 
		Set<String> possbeg = new HashSet<String>();
		for(int i=0; i<=9; i++) {
			if(g.containsVertex(i)) {
			Collection<Integer> connect = g.getPredecessors(i);
			if(connect.isEmpty()) {
				possbeg.add(Integer.toString(i));
			}
			}
		
		}
		//creates possible key using possible beginning
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(String beg:possbeg) {
			System.out.println(beg);
			temp = createKey(g, Integer.valueOf(beg));
		}
		//performs swap based on directed edges of graph
		Collection<String> edges = g.getEdges();
		int numswaps = 0;
		for(String edge:edges)	{
			int[] nums = Stream.of(edge.split("")).mapToInt(Integer::parseInt).toArray();
			if(temp.indexOf(nums[0]) > temp.indexOf(nums[1])) {
				Collections.swap(temp, temp.indexOf(nums[0]), temp.indexOf(nums[1]));
				numswaps++;
			}
				
			}
		//continues swapping until key matches graph directed logic and no more swaps
		//are performed
		while(numswaps>0) {
			numswaps = 0;
		for(String edge:edges)	{
			int[] nums = Stream.of(edge.split("")).mapToInt(Integer::parseInt).toArray();
			if(temp.indexOf(nums[0]) > temp.indexOf(nums[1])) {
				Collections.swap(temp, temp.indexOf(nums[0]), temp.indexOf(nums[1]));
				numswaps++;
			}
				
			}
		}
			System.out.println(temp);
		}
	//creates potential key using depth first search
	public static ArrayList<Integer> createKey(Graph<Integer, String> g, int vertex) {
		Queue<Integer> searchqueue = new LinkedList<Integer>();
		ArrayList<Integer> visited = new ArrayList<Integer>(g.getVertexCount());
		ArrayList<Integer> identified = new ArrayList<Integer>(g.getVertexCount());
		visited.add(vertex);
		searchqueue.add(vertex);
		while(!searchqueue.isEmpty()) {
			int look = searchqueue.poll();
			if(!wasVisited(look, visited)) {
			visited.add(look);
			}
			Collection<Integer> connections = g.getNeighbors(look);
			Integer[] connectval = connections.toArray(new Integer[connections.size()]);
			for(int i = 0; i< connectval.length; i++) {
				if(!wasVisited(connectval[i], visited)) {
					searchqueue.add(connectval[i]);
						visited.add(connectval[i]);
							
						}
				}
			identified.add(look);	
			}
		
		
		System.out.println(identified.toString());
		return identified;
		}
		
	
	//returns true if value was visited, false if not
	public static boolean wasVisited(int val, ArrayList<Integer> visited) {
		for(int i = 0; i<visited.size(); i++) {
			if (visited.get(i) == val) {
				return true;
			}
			
		}
		return false;
		}
	

}
