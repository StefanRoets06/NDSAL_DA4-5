public class Node<T> {
        private final T vertex;
        private final double distance;

        public Node(T vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public T getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }