import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public final class PandorasUnderwaterWorld {
    private PandorasUnderwaterWorld() {
    }
    
    private static class Coordinate {
        private final int layer;
        private final int row;
        private final int column;
        
        public Coordinate(int layer, int row, int column) {
            this.layer = layer;
            this.row = row;
            this.column = column;
        }
        
        public boolean equals(Object obj) {
            if (!(obj instanceof Coordinate)) {
                return false;
            }
            
            Coordinate other = (Coordinate)obj;
            
            return other.layer == this.layer &&
                other.row == this.row && 
                other.column == this.column;
        }
        
        public int hashCode() {
            return Objects.hash(this.layer, this.row, this.column);
        }
    }
    
    private static class Element implements Comparable<Element> {
        private final Coordinate coordinate;
        private final int distance;
        
        public Element(Coordinate coordinate, int distance) {
            this.coordinate = coordinate;
            this.distance = distance;
        }
        
        public int compareTo(Element other) {
            if (other == null) {
                return 1;
            }
            
            return this.distance - other.distance;
        }
    }
    
    private static class Grid {
        private final int layers;
        private final int rows;
        private final int columns;
        private final boolean[][][] airPockets;
        
        public Grid(int layers, int rows, int columns) {
            this.layers = layers;
            this.rows = rows;
            this.columns = columns;
            this.airPockets = new boolean[layers][rows][columns];
        }
        
        public void addAirPocket(int layer, int row, int column) {
            this.airPockets[layer][row][column] = true;
        }
        
        public Iterable<Coordinate> neighbors(Coordinate source) {
            ArrayList<Coordinate> results = new ArrayList<Coordinate>();
            
            if (source.layer + 1 < this.layers && this.airPockets[source.layer + 1][source.row][source.column]) {
                    results.add(new Coordinate(source.layer + 1, source.row, source.column));
            }
            
            if (source.layer - 1 >= 0 && this.airPockets[source.layer - 1][source.row][source.column]) {
                    results.add(new Coordinate(source.layer - 1, source.row, source.column));
            }
            
            if (source.row + 1 < this.rows && this.airPockets[source.layer][source.row + 1][source.column]) {
                    results.add(new Coordinate(source.layer, source.row + 1, source.column));
            }
            
            if (source.row - 1 >= 0 && this.airPockets[source.layer][source.row - 1][source.column]) {
                    results.add(new Coordinate(source.layer, source.row - 1, source.column));
            }
            
            if (source.column + 1 < this.columns && this.airPockets[source.layer][source.row][source.column + 1]) {
                    results.add(new Coordinate(source.layer, source.row, source.column + 1));
            }
            
            if (source.column - 1 >= 0 && this.airPockets[source.layer][source.row][source.column - 1]) {
                    results.add(new Coordinate(source.layer, source.row, source.column - 1));
            }
            
            return results;
        }
    
         public Map<Coordinate, Integer> dijkstra(Coordinate source) {
            HashMap<Coordinate, Integer> distances = new HashMap<Coordinate, Integer>();
            PriorityQueue<Element> priorityQueue = new PriorityQueue<Element>();
            
            for (int layer = 0; layer < this.layers; layer++) {
                for (int row = 0; row < this.rows; row++) {
                    for (int column = 0; column < this.columns; column++) {
                        distances.put(new Coordinate(layer, row, column), Integer.MAX_VALUE);
                    }
                }
            }
            
            distances.put(source, 0);
            priorityQueue.add(new Element(source, 0));
            
            while (!priorityQueue.isEmpty()) {
                Element current = priorityQueue.remove();
                
                if (current.distance > distances.get(current.coordinate)) {
                    continue;
                }
                
                for (Coordinate neighbor : this.neighbors(current.coordinate)) {
                    final int distance = current.distance + 1;
                    
                    if (distance < distances.get(neighbor)) {
                        distances.put(neighbor, distance);
                        priorityQueue.add(new Element(neighbor, distance));
                    }
                }
            }
            
            return distances;
        }
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final int layers = scanner.nextInt();
            final int rows = scanner.nextInt();
            final int columns = scanner.nextInt();
            final Grid grid = new Grid(layers, rows, columns);
            
            Coordinate start = null;
            Coordinate end = null;
            
            for (int layer = 0; layer < layers; layer++) {
                for (int row = 0; row < rows; row++) {
                    String line = scanner.next();
                    
                    for (int column = 0; column < columns; column++) {
                        char current = line.charAt(column);
                        
                        switch (current) {
                            case 'A':
                                grid.addAirPocket(layer, row, column);
                                break;
                                
                            case 'E':
                                end = new Coordinate(layer, row, column);
                                
                                grid.addAirPocket(layer, row, column);
                                break;
                                
                            case 'S':
                                start = new Coordinate(layer, row, column);
                                break;
                        }
                    }
                }
            }
            
            final int distance = grid.dijkstra(start).get(end);
            
            if (distance == Integer.MAX_VALUE) {
                System.out.println("Staying forever!");
                
                return;
            }
            
            System.out.println("Reached the surface in " + distance + " minute(s).");
        }
    }
}
