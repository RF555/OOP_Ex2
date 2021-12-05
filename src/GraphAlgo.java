import api.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
    final int WHITE =0 , GRAY = 1 , BLACK=2;
    DWGraph myGraph;
    public GraphAlgo(DWGraph g){
        init(g);
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = (DWGraph)g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        if(DFS(this.myGraph) == 1){
            DWGraph tempGraph = GraphTraspose(this.myGraph);
            if(DFS(tempGraph) == 1){
                return true;
            }
        }
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    private DWGraph GraphTraspose(DWGraph g){
        DWGraph transposeGraph = new DWGraph();
        Iterator<NodeData> NodeIter = g.nodeIter();
        while (NodeIter.hasNext()){
            Node temp =(Node)NodeIter.next();
            temp.setTag(WHITE);
            transposeGraph.addNode(temp);
        }
        Iterator<EdgeData> EdgeIter = g.edgeIter();
        while (EdgeIter.hasNext()){
            Edge temp = (Edge)EdgeIter.next();
            transposeGraph.connect(temp.getDest(),temp.getSrc(), temp.getWeight());
        }
        return transposeGraph;
    }

    private void DFSRec(DWGraph g,Node n){
        g.getNode(n.getKey()).setTag(GRAY);
        Iterator<EdgeData> it = g.edgeIter(n.getKey());
        while (it != null && it.hasNext()){
            Node nTemp = (Node) g.getNode(it.next().getDest());
            if(nTemp.getTag() == WHITE){
                DFSRec(g,nTemp);
            }
        }
    }

    private int DFS(DWGraph g) {
        int counter = 0;
        Iterator<NodeData> it = g.nodeIter();
        while (it != null && it.hasNext()) {
            Node nTemp = (Node) it.next();
            if (nTemp.getTag() == WHITE) {
                DFSRec(g, nTemp);
                counter++;
            }
        }
        return counter;
    }
}

