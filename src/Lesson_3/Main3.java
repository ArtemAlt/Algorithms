package Lesson_3;

import java.util.Arrays;

/*
1. Создать программу, которая переворачивает вводимые строки (читает справа налево) при помощи стека. +
2. Создать класс для реализации дека.
3. Создать класс с реализацией приоритетной очереди
 */
public class Main3 {
    private static class Stack {
        private int[] stack;
        private int head;

        public Stack(int size) {
            this.stack = new int[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(int i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public int pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public int peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }

    }
    private static class StackChar {
        private char[] stack;
        private int head;

        public StackChar(int size) {
            this.stack = new char[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(char i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public char pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public char peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }

    }
    private static class Deque {
        private int[] deque;
        private int first;
        private int last;
        private int capacity;

        public Deque (int initial){
            deque = new int [initial];
            first= 0;
            last = deque.length-1;
            capacity = 0;
        }
        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == deque.length;
        }

        public void putFirst (int value){
            if (isFull())
                throw new RuntimeException("Deque is full!");
            for (int i = first; i >0; i--) {
                deque[i]=deque[i-1];
            }
            deque[0]=value;
            first++;
            capacity++;

        }
        public void deleteFirst(){
            for (int i = 0; i < first; i++) {
                deque[i]=deque[i+1];
            }
            deque[first-1]=0;
            first--;
            capacity--;
        }
        public void putLast (int value){
            if (isFull())
                throw new RuntimeException("Deque is full!");
            for (int i = last; i < deque.length-1; i++) {
                deque[i]=deque[i+1];
            }
            deque[deque.length-1]=value;
            capacity++;
            last--;
        }
        public void deleteLast (){
            if (isEmpty())
                throw new RuntimeException("Deque is empty!");
            for (int i = deque.length-1; i > last; i--) {
                deque[i]=deque[i-1];

            }
            deque[last+1]=0;
            capacity--;
            last++;
        }
        public void print (){
            for (int i = 0; i < deque.length; i++) {
                System.out.println(deque[i]);
            }
        }
        public int getFirst(){
            return deque[0];
        }
        public int getLast(){
            return deque[deque.length-1];
        }
    }
    private static class Queue {
        private int[] queue;
        private int head;
        private int tail;
        private int capacity;

        public Queue(int initial) {
            queue = new int[initial];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length -1)
                tail = -1;
            queue[++tail] = i;
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }

    }

    private static int checkBrackets(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '(' || ch == '<' || ch == '{') {
                st.push(ch);
            } else if (ch == ']' || ch == ')' || ch == '>' || ch == '}') {
                if (st.isEmpty())
                    return i;

                char op = (char) st.pop();
                if (!((op == '[' && ch == ']') ||
                        (op == '{' && ch == '}') ||
                        (op == '(' && ch == ')') ||
                        (op == '<' && ch == '>'))) {
                    return i;
                }
            }
        }
        if (!st.isEmpty()) {
            return size;
        }
        return -1;
    }
    private static class Cell{
        private int priority;
        private int value;
        public Cell(){};
         public Cell (int priority, int value){
             this.priority=priority;
             this.value=value;
         }

        public int getPriority() {
            return priority;
        }


        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{" +
                    "pr=" + priority +
                    ", v=" + value +
                    '}';
        }
    }

    private static class PriorityQueue {
        private Cell[] queue;
        private int head;
        private int tail;
        private int capacity;
        private int size;

        public PriorityQueue(int initial) {
            queue = new Cell[initial];
            size = initial;
            head = 0;
            tail = -1;
            capacity = 0;
        }
        public Cell getCell (int index){
            return queue[index];
        }

        @Override
        public String toString() {
            String [] outArray = new String[this.capacity];
            for (int i = 0; i < this.capacity; i++) {
              outArray[i]= this.getCell(i).toString();

            }
            return Arrays.toString(outArray);
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int priority, int value) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            Cell cell = new Cell(priority,value);
            if (tail == queue.length -1)
                tail = -1;
            queue[++tail] = cell;
            capacity++;
        }




        public Cell remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            Cell temp = queue[head++];
            head %= queue.length;
            capacity--;
            return temp;
        }
        public Cell getMaxPriority(){
            Cell result=this.getCell(0);
            for (int i = 1; i < capacity; i++) {
                if(this.getCell(i).getPriority()>result.getPriority()){
                    result=this.getCell(i);
                }
            }return result;
        }
        public Cell getMinPriority(){
            Cell result=this.getCell(0);
            for (int i = 1; i < capacity; i++) {
                if(this.getCell(i).getPriority()<result.getPriority()){
                    result=this.getCell(i);
                }
            }return result;
        }

    }

    public static void main(String[] args) {
       // System.out.println(checkBrackets("<> () [] {} {[() <>]}"));
        PriorityQueue pq = new PriorityQueue(10);
        pq.insert(5,99);
        pq.insert(2,98);
        pq.insert(1,98);
        pq.insert(0,98);
        System.out.println(pq.toString());
        System.out.println(pq.getMaxPriority().toString());
        System.out.println(pq.getMinPriority().toString());

//        System.out.println(rollBackString("Hello World!"));
//        Deque dq = new Deque(10);
//        dq.putFirst(1);
//        dq.putFirst(3);
//        dq.putFirst(4);
//        dq.putFirst(7);
//        dq.putFirst(8);
//        dq.putFirst(9);
//        dq.putLast(100);
//        dq.putLast(101);
//        dq.putLast(102);
//        dq.putLast(103);
//        dq.print();
//        System.out.println();
//        dq.deleteLast();
//        dq.print();
//        dq.putFirst(11);
//        dq.print();
//        dq.deleteFirst();
//        dq.deleteFirst();
//        dq.print();
//        System.out.println(dq.getFirst());
//        System.out.println(dq.getLast());

    }
    private static String rollBackString (String input){
        StackChar temp = new StackChar(input.length());
        char[] arrString = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            temp.push(arrString[i]);
        }
        for (int i = 0; i < input.length(); i++) {
            arrString[i]=temp.pop();
        }
        return new String(arrString);
    }
}
