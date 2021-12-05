import api.GeoLocation;

import java.util.LinkedList;

public class Node implements api.NodeData {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int key;
    double nodeWeight;
    GeoLocationData cordinates;
    int tagInfo, previusNodeId;
    LinkedList<Edge> incomingEdges = new LinkedList<Edge>();
    double spv; // Shortest path value (for the ShortestPath function)

    public Node() {
        this.cordinates = new GeoLocationData();
        this.key = 0;
        this.tagInfo = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.previusNodeId = -1;
    }

    public Node(int key, GeoLocation L) {
        this.cordinates = (GeoLocationData) L;
        this.key = key;
        this.tagInfo = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.previusNodeId = -1;
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

        return this.tagInfo;
    }

    @Override
    public void setTag(int t) {

        this.tagInfo = t;
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

}
