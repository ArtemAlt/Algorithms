package Lesson_7;


import jads.lessonc.home.Deque;
import jads.lessonc.online.Queue;
import jads.lessonc.online.Stack;

public class Manor {


    private static class Graph {
        private class Vertex {
            char label;
            int pos;
            boolean wasVisited;

            public Vertex(char label) {
                this.label = label;
                this.wasVisited = false;
                this.pos = 0;
            }

            @Override
            public String toString() {
                return String.format("*=%c ", label);
            }

            public char getLabel() {
                return label;
            }

            public int getPos() {
                return pos;
            }

            public void setPos(int pos) {
                this.pos = pos;
            }
        }

        private final int MAX_VERTICES = 10;
        private Vertex[] vertexList;
        private int[][] adjacencyMatrix;
        private int currentSize;

        public Graph() {
            vertexList = new Vertex[MAX_VERTICES];
            adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
            currentSize = 0;
        }

        public void addVertex(char label) {
            vertexList[currentSize++] = new Vertex(label);
        }


        public void addEdge(int start, int end) {
            adjacencyMatrix[start][end] = 1; // change 1 to weight for weight
            adjacencyMatrix[end][start] = 1; // delete this for direction
        }

        public void displayVertex(int v) {
            System.out.print(vertexList[v] + " ");
        }

        private int getUnvisitedVertex(int current) {
            for (int i = 0; i < currentSize; i++) {
                if (adjacencyMatrix[current][i] == 1 &&
                        !vertexList[i].wasVisited) {
                    return i;
                }
            }
            return -1;
        }

        private int getVisitedVertex(int current) {
            for (int i = 0; i < currentSize; i++) {
                if (adjacencyMatrix[current][i] == 1 &&
                        vertexList[i].wasVisited) {
                    return i;
                }
            }
            return -1;
        }

        public void depthTraverse() {
            Stack stack = new Stack(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            displayVertex(0);
            stack.push(0);
            while (!stack.isEmpty()) {
                int v = getUnvisitedVertex(stack.peek());
                if (v == -1) {
                    stack.pop();
                } else {
                    vertexList[v].wasVisited = true;
                    displayVertex(v);
                    stack.push(v);
                }
            }
            resetFlags();
        }

        public void widthTraverse() {
            Queue queue = new Queue(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            queue.insert(0);
            while (!queue.isEmpty()) {
                int current = queue.remove();
                displayVertex(current);
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].wasVisited = true;
                    queue.insert(next);
                }
            }
            resetFlags();
        }

        private void resetFlags() {
            for (int i = 0; i < currentSize; i++) {
                vertexList[i].wasVisited = false;
            }
        }

        private void resetPos() {
            for (int i = 0; i < currentSize; i++) {
                vertexList[i].pos = 0;
            }
        }


        private void way(char finish) {
            Deque deque = new Deque(MAX_VERTICES);
            Stack stackWay = new Stack(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            deque.insert(0);
            int count = 1;
            while (!deque.isEmpty()) {
                int current = deque.remove();
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].pos = count;
                    vertexList[next].wasVisited = true;
                    deque.insert(next);
                    if (vertexList[next].label == finish) {
                        vertexList[next].wasVisited = false;
                        //  обратный путь к началу
                        stackWay.push(next);
                        displayVertex(next);
                        int prev;
                        while ((prev = getVisitedVertex(stackWay.peek())) != -1 || stackWay.pop() ==0) { // todo - уловие для выхода

                            if (vertexList[prev].pos < vertexList[stackWay.peek()].pos) {
                                vertexList[prev].wasVisited = false;
                                stackWay.push(prev);
                                displayVertex(stackWay.peek());
                            } else {
                                vertexList[prev].wasVisited = true;
                            }

                        }

                    }
                }
                count++;

            }
            while (!stackWay.isEmpty()) {
                displayVertex(stackWay.pop());
            }
            resetFlags();
        }
    }


    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex('A');
        g.addVertex('B');
        g.addVertex('C');
        g.addVertex('D');
        g.addVertex('E');
        g.addVertex('F');
        g.addVertex('G');
        g.addVertex('H');
        g.addVertex('I');
        g.addEdge(0, 5);
        g.addEdge(0, 4);
        g.addEdge(4, 1);
        g.addEdge(4, 6);
        g.addEdge(5, 6);
        g.addEdge(6, 2);
        g.addEdge(1, 7);
        g.addEdge(2, 3);
        g.addEdge(7, 8);
        g.addEdge(3, 8);

        g.depthTraverse();
        System.out.println();
        g.widthTraverse();
        System.out.println();
        g.way('I');
    }
}
