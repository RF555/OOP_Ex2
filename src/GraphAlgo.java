import api.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    DWGraph myGraph;
    HashMap<Integer, Node> nodes;
    HashMap<Integer, HashMap<Integer, Edge>> edegs;

    public GraphAlgo(DWGraph g) {
        init(g);
        this.nodes = g.nodes;
        this.edegs = g.edegs;
    }

    public GraphAlgo() {
        this.nodes = new HashMap<Integer, Node>();
        this.edegs = new HashMap<Integer, HashMap<Integer, Edge>>();
    }


    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = (DWGraph) g;
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
        if (DFS(this.myGraph) == 1) {
            DWGraph tempGraph = GraphTraspose(this.myGraph);
            if (DFS(tempGraph) == 1) {
                return true;
            }
        }
        return false;
    }

    // Dijkstra-matanel
    /*
    @Override
    public double shortestPathDist(int src, int dest) {
        if (this.myGraph.nodeSize() == 0 || this.myGraph.getNode(src) == null || this.myGraph.getNode(dest) == null)
            return -1;
        if (src == dest)
            return 0;
        List<NodeData> path = this.shortestPath(src, dest);
        if (path == null)
            return -1;
        if (path.get(0) == this.myGraph.getNode(src))
            return this.myGraph.getNode(dest).getWeight();
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {

    }

    // Matanel's Dijkstra
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        Comparator<NodeData> lessWeight = new Comparator<>() {
            @Override
            public int compare(NodeData node1, NodeData node2) {
                return Double.compare(node1.getWeight(), node2.getWeight());
            }
        };
        Iterator<NodeData> resetnodes = this.myGraph.nodeIter();
        while (resetnodes.hasNext()) {
            Node r = (Node) resetnodes.next();
            r.setWeight(Double.MAX_VALUE);
            r.setTag(0);
        }
        PriorityQueue<Node> q = new PriorityQueue<>(lessWeight);
        HashMap<Integer, Integer> parents = new HashMap();
        Node tempnode = (Node) this.myGraph.getNode(src);
        q.add(tempnode);
        tempnode.nodeWeight = 0;
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.getTag() == 0) {
                curr.setTag(1);
                if (curr.getKey() == dest) break;
                Iterator<NodeData> it = this.myGraph.nodeIter();
                while (it.hasNext()) {
                    Node n = (Node) it.next();
                    if (n.getTag() == 0) {
                        Edge temp = (Edge) this.myGraph.getEdge(curr.getKey(), n.getKey());
                        if (temp != null && temp.getWeight() + curr.nodeWeight < n.nodeWeight) {
                            n.setWeight((temp.getWeight() + curr.nodeWeight));
                            if (parents.containsKey(n.getKey())) {
                                parents.remove(n.getKey());
                            }
                            parents.put(n.getKey(), curr.getKey());
                            if (!q.contains(n))
                                q.add(n);
                        }
                    }
                }
            }
        }
        List<Node> path = new ArrayList<>();
        if (this.myGraph.getNode(dest).getWeight() == Double.MAX_VALUE)
            return null;
        int assemble = dest;
        while (assemble != src) {
            Node par = (Node) this.myGraph.getNode(assemble);
            path.add(par);
            assemble = parents.get(assemble);
        }
        path.add((Node) this.myGraph.getNode(src));
        List<NodeData> reversedpath = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            reversedpath.add(path.get(i));
        }
        return reversedpath;
    }
    **/


    private List<NodeData> removeDup(List<NodeData> NL) {
        List<NodeData> new_list = new LinkedList<>();
        for (NodeData node : NL) {
            if (!new_list.contains(node))
                new_list.add(node);
        }
        return new LinkedList<>(new_list);
    }

    @Override
    public NodeData center() {
        double centerDist = Double.MAX_VALUE;
        Iterator<NodeData> it1 = this.myGraph.nodeIter();
        NodeData curr_src;
        NodeData tempCenter = new Node(-1, new GeoLocationData(0, 0, 0));
        while (it1.hasNext()) {
            curr_src = it1.next();
            Iterator<NodeData> it2 = this.myGraph.nodeIter();
            double maxDist = -1;
            double currDist = -1;
            NodeData curr_dest = new Node(-1, new GeoLocationData(0, 0, 0));
            NodeData curr_maxNode = new Node(-1, new GeoLocationData(0, 0, 0));
            while (it2.hasNext()) {
                curr_dest = it2.next();
                currDist = shortestPathDist(curr_src.getKey(), curr_dest.getKey());
                if (currDist < Double.MAX_VALUE) {
                    if (currDist > maxDist) {
                        maxDist = currDist;
                        curr_maxNode = curr_dest;
                    }
                }

//                NodeData tempIt = it2.next();
//                if (tempIt.getWeight() < Double.MAX_VALUE)
//                    if (tempIt.getWeight() > maxDist) {
//                        maxDist = tempIt.getWeight();
//                        curr_dest = tempIt;
//                    }
            }
            if (maxDist < centerDist) {
                centerDist = maxDist;
                tempCenter = curr_maxNode;
            }
        }
        return tempCenter;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        JSON j = new JSON(this.myGraph);
        return j.toJSONFileBool(file);
    }

    @Override
    public boolean load(String file) {
//        JSON j = new JSON(file);
        this.myGraph = new DWGraph(file);
        this.nodes = this.myGraph.nodes;
        this.edegs = this.myGraph.edegs;
        return true;

    }

    private DWGraph GraphTraspose(DWGraph g) {
        DWGraph transposeGraph = new DWGraph();
        Iterator<NodeData> NodeIter = g.nodeIter();
        while (NodeIter.hasNext()) {
            Node temp = (Node) NodeIter.next();
            temp.setTag(WHITE);
            transposeGraph.addNode(temp);
        }
        Iterator<EdgeData> EdgeIter = g.edgeIter();
        while (EdgeIter.hasNext()) {
            Edge temp = (Edge) EdgeIter.next();
            transposeGraph.connect(temp.getDest(), temp.getSrc(), temp.getWeight());
        }
        return transposeGraph;
    }

    private void DFSRec(DWGraph g, Node n) {
        g.getNode(n.getKey()).setTag(GRAY);
        Iterator<EdgeData> it = g.edgeIter(n.getKey());
        while (it != null && it.hasNext()) {
            Node nTemp = (Node) g.getNode(it.next().getDest());
            if (nTemp.getTag() == WHITE) {
                DFSRec(g, nTemp);
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

    private EdgeData getEdge(int src, int dest) {
        return this.myGraph.getEdge(src, dest);
    }

    private NodeData getNode(int nodeID) {
        return this.myGraph.getNode(nodeID);
    }

    private double getNodeWeight(int nodeID) {
        return this.myGraph.nodes.get(nodeID).getNodeWeight();
    }

    private void setNodeWeight(int nodeID, double w) {
        this.myGraph.nodes.get(nodeID).setNodeWeight(w);
    }

    // WHITE (not visited) = 0, BLACK (visited) = 2
    private int getNodeTag(int nodeID) {
        return this.myGraph.nodes.get(nodeID).getTag();
    }

    // WHITE (not visited) = 0, BLACK (visited) = 2
    private void setNodeTag(int nodeID, int tag) {
        this.myGraph.nodes.get(nodeID).setTag(tag);
    }

    private int getPrevNodeID(int nodeID) {
        return this.myGraph.nodes.get(nodeID).getPrevNodeID();
    }

    private void setPrevNodeID(int nodeID, int prevNodeID) {
        this.myGraph.nodes.get(nodeID).setPrevNodeID(prevNodeID);
    }

    private NodeData getPrevNode(int nodeID) {
        int prevID = getPrevNodeID(nodeID);
        return getNode(prevID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof DirectedWeightedGraphAlgorithms g))
            return false;
//         tempEdge;
        AtomicBoolean ans = new AtomicBoolean(false);
        this.myGraph.edegs.forEach((src, destMap) ->
                destMap.forEach((dest, edge) -> {
                            EdgeData tempEdge = g.getGraph().getEdge(src, dest);
                            if (tempEdge == null) {
                                ans.set(false);
                                return;
                            } else if (edge.getWeight() != tempEdge.getWeight()) {
                                if (!edge.equals((Edge) tempEdge)) {
                                    ans.set(false);
                                    return;
                                }
                            }
                        }
                ));
        return ans.get();
    }
}