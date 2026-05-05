public class Demo {

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addVertex("Arendskruin");
        graph.addVertex("Wynberg");
        graph.addVertex("Doringkraal");
        graph.addVertex("Groenvlei");
        graph.addVertex("Wolwekloof");
        graph.addVertex("Goudstroom");
        graph.addVertex("Breevallei");
        graph.addVertex("Wagterspos");
        graph.addVertex("Koningsrus");
        graph.addVertex("Doodskloof");
        graph.addVertex("Diepfontein");

        graph.addEdge("Arendskruin", "Wynberg", 2.0, true);
        graph.addEdge("Wynberg", "Doringkraal", 2.0, true);
        graph.addEdge("Doringkraal", "Groenvlei", 4.0, true);
        graph.addEdge("Groenvlei", "Breevallei", 4.0, true);
        graph.addEdge("Breevallei", "Wolwekloof", 4.0, true);
        graph.addEdge("Groenvlei", "Wolwekloof", 2.0, true);
        graph.addEdge("Doringkraal", "Goudstroom", 3.0, true);
        graph.addEdge("Goudstroom", "Breevallei", 2.0, true);
        graph.addEdge("Breevallei", "Doodskloof", 3.0, true);
        graph.addEdge("Goudstroom", "Koningsrus", 3.0, true);
        graph.addEdge("Koningsrus", "Diepfontein", 2.0, true);
        graph.addEdge("Doodskloof", "Diepfontein", 2.0, true);
        graph.addEdge("Wagterspos", "Koningsrus", 3.0, true);

        PathHolder<String> pathHolder = graph.bfs("Arendskruin", "Doodskloof");

        System.out.println("Roete: " + pathHolder.getPath());
        System.out.println("Totale gewig: " + pathHolder.getTotalWeight());
    }
}