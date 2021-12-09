import api.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    DWGraph myGraph;
    HashMap<Integer, Node> nodesMap;
    HashMap<Integer, HashMap<Integer, Edge>> edgesMap;

    public GraphAlgo(DWGraph g) {
        init(g);
        this.nodesMap = g.nodes;
        this.edgesMap = g.edegs;
    }

    public GraphAlgo() {
        this.nodesMap = new HashMap<Integer, Node>();
        this.edgesMap = new HashMap<Integer, HashMap<Integer, Edge>>();
    }


    @Override
    public void init(DirectedWeightedGraph g) {
        this.myGraph = (DWGraph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    //Roey's implementation
    @Override
    public DirectedWeightedGraph copy() {
        JSON json = new JSON(this.myGraph);
        return new DWGraph(json);
    }

    //Matanel's implementation
//    @Override
//    public DirectedWeightedGraph copy() {
//        DirectedWeightedGraph copy_graph = new DWGraph();
//        Iterator<NodeData> nodeIt = this.myGraph.nodeIter();
//        while(nodeIt.hasNext()) {
//            Node temp = (Node) nodeIt.next();
//            NodeData temp_node = new Node(temp);
//            copy_graph.addNode(temp_node);
//        }
//        Iterator<EdgeData> edgeIt = this.myGraph.edgeIter();
//        while(edgeIt.hasNext()) {
//            Edge temp = (Edge) edgeIt.next();
//            EdgeData temp_edge = new Edge(temp);
//            copy_graph.connect(temp_edge.getSrc(), temp_edge.getDest(), temp_edge.getWeight());
//        }
//        return copy_graph;
//    }


    @Override
    public boolean isConnected() {
        if (DFS(this.myGraph) == 1) {
            DWGraph tempGraph = GraphTraspose(this.myGraph);
            return DFS(tempGraph) == 1;
        }
        return false;
    }

    // MY FINAL TRY!
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;
        dijkstra(src);
        if (this.myGraph.nodes.get(dest).getNodeWeight() == Double.MAX_VALUE)
            return -1;
        else
            return this.myGraph.nodes.get(dest).getNodeWeight();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        dijkstra(src);
        return getPrevsList(src, dest);
    }

    private void dijkstra(int src) {
        Comparator<NodeData> lessWeight = new Comparator<>() {
            @Override
            public int compare(NodeData node1, NodeData node2) {
                return Double.compare(node1.getWeight(), node2.getWeight());
            }
        };
        PriorityQueue<Node> pq = new PriorityQueue<>(lessWeight);
        this.myGraph.nodes.forEach((id, node) -> {
            node.setPrevNodeID(-1);
            node.setTag(WHITE);
            if (id == src)
                node.setWeight(0);
            else
                node.setWeight(Double.MAX_VALUE);
        });
        pq.add(this.myGraph.nodes.get(src));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.getTag() == WHITE) {
                curr.setTag(BLACK);
                this.myGraph.edegs.get(curr.getKey()).forEach((destID, edge) -> {
                    if (getNodeTag(edge.getDest()) == WHITE)
                        relax(edge);
                    this.myGraph.edegs.get(curr.getKey()).forEach((dest, e) -> pq.add(getNode(e.getDest())));
                });
            }
        }
    }

    private void relax(Edge e) {
        if (getNodeWeight(e.getDest()) > (getNodeWeight(e.getSrc()) + e.getWeight())) {
            this.myGraph.nodes.get(e.getDest()).setNodeWeight(getNodeWeight(e.getSrc()) + e.getWeight());
            this.myGraph.nodes.get(e.getDest()).setPrevNodeID(e.getSrc());
        }
    }

    @Override
    public NodeData center() {
        List<NodeData> NL = new LinkedList<>();
        int[] centerNodeID = new int[1];
        double[] dist = new double[1];
        center(NL, centerNodeID, dist);
        return this.myGraph.nodes.get(centerNodeID[0]);
    }

    void center(List<NodeData> NL, int[] centerNodeID, double[] dist) {
        double centerDist = Double.MAX_VALUE;
        NodeData tempCenter = new Node(-1, new GeoLocationData(0, 0, 0));
        List<NodeData> centerNL = null;
        for (Node node1 : this.myGraph.nodes.values()) {
            dijkstra(node1.getKey());
            double maxDist = -1;
            NodeData curr_maxNode = new Node(-1, new GeoLocationData(0, 0, 0));
            List<NodeData> maxNL = null;
            for (Node node2 : this.myGraph.nodes.values()) {
                if (node2.getWeight() < Double.MAX_VALUE) {
                    if (node2.getWeight() > maxDist && node2.getWeight() != -1) {
                        maxDist = node2.getWeight();
                        curr_maxNode = node1;
                        maxNL = getPrevsList(node1.getKey(), node2.getKey());
                    }
                }
            }
            if (centerDist > maxDist) {
                centerDist = maxDist;
                tempCenter = curr_maxNode;
                centerNL = maxNL;
            }
        }
        dist[0] = centerDist;
        centerNodeID[0] = tempCenter.getKey();
        assert centerNL != null;
        NL.addAll(centerNL);
    }

    private List<NodeData> getPrevsList(int src, int dest) {
        if (this.myGraph.nodes.get(dest).getNodeWeight() == Double.MAX_VALUE)
            return null;
        List<NodeData> NL = new LinkedList<>();
        NL.add(this.myGraph.nodes.get(dest));
        double path = this.myGraph.nodes.get(dest).getNodeWeight();
        Node curr = this.myGraph.nodes.get(dest);
        while (NL.get(0).getKey() != src && path > 0) {
            NL.add(0, getPrevNode(curr));
            curr = getPrevNode(curr);
        }
        return NL;
    }

    // OR TRY - all mutations solution


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double min = Double.POSITIVE_INFINITY;
        List<NodeData> returnedPath = new ArrayList<>();
        Iterator<NodeData> srcIt = cities.listIterator();
        while (srcIt.hasNext()) {
            NodeData curr = srcIt.next();
            Comparator<NodeData> lessWeight = new Comparator<>() {
                @Override
                public int compare(NodeData node1, NodeData node2) {
                    return Double.compare(shortestPathDist(curr.getKey(), node1.getKey()), shortestPathDist(curr.getKey(), node2.getKey()));
                }
            };
            PriorityQueue<NodeData> pQueue = new PriorityQueue<>(lessWeight);
            Iterator<NodeData> dstIt = cities.listIterator();
            while (dstIt.hasNext()) {
                NodeData tmp = dstIt.next();
                if (curr.getKey() != tmp.getKey())
                    pQueue.add(tmp);
            }
            double sum = 0;
            List<NodeData> tempPath = new ArrayList<>();
            NodeData tempCurr = curr;
            tempPath.add(curr);
            while (!pQueue.isEmpty()) {
                sum = sum + shortestPathDist(tempCurr.getKey(), pQueue.peek().getKey());
                tempCurr = pQueue.peek();
                tempPath.add(pQueue.poll());
            }
            if (sum < min) {
                min = sum;
                returnedPath = tempPath;
            }
        }
        return returnedPath;
    }

    @Override
    public boolean save(String file) {
        JSON j = new JSON(this.myGraph);
        j.toJSONFile(file);
        return true;
    }

    @Override
    public boolean load(String file) {
        this.myGraph = new DWGraph(file);
        this.nodesMap = this.myGraph.nodes;
        this.edgesMap = this.myGraph.edegs;
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


    private Node getNode(int nodeID) {
        return (Node) this.myGraph.getNode(nodeID);
    }

    private double getNodeWeight(int nodeID) {
        return this.myGraph.nodes.get(nodeID).getWeight();
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

    private Node getPrevNode(Node node) {
        return this.myGraph.nodes.get(node.getPrevNodeID());
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
        AtomicBoolean ans = new AtomicBoolean(false);
        this.myGraph.edegs.forEach((src, destMap) ->
                destMap.forEach((dest, edge) -> {
                            EdgeData tempEdge = g.getGraph().getEdge(src, dest);
                            if (tempEdge == null) {
                                ans.set(false);
                            } else if (edge.getWeight() != tempEdge.getWeight()) {
                                if (!edge.equals((Edge) tempEdge)) {
                                    ans.set(false);
                                }
                            }
                        }
                ));
        return ans.get();
    }
}