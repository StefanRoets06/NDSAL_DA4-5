public class Edge<T> {
    private final T destination;
    private final double weight;

    public Edge(T destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public T getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}