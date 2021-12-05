public class Edge implements api.EdgeData, Comparable<Edge> {
    final int WHITE = 0, GRAY = 1, BLACK = 2;
    int source, destination;
    double weight;
    int tagInfo;

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

        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {

        return this.tagInfo;
    }

    public String toString() {
        return ("{\n    \"src\": " + this.source + ",\n    \"w\": " + this.weight + ",\n    \"dest\": " + this.destination + "\n}");
    }

    @Override
    public void setTag(int t) {
        this.tagInfo = t;
    }

    @Override
    public int compareTo(Edge e) {
        return Double.compare(this.getWeight(), e.getWeight());
    }

}
