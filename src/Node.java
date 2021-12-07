import api.GeoLocation;
import api.NodeData;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class Node implements api.NodeData, Comparable<Node> {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int key;
    double nodeWeight;
    GeoLocationData coordinates;
    int tag, prevNodeID;
    LinkedList<Edge> incomingEdges = new LinkedList<Edge>();
    List<NodeData> NL = new LinkedList<>();

    public Node() {
        this.coordinates = new GeoLocationData();
        this.key = 0;
        this.tag = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.prevNodeID = -1;
    }

    public Node(int key, GeoLocation L) {
        this.coordinates = (GeoLocationData) L;
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

        return this.coordinates;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.coordinates = (GeoLocationData) p;
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

    public void setPrevNodeID(int prevNodeID) {
        this.prevNodeID = prevNodeID;
    }

    public int getPrevNodeID() {
        return prevNodeID;
    }

    public void setNL(List<NodeData> NL) {
        this.NL = NL;
    }

    public List<NodeData> getNL() {
        return NL;
    }

    @Override
    public boolean equals(Object that) {
        Node obj = (Node) that;
        return (obj.getKey() == this.getKey());
    }


    @Override
    public int compareTo(@NotNull Node o) {
        return Integer.compare(o.getKey(), this.getKey());
    }
}
