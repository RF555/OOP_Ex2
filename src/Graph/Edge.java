package Graph;

import api.EdgeData;

public class Edge implements api.EdgeData, Comparable<EdgeData> {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int source, destination;
    double weight;
    int tagInfo;
    String info;

    public Edge() {
        this.destination = 0;
        this.source = 0;
        this.weight = 0;
        this.tagInfo = WHITE;
    }

    public Edge(int src, int dst, double w) {
        this.weight = w;
        this.source = src;
        this.destination = dst;
        this.tagInfo = WHITE;
    }

    /**
     * Copy Constructors
     */
    public Edge(Edge other) {
        this.source = other.source;
        this.weight = other.weight;
        this.destination = other.destination;
        this.tagInfo = other.tagInfo;
    }

    @Override
    public int getSrc() {
        return this.source;
    }

    @Override
    public int getDest() {
        return this.destination;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return toString();
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    public double getW() {
        return this.weight;
    }

    @Override
    public int getTag() {

        return this.tagInfo;
    }

    @Override
    public void setTag(int t) {
        this.tagInfo = t;
    }

    @Override
    public int compareTo(EdgeData e) {
        return Double.compare(this.getWeight(), e.getWeight());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof EdgeData e))
            return false;
        if (e.getSrc() != this.getSrc() || e.getDest() != this.getDest())
            return false;
        return Double.compare(this.getWeight(), e.getWeight()) == 0;
    }

    public String toString() {
        return ("\n    {\n      \"src\": " + this.source + ",\n      \"w\": " + this.weight + ",\n      \"dest\": " + this.destination + "\n    }");
    }

}
