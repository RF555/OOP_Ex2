import api.*;

import javax.management.Query;
import java.util.*;

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
        Dijkstra(src, dest);
        return this.nodes.get(dest).getNodeWeight();
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

    //Dijkstra

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
}

