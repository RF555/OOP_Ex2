import api.GeoLocation;

import java.util.LinkedList;

public class Node implements api.NodeData, Comparable<Node> {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int key;
    double nodeWeight;
    GeoLocationData cordinates;
    int tag, prevNodeID;
    LinkedList<Edge> incomingEdges = new LinkedList<Edge>();
    double spv; // Shortest path value (for the ShortestPath function)

    public Node() {
        this.cordinates = new GeoLocationData();
        this.key = 0;
        this.tag = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.prevNodeID = -1;
    }

    public Node(int key, GeoLocation L) {
        this.cordinates = (GeoLocationData) L;
        this.key = key;
        this.tag = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.prevNodeID = -1;
    }

    @Override
    public int getKey() {

        return this.key;
    }

    @Override
    public GeoLocation getLocation() {

        return this.cordinates;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.cordinates = (GeoLocationData) p;
    }

    @Override
    public double getWeight() {

        return this.nodeWeight;
    }

    @Override
    public void setWeight(double w) {

        this.nodeWeight = w;
    }

    @Override
    public String getInfo() {
        return this.toString();
    }

    @Override
    public void setInfo(String s) {
        String tempString = s.split(":")[1];
        this.key = Integer.parseInt(tempString);
    }

    @Override
    public int getTag() {

        return this.tag;
    }

    @Override
    public void setTag(int t) {

        this.tag = t;
    }

    public String toString() {
        return "Key:" + this.key;
    }

    public double getSpv() {
        return spv;
    }

    public void setSpv(double spv) {
        this.spv = spv;
    }

    public void setPrevNodeID(int prevNodeID) {
        this.prevNodeID = prevNodeID;
    }

    public int getPrevNodeID() {
        return prevNodeID;
    }

    public double getNodeWeight() {
        return nodeWeight;
    }

    public void setNodeWeight(double nodeWeight) {
        this.nodeWeight = nodeWeight;
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.getNodeWeight(), n.getNodeWeight());
    }
}
