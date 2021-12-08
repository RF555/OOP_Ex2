import api.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    DWGraph myGraph;
    HashMap<Integer, Node> nodes;
    HashMap<Integer, HashMap<Integer, Edge>> edegs;
    double MAX_DOUBLE = 1000000000;

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

    //most current - Dijkstra
    @Override
    public double shortestPathDist(int src, int dest) {
        Dijkstra(src);
        return this.myGraph.nodes.get(dest).getNodeWeight();
    }

/*
    @Override
    public double shortestPathDist(int src, int dest) {
        List<Node> SP = Dijkstra(src, dest, this.myGraph);
        return this.nodes.get(dest).getNodeWeight();
    }


    public List<Node> Dijkstra(int src, int dest, DWGraph gr) {
        Iterator<Node> it_nodes = gr.NodeIter();
        while (it_nodes.hasNext()) {
            Node r = it_nodes.next();
            r.setNodeWeight(Double.MAX_VALUE);
            r.setTag(WHITE);
        }
        PriorityQueue<Node> q = new PriorityQueue<>();
        HashMap<Integer, Integer> parents = new HashMap();
        q.add(gr.nodes.get(src));
        gr.getNode(src).setTag(0);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.getTag() == WHITE) {
                curr.setTag(BLACK);
                if (curr.getKey() == dest) break;
//                Iterator<Node> it = gr.getV(curr.getKey()).iterator();
                Iterator<Edge> it_e = gr.edegs.get(curr.getKey()).values().iterator();
                while (it_e.hasNext()) {
                    Edge E = it_e.next();
                    if (gr.nodes.get(E.getDest()).getTag() == WHITE) {
                        double temp_w = gr.getEdge(curr.getKey(), E.getDest()).getWeight();
                        if (temp_w != -1 && temp_w + curr.getTag() < E.getTag()) {
                            gr.nodes.get(E.getDest()).setNodeWeight(temp_w + curr.getNodeWeight());
                            if (parents.containsKey(E.getDest())) parents.remove(E.getDest());
                            parents.put(E.getDest(), curr.getKey());
                            if (!q.contains(E.getDest())) q.add(gr.nodes.get(E.getDest()));
                        }
                    }
                }
            }
        }
        List<Node> path = new ArrayList<Node>();
        if (gr.nodes.get(dest).getNodeWeight() == Double.MAX_VALUE) return null;
        int assemble = dest;
        while (assemble != src) {
            Node par = gr.nodes.get(assemble);
            path.add(par);
            assemble = parents.get(assemble);
        }
        path.add(gr.nodes.get(src));
        List<Node> reversed_path = new ArrayList<Node>();
        for (int i = path.size() - 1; i >= 0; i--) {
            reversed_path.add(path.get(i));
        }
        return reversed_path;
    }
**/

    /*
     * Dijkstra PseudoCode:
     *      function dijkstra(G, S)
     *          for each vertex V in G
     *              distance[V] <- infinite
     *              previous[V] <- NULL
     *              If V != S, add V to Priority Queue Q
     *          distance[S] <- 0
     *
     *          while Q IS NOT EMPTY
     *              U <- Extract MIN from Q
     *              for each unvisited neighbour V of U
     *                  tempDistance <- distance[U] + edge_weight(U, V)
     *                  if tempDistance < distance[V]
     *                      distance[V] <- tempDistance
     *                      previous[V] <- U
     *          return distance[], previous[]
     */

    // Dijkstra #2
    private void Dijkstra(int src) {
//        PriorityQueue<Node> pq = new PriorityQueue<>(this.nodes.size());
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.getWeight(), o2.getWeight());
            }
        });
        this.myGraph.nodes.forEach((id, node) -> {
            node.setTag(WHITE);
            node.setPrevNodeID(-1);
            node.setNodeWeight(Double.MAX_VALUE);
//            if (node.getKey() != src)
//                pq.add(node);
        });
        this.myGraph.nodes.get(src).setNodeWeight(0);
        this.myGraph.nodes.get(src).getNL().add(getNode(src));
//        pq.add(this.nodes.get(src));
//        List<NodeData> NL_src = new LinkedList<>();
//        NL_src.add(getNode(src));


