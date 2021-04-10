class VertexTopo {
    public char label;

    public VertexTopo(char lab) {
        label = lab;
    }
}

class GraphTopo {
    private final int MAX_VERTS = 20;
    private VertexTopo vertexList[];
    private int adjMat[][];
    private int nVerts;
    private char sortedArray[];

    public GraphTopo() {
        vertexList = new VertexTopo[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++)
            for (int k = 0; k < MAX_VERTS; k++)
                adjMat[j][k] = 0;
        sortedArray = new char[MAX_VERTS];
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new VertexTopo(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label);
    }

    public void topo() {
        int orig_nVerts = nVerts;
        while (nVerts > 0) {
            int currentVertex = noSuccessors();
            if (currentVertex == -1) {
                System.out.println("ERROR: Graphs has cycles");
                return;
            }

            sortedArray[nVerts - 1] = vertexList[currentVertex].label;

            deleteVertex(currentVertex);
        }

        System.out.print("Topologicaly sorted  order: ");
        for (int j = 0; j < orig_nVerts; j++)
            System.out.print(sortedArray[j]);
        System.out.println("");

    }

    public int noSuccessors() {
        boolean isEdge;

        for (int row = 0; row < nVerts; row++) {
            isEdge = false;

            for (int col = 0; col < nVerts; col++) {
                if (adjMat[row][col] > 0) {
                    isEdge = true;
                    break;
                }
            }

            if (!isEdge)
                return row;
        }

        return -1;
    }

    public void deleteVertex(int delVert) {
        if (delVert != nVerts - 1) {
            for (int j = delVert; j < nVerts - 1; j++)
                vertexList[j] = vertexList[j + 1];

            for (int row = delVert; row < nVerts - 1; row++)
                moveRowUp(row, nVerts);

            for (int col = 0; col < nVerts - 1; col++)
                moveColLeft(col, nVerts - 1);
        }
        nVerts--;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++)
            adjMat[row][col] = adjMat[row][col];
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++) {
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }

}

class TopoApp {
    public static void main(String[] args) {
        GraphTopo theGraph = new GraphTopo();
        theGraph.addVertex('A');
        theGraph.addVertex('B');
        theGraph.addVertex('C');
        theGraph.addVertex('D');
        theGraph.addVertex('E');
        theGraph.addVertex('F');
        theGraph.addVertex('G');
        theGraph.addVertex('H');
        theGraph.addEdge(0, 3);
        theGraph.addEdge(0, 4);
        theGraph.addEdge(1, 4);
        theGraph.addEdge(2, 5);
        theGraph.addEdge(3, 6);
        theGraph.addEdge(4, 6);
        theGraph.addEdge(5, 7);
        theGraph.addEdge(6, 7);
        theGraph.topo();
    }
}