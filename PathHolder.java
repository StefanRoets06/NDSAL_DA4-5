import java.util.List;

// PathHolder datastruktuur om die paaie van die begin tot die bestemming te stoor
public class PathHolder<T> {
    private List<T> path;
    private double totalWeight;

    public PathHolder(List<T> path, double totalWeight) {
        this.path = path;
        this.totalWeight = totalWeight;
    }

    public List<T> getPath() {
        return path;
    }

    public void setPath(List<T> path) {
        this.path = path;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
