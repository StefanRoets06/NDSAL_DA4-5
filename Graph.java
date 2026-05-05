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
        List<List<T>> allPaths = new ArrayList<>();
        Queue<List<T>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));

        while (!queue.isEmpty()) {
            List<T> path = queue.poll();
            T current = path.get(path.size() - 1);

            if (current.equals(end)) {
                allPaths.add(new ArrayList<>(path));
                continue;
            }
            
            for (Edge<T> edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!path.contains(edge.getDestination())) {
                    List<T> newPath = new ArrayList<>(path);
                    newPath.add(edge.getDestination());
                    queue.add(newPath);
                }
            }
        }

        return findShortestPath(allPaths, start, end);
    }

    private PathHolder<T> findShortestPath(List<List<T>> allPaths, T start, T end) {
        double shortestWeight = Double.MAX_VALUE;
        List<T> shortestPath = new ArrayList<>();

        for (List<T> path : allPaths) {
            double weight = calculatePathWeight(path);
            if (weight < shortestWeight) {
                shortestWeight = weight;
                shortestPath = path;
            }
        }

        return new PathHolder<>(shortestPath, shortestWeight);
    }

    private double calculatePathWeight(List<T> path) {
        double totalWeight = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {
            T current = path.get(i);
            T next = path.get(i + 1);

            for (Edge<T> edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (edge.getDestination().equals(next)) {
                    totalWeight += edge.getWeight();
                    break;
                }
            }
        }

        return totalWeight;
    }
}