//        continue!!!!!!!!!!!!!!
//        New List for every loop

        this.myGraph.edegs.get(src).forEach((dest, edge) -> pq.add(edge));
        int curr = src;
        while (!pq.isEmpty()) {
//            if (getNodeTag(curr) == WHITE) {
            setNodeTag(curr, BLACK);
            int finalCurr = curr;
            List<NodeData> tempList = new LinkedList<>(new LinkedList<>(this.myGraph.nodes.get(finalCurr).getNL()));
            this.myGraph.edegs.get(curr).forEach((E_src, E) -> {
                if (getNodeTag(E.getDest()) == WHITE) {
                    pq.add(E);
                    double tempW = getNodeWeight(finalCurr) + E.getWeight();
                    if ((tempW <= getNodeWeight(E.getDest()))) {
                        setNodeWeight(E.getDest(), tempW);
//                        List<NodeData> tempList = new LinkedList<>(this.myGraph.nodes.get(E.getSrc()).getNL());
//                        tempList.add(getNode(E.getDest()));
                        setPrevNodeID(E.getDest(), finalCurr);
                        this.myGraph.nodes.get(E.getDest()).setNL(new LinkedList<>(tempList));
                        this.myGraph.nodes.get(E.getDest()).getNL().add(getNode(E.getDest()));
                    }
                }
            });
//            }
            if (pq.peek() == null)
                break;
            else
                curr = pq.poll().getDest();
        }
    }
    // Dijkstra #1
    /*
    public void Dijkstra(int src, int dest) {
        int n = this.nodes.size();
        Iterator<NodeData> it_node = this.myGraph.nodeIter();
//        PriorityQueue<Node> pq = new PriorityQueue<>(this.nodes.size());
        Queue<Node> pq = new PriorityQueue<>();
//        Queue<Node> pq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int k = it_node.next().getKey();
            this.nodes.get(k).setTag(WHITE);
            this.nodes.get(k).setWeight(Double.MAX_VALUE);
            this.nodes.get(k).setPrevNodeID(-1);
//            if (k != src)
//                pq.add(this.nodes.get(k));
        }
        pq.add(this.nodes.get(src));
        this.nodes.get(src).setNodeWeight(0);
        while (!pq.isEmpty()) {
            Node U = pq.poll();
            if (U.getTag() == WHITE) {
                nodes.get(U.getKey()).setTag(BLACK);
                if (U.getKey() == dest) break;
                for (Edge V : this.edegs.get(U.key).values()) {
                    if (V.getWeight() != -1 && this.nodes.get(V.getDest()).getTag() == WHITE) {
                        double tempDist = U.getNodeWeight() + V.getWeight();
                        if (pq.peek() != null && tempDist < pq.peek().getNodeWeight()) {
                            this.nodes.get(V.getDest()).setNodeWeight(tempDist);
                            this.nodes.get(V.getDest()).setPrevNodeID(U.getKey());
                        }
                    }
                }
            }
        }

    }

     **/

    //third try
