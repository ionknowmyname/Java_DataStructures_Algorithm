import com.faithfulolaleru.base.GraphNode;
import com.faithfulolaleru.base.GraphNodeAdjacencyList;

import java.util.ArrayList;

public class GraphWithAdjacencyMatrix {

    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
    int[][] adjacencyMatrix;



    public GraphWithAdjacencyMatrix(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];   // make a 2D array with the number of nodes
    }




    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
        nodeList.add(new GraphNode("A", 0));
        nodeList.add(new GraphNode("B", 1));
        nodeList.add(new GraphNode("C", 2));
        nodeList.add(new GraphNode("D", 3));
        nodeList.add(new GraphNode("E", 4));

        GraphWithAdjacencyMatrix g = new GraphWithAdjacencyMatrix(nodeList);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(0, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(3, 4);
        System.out.print(g.toString());

        ///////////////////////////////////////////////////////

        ArrayList<GraphNodeAdjacencyList> nodeList2 = new ArrayList<GraphNodeAdjacencyList>();
    }



    public void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;  // set 1 when there's a connection between nodes
        adjacencyMatrix[j][i] = 1;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("   ");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + " ");
        }
        s.append("\n");

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ": ");
            for(int j : adjacencyMatrix[i]) {
                s.append((j) + " ");
            }
            s.append("\n");
        }

        return s.toString();
    }

}
