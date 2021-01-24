package Lesson_4;

import java.util.LinkedList;
import java.util.Objects;

public class Main {
    // class iteroCat
    // reset()
    // next(), prev(for dll)
    // getCurrent()
    // atEnd()
    // insertBefore();
    // insertAfter();
    // deleteCurrent();
    private static class Cat {
        int age;
        String name;

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Cat(a=%d,n=%s)", age, name);
        }
    }
    private static class SingleLinkedList {
        //here is your path...
        private class Node {
            Cat c;
            Node next;
            public Node(Cat c) {
                this.c = c;
            }
            @Override
            public String toString() {
                return String.format("Node(c=%s)", c);
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next);
            }
        }
        private Node head;
        public SingleLinkedList() {
            this.head = null;
        }
        public boolean isEmpty() {
            return head == null;
        }
        public void push(Cat c) {
            Node n = new Node(c);
            n.next = head;
            head = n;
        }
        public Cat pop() {
            if (isEmpty()) return null;
            Cat temp = head.c;
            head = head.next;
            return temp;
        }
        public boolean contains(Cat c) {
            Node n = new Node(c);
            Node current = head;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }
        public void delete(Cat c) {
            Node n = new Node(c);
            Node current = head;
            Node previous = null;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == head) {
                head = head.next;
            } else {
                previous.next = current.next;
            }

            // return current.c

        }
    }
    private static class DoubleLinkedList {
        //here is your path...
        private class DNode {
            Cat c;
            DNode next;
            DNode prev;
            public DNode(Cat c) {
                this.c = c;
            }
            @Override
            public String toString() {
                return String.format("Node(c=%s)", c);
            }
            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                DNode node = (DNode) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next);
            }
        }

        private DNode head;
        private DNode tail;
        private DNode tempHead;
        private DNode tempTail;
        private int capacity;


        public DoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }
        public boolean isTail() {return tail!=null;}
        public boolean isHead() {return head!=null;}
        public boolean isEmpty() {
         return capacity==0;
        }
        public void pushFirst(Cat c) {
            DNode n = new DNode(c);
            if (!isHead()){
                head=n;
                tail = n;
                head.prev=tail;
                tail.next=head;
            }
            n.prev = head;
            n.next=tail;
            head.next=n;
            head=n;
            capacity++;
        }

        public void pushLast(Cat c) {
            DNode n = new DNode(c);
            if (!isTail()){
                tail=n;
                head = n;
                tail.next=head;
                head.prev=tail;
            }
            n.next=tail;
            n.prev=head;
            tail.prev=n;
            tail=n;
            capacity++;
        }
        public Cat popHead() {
            if (isEmpty()) throw new RuntimeException (" List is empty!");
            Cat temp = head.c;
            head = head.prev;
            capacity--;
            return temp;
        }
        public Cat popTail() {
            if (isEmpty()) throw new RuntimeException (" List is empty!");
            Cat temp = tail.c;
            tail = tail.next;
            capacity--;
            return temp;
        }
//        public boolean contains(Cat c) {
//            DNode n = new DNode(c);
//            DNode current = head;
//            while (!current.equals(n)) {
//                if (current.next == null) return false;
//                else current = current.next;
//            }
//            return true;
//        }
        public void delete(Cat c) {
            DNode n = new DNode(c);
            DNode current = head;
            DNode previous = null;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == head) {
                head = head.next;
            } else {
                previous.next = current.next;
            }

            // return current.c

        }
    }
    public static void main(String[] args) {
        DoubleLinkedList dl = new DoubleLinkedList();
        Cat c1 = new Cat (1, "Барсик");
        Cat c2 = new Cat (2, "Мурзик");
        Cat c3 = new Cat (3, "Пушок");
        Cat c4 = new Cat (4, "Тузик");
        Cat c5 = new Cat (5, "Рузик");
        Cat c6 = new Cat (6, "Снежок");
        Cat c7 = new Cat (-1, "Барсик");
        Cat c8 = new Cat (-2, "Мурзик");
        Cat c9 = new Cat (-3, "Пушок");
        Cat c10 = new Cat (-4, "Тузик");
        Cat c11 = new Cat (-5, "Рузик");
        Cat c12 = new Cat (-6, "Снежок");
        dl.pushFirst(c1);
        dl.pushFirst(c2);
        dl.pushFirst(c3);
        dl.pushFirst(c4);
        dl.pushFirst(c5);
        dl.pushFirst(c6);
        dl.pushLast(c7);
        dl.pushLast(c8);
        dl.pushLast(c9);
        dl.pushLast(c10);
        dl.pushLast(c11);
        dl.pushLast(c12);
        System.out.println(dl.capacity);
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());
        System.out.println(dl.popTail().toString());

    }
}
