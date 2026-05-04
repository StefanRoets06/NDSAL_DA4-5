import java.util.*;

public class Graph<T> {
    private Map<T, List<T>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination, boolean bidirectional) {
        if (!adjacencyList.containsKey(source)) addVertex(source);
        if (!adjacencyList.containsKey(destination)) addVertex(destination);
        
        adjacencyList.get(source).add(destination);
        if (bidirectional) {
            adjacencyList.get(destination).add(source);
        }
    }

    public PathHolder<T> bfs(T start, T end) {
        Map<T, T> parentMap = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            
            if (current.equals(end)) {
                return constructPath(parentMap, start, end);
            }

            for (T neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return new PathHolder<>(new ArrayList<>());
    }

    private PathHolder<T> constructPath(Map<T, T> parentMap, T start, T end) {
        List<T> path = new ArrayList<>();
        T current = end;

        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }

        if (!path.isEmpty() && path.get(0).equals(start)) {
            return new PathHolder<>(path);
        }

        return new PathHolder<>(new ArrayList<>());
    }
}