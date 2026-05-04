import java.util.List;

public class PathHolder<T> {
    private List<T> path;

    public PathHolder(List<T> path) {
        this.path = path;
    }

    public List<T> getPath() {
        return path;
    }

    public void setPath(List<T> path) {
        this.path = path;
    }
}
