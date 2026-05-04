public class Demo {

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();

        graph.addVertex("Misheuwel");
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

        graph.addEdge("Misheuwel", "Wynberg", true);
        graph.addEdge("Wynberg", "Doringkraal", true);
        graph.addEdge("Doringkraal", "Groenvlei", true);
        graph.addEdge("Groenvlei", "Wolwekloof", true);
        graph.addEdge("Doringkraal", "Goudstroom", true);
        graph.addEdge("Goudstroom", "Breevallei", true);
        graph.addEdge("Breevallei", "Wagterspos", true);
        graph.addEdge("Goudstroom", "Koningsrus", true);
        graph.addEdge("Koningsrus", "Diepfontein", true);
        graph.addEdge("Doodskloof", "Diepfontein", true);
        graph.addEdge("Wagterspos", "Koningsrus", true);

        PathHolder<String> pathHolder = graph.bfs("Misheuwel", "Doodskloof");
        
        System.out.println("Roete vanaf Misheuwel na Doodskloof: " + pathHolder.getPath());
    }
}