/*
    @Override
    public double shortestPathDist(int src, int dest) {
        int node_num = this.nodes.size();
        int[] prevNode = new int[node_num];
        boolean[] visited = new boolean[node_num];
        // initialize all arrays
        for (int i = 0; i < node_num; i++) {
            this.nodes.get(i).setSpv(MAX_DOUBLE);
            prevNode[i] = -1;
            visited[i] = false;
        }
        this.nodes.get(src).setSpv(0);
//        PriorityQueue<Node> n_pq = new PriorityQueue<Node>(this.nodes.size());
//        n_pq.addAll((Collection<? extends Node>) this.nodes.values());
//        while (!visited[dest] && !n_pq.isEmpty()) {
//            Node curr = n_pq.poll();
//            int curr_id = curr.getKey();
        for (int curr_id = 0; curr_id < this.nodes.size(); curr_id++) {
            Node curr = this.nodes.get(curr_id);


            if (this.edegs.get(curr_id) != null && !visited[curr_id]) {
                visited[curr_id] = true;
                PriorityQueue<Edge> e_pq = new PriorityQueue<Edge>(this.edegs.get(curr_id).size());
                e_pq.addAll((Collection<? extends Edge>) this.edegs.get(curr_id).values());
                for (Edge e : e_pq) {
                    int e_id = e.getDest();
                    if (curr.getSpv() + e.getWeight() < this.nodes.get(e_id).getSpv()) {
                        this.nodes.get(e_id).setSpv(curr.getSpv() + e.getWeight());
                        prevNode[e_id] = curr_id;

                    }
                }
            }
        }

        return this.nodes.get(dest).getSpv();
    }
**/

    //third try
    /*
    @Override
    public double shortestPathDist(int src, int dest) {
        double[] dist = new double[nodes.size()];
        boolean[] visited = new boolean[nodes.size()];
        for (int i = 0; i < dist.length; i++) {
            visited[i] = false;
            dist[i] = Double.MAX_VALUE;
        }
        dist[src] = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if (edegs.get(i).size() > 0) {
//                PriorityQueue<EdgeData> pq = new PriorityQueue<>(edegs.get(i).size());
//                pq.addAll((Collection<? extends EdgeData>) edegs.get(i).values());
                if (!visited[i]) {
                    Iterator<Map.Entry<Integer, Edge>> it = this.edegs.get(i).entrySet().iterator();
                    while (it.hasNext()) {
                        EdgeData e = pq.poll();
                        double temp = dist[i] + e.getWeight();
                        if (temp < dist[e.getDest()])
                            dist[e.getDest()] = temp;
                    }
                }
                if (pq.isEmpty())
                    break;
                for (int v = 0; v < edegs.get(i).size(); v++) {
                    if (!visited[v] && ((dist[i] + edegs.get(i).get(v).getWeight()) < dist[v])) {
                        dist[v] = dist[i] + edegs.get(i).get(v).getWeight();
                    }
                }
            }
            visited[i] = true;
        }
        return dist[dest];
    }
**/
/*
    @Override
    public double shortestPathDist(int src, int dest) {
        double[] dist = new double[nodes.size()];
        boolean[] visited = new boolean[nodes.size()];
        double[] path = new double[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            visited[i] = false;
            path[i] = -1;
            dist[i] = Double.MAX_VALUE;
        }
        dist[src] = 0;
        path[src] = -1;
        int curr = src;
        Set<Integer> sett = new HashSet<>();
        while (true) {
            // Mark current as visited
            visited[curr] = true;
            Iterator it=this.edegs.get(i).entrySet().iterator();
            for (int i = 0; i < this.edegs.get(curr).size(); i++) {
                int v = this.edegs.get(curr).get(i).getDest();
                if (visited[v])
                    continue;

                // Inserting into the
                // visited vertex
                sett.add(v);
                double alt = dist[curr] + this.edegs.get(curr).get(v).getWeight();

                // Condition to check the distance
                // is correct and update it
                // if it is minimum from the previous
                // computed distance
                if (alt < dist[v]) {
                    dist[v] = alt;
                    path[v] = curr;
                }
            }
            sett.remove(curr);

            if (sett.isEmpty())
                break;

            // The new current
            double minDist = Double.MAX_VALUE;
            int index = 0;

            // Loop to update the distance
            // of the vertices of the graph
            for (int a : sett) {
                if (dist[a] < minDist) {
                    minDist = dist[a];
                    index = a;
                }
            }
            curr = index;
        }
        return dist[dest];


    }
**/
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        Dijkstra(src);
        List<NodeData> NL = new ArrayList<>();
        if (getNodeWeight(dest) == Integer.MAX_VALUE)
            return null;
        else {
//            NL.add(getNode(dest));
//            while (NL.get(0).getKey() != src) {
//                NL.add(getPrevNode(NL.get(0).getKey()));
//            }
//            return NL;
            return removeDup(this.myGraph.nodes.get(dest).getNL());
        }
    }

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
        double minMaxDist = Double.MAX_VALUE;
        Iterator<NodeData> it1 = this.myGraph.nodeIter();
        NodeData curr;
        NodeData tempC = new Node(-1, new GeoLocationData(0, 0, 0));
        while (it1.hasNext()) {
            curr = it1.next();
            Dijkstra(curr.getKey());
            Iterator<NodeData> it2 = this.myGraph.nodeIter();
            double maxDist = -1;
            NodeData tempMax = new Node(-1, new GeoLocationData(0, 0, 0));
            while (it2.hasNext()) {
                NodeData tempIt = it2.next();
                if (tempIt.getWeight() < Double.MAX_VALUE)
                    if (tempIt.getWeight() > maxDist) {
                        maxDist = tempIt.getWeight();
                        tempMax = tempIt;
                    }
            }
            if (maxDist < minMaxDist) {
                minMaxDist = maxDist;
                tempC = tempMax;
            }
        }
        return tempC;
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


//    @Override
//    public int compareTo(GraphAlgo otherGraph) {
//        EdgeData tempEdge;
//        return this.myGraph.edegs.forEach((src, destMap) ->
//                destMap.forEach((dest, edge) -> {
//                    tempEdge = otherGraph.getEdge(src, dest);
//                    if (tempEdge == null)
//                        return -1;
//                    else if (!(edge.getWeight() == tempEdge.getWeight())) return edge.compareTo((Edge) tempEdge);
//                };
//                ));
//    }

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

