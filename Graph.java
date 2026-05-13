import java.util.*;

public class Graph<T> {
    private Map<T, List<Edge<T>>> adjacencyList;

    // Constructor om n nuwe grafiek te skep
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    // Metode om punte op die grafiek by te voeg
    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Metode om sye op die grafiek by te voeg
    public void addEdge(T source, T destination, double weight, boolean bidirectional) {
        if (!adjacencyList.containsKey(source)) addVertex(source);
        if (!adjacencyList.containsKey(destination)) addVertex(destination);

        adjacencyList.get(source).add(new Edge<>(destination, weight));
        if (bidirectional) {
            adjacencyList.get(destination).add(new Edge<>(source, weight));
        }
    }

    // BFS soek algoritme
    public PathHolder<T> wbfs(T start, T end) {
        // Skep n nuwe lys om al die paaie na die bestemming te stoor
        List<List<T>> allPaths = new ArrayList<>();
        // Skep n tou om die paaie te verken met BFS
        Queue<List<T>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));

        // Beweeg deur die hele boom om elke moontlike pad te find
        while (!queue.isEmpty()) {
            List<T> path = queue.poll();
            T current = path.get(path.size() - 1);

            // Kyk by elke node of die bestemming gevind is
            if (current.equals(end)) {
                // Voeg die pad by die lys van paaie na die bestemming
                allPaths.add(new ArrayList<>(path));
                continue;
            }
            
            // Kry die buure van die heidige node
            for (Edge<T> edge : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                // Maak seker dat dit nie in sirkels loop nie
                if (!path.contains(edge.getDestination())) {
                    // Skep n nuwe pad
                    List<T> newPath = new ArrayList<>(path);
                    newPath.add(edge.getDestination());
                    queue.add(newPath);
                }
            }
        }
        
        // Roep n metode om die kortste pad te find
        return findShortestPath(allPaths, start, end);
    }

    // Vind die kortste pad in n lys paaie
    private PathHolder<T> findShortestPath(List<List<T>> allPaths, T start, T end) {
        // Stel due kortste pad se gewig/koste na die grootste moontlike getal
        double shortestWeight = Double.MAX_VALUE;
        // Skep n nuwe lys om die kortste pad te stoor
        List<T> shortestPath = new ArrayList<>();

        // Bereken die gewig/koste vir elke pad
        for (List<T> path : allPaths) {
            double weight = calculatePathWeight(path);
            // Vervang die kortste pad indien n korter pad gevind is
            if (weight < shortestWeight) {
                shortestWeight = weight;
                shortestPath = path;
            }
        }

        // Gee die pad terrug in die vorm van n eie datastruktuur
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