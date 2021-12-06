import java.util.Objects;

public class Path implements api.EdgeData {
    int src, dest;
    double w;

    public Path() {
        this.src = -1;
        this.dest = -2;
        this.w = -1;
    }

    public Path(int s, int d, double w) {
        this.src = s;
        this.dest = d;
        this.w = w;
    }

    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public double getWeight() {
        return w;
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
        return 0;
    }

    @Override
    public void setTag(int t) {

    }

    public void setWeight(double w) {
        this.w = w;
    }

    public String getID() {
        return (this.src + "->" + this.dest);
    }

    @Override
    public int hashCode() {
        return this.getID().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Path))
            return false;
        if (o == this)
            return true;
        return (Objects.equals(this.getID(), ((Path) o).getID()));

    }
}
