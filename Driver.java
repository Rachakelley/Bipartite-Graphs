import java.util.*;
public class Driver {

	public static void main(String[] args) {
		try {
			Scanner keyIn = new Scanner(System.in);
			System.out.println("Enter the size n (number of nodes) "
					+ "of the adjacency matrix:");
		    int n = keyIn.nextInt();
		    System.out.println("Enter the nxn adjacency matrix:");
	        int[][] graph = new int[n][n];
	        for (int i = 0; i < n; i++)
	            for (int j = 0; j < n; j++)
	            	graph[i][j] = keyIn.nextInt();
	        System.out.println("You entered the adjacency matrix:");
	        display(graph);
	        
	       // showing the vertex set of the graph
	        System.out.print("Vertex set:\n{");
			for(int row = 0; row < graph.length; row++)
				for(int col = 0; col < graph.length; col++)
					if(graph[row][col] == 1) {
						System.out.print("(" + row + ", " + col + ")");
					}
			System.out.print("}\n");
			
			// displaying the bipartition if the graph is bipartite
			if(isBipartite(graph, n, 0) == true) {
				System.out.println("Graph is bipartite\n");
				for(int row = 0; row < graph.length; row++) {
					System.out.print(row + " --> ");
					for(int col = 0; col < graph.length; col++)
						if(graph[row][col] == 1)
							System.out.print(col + ", ");
					System.out.println();
				}
			}
		    else
		    	System.out.println("Graph is not bipartite");
		} catch(InputMismatchException e) {
			System.out.println("Error: Invalid input.");
		}
	}
	
	public static boolean isBipartite(int graph[][], int vertices, int source) {
		// 1 = first color (red)
		// 0 = second color (blue)
		// -1 = no color is assigned to vertex
		int colors[] = new int[vertices];
		// initializing all vertices(indices of array) with no color
		for (int i = 0; i < vertices; i++)
            colors[i] = -1;	
		// choosing a source vertex and assigning it the first color (red).
        colors[source] = 1;
        
        LinkedList<Integer> queue = new LinkedList<Integer>();
        // adding the first colored vertex to the queue
        queue.add(source);
        while (queue.size() > 0) {
        	// Remove vertex from the queue and assign it to 'i'
            int i = queue.poll();
            // looking at all of the other adjacent vertices that have
            // not yet been colored yet
            for (int vertex = 0; vertex < vertices; vertex++) {
                // If there is an edge between i and vertex and vertex has not
                // been colored, assign the other color to the vertex adjacent
            	// to 'i'.
                if (graph[i][vertex] == 1 && colors[vertex] == -1) {
                    colors[vertex] = 1 - colors[i];
                    queue.add(vertex);
                }
                // is not bipartite. (there is an edge from i to vertex,
                // and vertex and 'i' already have the same color assigned to them)
                else if (graph[i][vertex] == 1 && colors[vertex] == colors[i])
                    return false;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        // showing the bipartition by color
        for(int vertex = 0; vertex < vertices; vertex++) {
        	if(colors[vertex] == 1)
        		sb.append(vertex + ", ");
      	  	if(colors[vertex] == 0)
      	  		sb2.append(vertex + ", ");
        }
        
        sb.delete(sb.lastIndexOf(", "), sb.length());
        sb2.delete(sb2.lastIndexOf(", "), sb2.length());
        System.out.print("Red vertices: " + sb.toString() +
        		("\nBlue vertices: " + sb2.toString()) + "\n");
        // is bipartite
        return true;
    }
        
	public static void display(int[][] graph) {
		int n = graph.length;
		System.out.print(" ");
	    for (int col = 0; col < n; col++)
	    	System.out.print("  " + col);
	    System.out.println();
	    for (int col = 0; col < n; col++) {
	        System.out.print(col + " ");
	        for (int row = 0; row < n; row++) {
	        	if (graph[col][row] != 0) 
	        		System.out.print(" 1 ");
	            else                  
	                System.out.print(" 0 ");
	        }
	        System.out.println();
	    }
	    System.out.println();
	}
}
