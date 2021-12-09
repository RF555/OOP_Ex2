import api.GeoLocation;
import api.NodeData;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Node implements api.NodeData, Comparable<Node> {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int id;
    double nodeWeight;
    GeoLocationData cordinates;
    int tag, prevNodeID;
    LinkedList<Edge> incomingEdges = new LinkedList<Edge>();

    public Node() {
        this.cordinates = new GeoLocationData();
        this.id = 0;
        this.tag = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.prevNodeID = -1;
    }

    public Node(int id, GeoLocation L) {
        this.cordinates = (GeoLocationData) L;
        this.id = id;
        this.tag = WHITE;
        this.nodeWeight = Integer.MAX_VALUE;
        this.prevNodeID = -1;
    }

    public Node(JSONObject nodeObj) {
        this.id = nodeObj.getInt("id");
        String[] stGeo = nodeObj.getString("pos").split(",");
        this.cordinates = new GeoLocationData(Double.parseDouble(stGeo[0]), Double.parseDouble(stGeo[1]), Double.parseDouble(stGeo[2]));
        if (nodeObj.has("nodeWeight"))
            this.nodeWeight = nodeObj.getDouble("nodeWeight");
        if (nodeObj.has("prevNodeID"))
            this.prevNodeID = nodeObj.getInt("prevNodeID");
    }

    @Override
    public int getKey() {

        return this.id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.id = Integer.parseInt(tempString);
    }

    @Override
    public int getTag() {

        return this.tag;
    }

    @Override
    public void setTag(int t) {

        this.tag = t;
    }

    @Override
    public String toString() {
        return ("\n    {\n      \"pos\": \"" + this.cordinates.toString() + "\",\n      \"id\": " + this.id +
                ",\n      \"nodeWeight\": " + this.nodeWeight + ",\n      \"prevNodeID\": " + this.prevNodeID +
                ",\n      \"tag\": " + this.tag + "\n    }");
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
