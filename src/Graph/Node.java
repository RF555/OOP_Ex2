package Graph;

import api.GeoLocation;
import org.json.JSONObject;
import java.util.LinkedList;

public class Node implements api.NodeData, Comparable<Node> {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int id;
    double nodeWeight;
    GeoLocationData coordinates;
    int tag, prevNodeID;
    LinkedList<Edge> incomingEdges = new LinkedList<Edge>();

    public Node() {
        this.coordinates = new GeoLocationData();
        this.id = 0;
        this.tag = WHITE;
        this.nodeWeight = Double.MAX_VALUE;
        this.prevNodeID = -1;
    }

    public Node(int id, GeoLocation L) {
        this.coordinates = (GeoLocationData) L;
        this.id = id;
        this.tag = WHITE;
        this.nodeWeight = Double.MAX_VALUE;
        this.prevNodeID = -1;
    }

    public Node(JSONObject nodeObj) {
        this.id = nodeObj.getInt("id");
        String[] stGeo = nodeObj.getString("pos").split(",");
        this.coordinates = new GeoLocationData(Double.parseDouble(stGeo[0]), Double.parseDouble(stGeo[1]), Double.parseDouble(stGeo[2]));
        if (nodeObj.has("nodeWeight"))
            this.nodeWeight = nodeObj.getDouble("nodeWeight");
        if (nodeObj.has("prevNodeID"))
            this.prevNodeID = nodeObj.getInt("prevNodeID");
    }

    //copy
    public Node(Node other){
        this.id = other.getKey();
        this.coordinates = (GeoLocationData) other.getLocation();
        this.nodeWeight = other.getWeight();
        this.tag = other.getTag();
    }

    @Override
    public int getKey() {
        return this.id;
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
        String[] tempString = s.split(":");
        String[] stGeo = tempString[1].split(",");
        String[] stGeo1 = stGeo[1].split("\"");
        String[] stGeo2 = stGeo1[0].split("\"");
        String[] stZ=stGeo[2].split("\"")[0].split(" ");
        this.coordinates = new GeoLocationData(Double.parseDouble(stGeo1[0]), Double.parseDouble(stGeo2[0]), Double.parseDouble(stZ[0]));
        this.id= Integer.parseInt(tempString[2].split(",")[0].split(" ")[1]);
        this.nodeWeight = Double.parseDouble(tempString[3].split(" ")[1].split(",")[0]);
        this.tag= Integer.parseInt(tempString[5].split("\n")[0].split(" ")[1]);
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
        return ("\n    {\n      \"pos\": \"" + this.coordinates.toString() + "\",\n      \"id\": " + this.id +
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
