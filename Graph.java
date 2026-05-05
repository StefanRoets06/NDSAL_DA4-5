import java.util.*;

public class Graph<T> {
    private Map<T, List<Edge<T>>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination, double weight, boolean bidirectional) {
        if (!adjacencyList.containsKey(source)) addVertex(source);
        if (!adjacencyList.containsKey(destination)) addVertex(destination);

        adjacencyList.get(source).add(new Edge<>(destination, weight));
        if (bidirectional) {
            adjacencyList.get(destination).add(new Edge<>(source, weight));
        }
    }

    public PathHolder<T> bfs(T start, T end) {
        Map<T, Double> distances = new HashMap<>();
        Map<T, T> parentMap = new HashMap<>();
        PriorityQueue<Node<T>> queue = new PriorityQueue<>(Comparator.comparingDouble(Node::getDistance));
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(start, 0.0);
        queue.add(new Node<>(start, 0.0));

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            T current = currentNode.getVertex();

            if (visited.contains(current)) continue;
            visited.add(current);

            for (Edge<T> edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                T neighbor = edge.getDestination();
                double newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    parentMap.put(neighbor, current);
                    queue.add(new Node<>(neighbor, newDist));
                }
            }
        }

        return constructPath(parentMap, distances, start, end);
    }

    private PathHolder<T> constructPath(Map<T, T> parentMap, Map<T, Double> distances, T start, T end) {
        List<T> path = new ArrayList<>();
        T current = end;

        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        if (!path.isEmpty() && path.get(0).equals(start)) {
            return new PathHolder<>(path, distances.get(end));
        }

        return new PathHolder<>(new ArrayList<>(), Double.MAX_VALUE);
    }

    private static class Edge<T> {
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
}