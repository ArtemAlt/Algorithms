package Lesson_2;

import java.util.ArrayList;
import java.util.Arrays;
// homework
//        boolean deleteAll(int value) { } // by value
//        boolean deleteAll() { } // clear
//        void insert(int index, int value) { } // shift the tail
//        improve bubble sort (kinda make it better somehow)

public class Main {

    private static class Array {
        private int[] arr;
        private int size;
        private boolean isSorted;

        private Array() {
            isSorted = false;
        }

        public Array(int size) {
            this();
            this.size = size;
            this.arr = new int[size];
        }

        public Array(int... args) {
            this();
            this.size = args.length;
            this.arr = args;
        }

        public Array(boolean isSorted, int... args) {
            this(args);
            this.isSorted = isSorted;
        }

        public int get(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);
            return arr[index];
        }

        public void set(int index, int value) {
            arr[index] = value;
            isSorted = false;
        }
        public Array insert (int index, int value){
            // мне кажется что не так оно должно работать
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);
            int[] temp = new int[size + 1];
            int i=0;
                while (i!=index){
                    temp[i]=arr[i];
                    i++;
                }
                temp[i]=value;
                i++;
                for ( ; i< temp.length; i++) {
                    temp[i]=arr[i-1];
                }
            return new Array(temp);
        }

        public boolean delete() { // last
            if (size == 0) return false;
            size--;
            return true;
        }

        public boolean delete(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);

            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
            size--;
            return true;
        }

        public void append(int value) {
            if (size >= arr.length - 1) {
                int[] temp = arr;
                arr = new int[size * 2];
                System.arraycopy(temp, 0, arr, 0, size);
            }
            arr[size++] = value;
            isSorted = false;
        }
        public void isValuesInArray(int value){
            // ограничить прыжки последним элементом
            int jumpStep;
            int currentValue;
            int currentIndex = 0;
            int previousValue=0;
            int previousIndex = 0;
            ArrayList <Integer> result = new ArrayList<>();
            jumpStep = (int)(Math.sqrt(arr.length));
            currentValue = arr[jumpStep];
           while (currentValue<=value){
               previousValue = arr[jumpStep];
               previousIndex = jumpStep;
               jumpStep += (int)(Math.sqrt(arr.length));
               currentValue = arr[jumpStep];
               currentIndex=jumpStep;
           }
           if (previousValue<= value && value<currentValue){
               for (int i = previousIndex ; i < currentIndex; i++) {
                   if (this.arr[i] == value) {
                       result.add(i);
                   }
               }
           }

            System.out.println("In searching array value - "+value+" is found on index"+result.toString());
        }
        public int isValueInArray2(int value) {
            // такой поиск подходи для сортированого массива с равномерным распределением данных
            // т.к. предсказывает позицию элемента
            int startIndex = 0;
            int lastIndex = (arr.length - 1);
            while ((startIndex <= lastIndex) && (value >= arr[startIndex]) && (value <= arr[lastIndex])) {
                // используем формулу интерполяции для поиска возможной лучшей позиции для существующего элемента
                // саму формулу подсмотрел в google но принцип помнил что такой был
                int pos = startIndex + (((lastIndex - startIndex) /
                        (arr[lastIndex] - arr[startIndex])) *
                        (value - arr[startIndex]));

                if (arr[pos] == value)
                    return pos;
                if (arr[pos] < value)
                    startIndex = pos + 1;

                else
                    lastIndex = pos - 1;
            }
            return -1;
            }
        boolean deleteAll(int value) {
            // этот алгоритм подходит для сортированного массива, т.к. в основе лежит поиск элемента
            int index;
            index = isValueInArray2(value);
            while (this.get(index)==value){
               delete(index);
            }
            return true;
        }
        boolean deleteAll() {
           this.size = 0;
           return true;
        }
        boolean clearAll(){
            for (int i = 0; i < this.size; i++) {
                arr[i]=0;
            }
            return true;
        }
        public int indexIsInArray(int value) {
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == value) {
                    return i;
                }
            }
            return -1;
        }
        public boolean isInArray(int value) {
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == value) {
                    return true;
                }
            }
            return false;
        }
        public int hasValue(int value) {
            if (!isSorted)
                throw new RuntimeException("Trying to search in unsorted array");

            int l = 0;
            int r = size;
            int m;
            while (l < r) {
                m = (l + r) >> 1;
                if (value == arr[m]) {
                    return m;
                } else {
                    if (value < arr[m]) {
                        r = m;
                    } else {
                        l = m + 1;
                    }
                }
            }

            return -1;
        }
        private void swap(int a, int b) {
            int tmp = this.arr[a];
            this.arr[a] = this.arr[b];
            this.arr[b] = tmp;
        }
        public void sortBubble() {
            int count=0;
            for (int out = size - 1; out > 0; out--) {
                for (int in = 0; in < out; in++) {
                    if (this.arr[in] > arr[in + 1]) {
                        swap(in, in + 1);
                        count++;
                    }
                }
            }
            isSorted = true;
            System.out.println("Steps of sortBubble is - "+count);
        }
        public void sortShaker(){
            int temp;
            int count=0;
            int leftSide = 0;
            int rightSide = arr.length - 1;
            do {
                for (int i = leftSide; i < rightSide; i++) { // идем влево ищем максимум
                    if (arr[i] > arr[i + 1]) {
                        temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        count++;
                    }
                }
                rightSide--;
                for (int i = rightSide; i > leftSide; i--) { // идем обратно ищем минимум
                    if (arr[i] < arr[i - 1]) {
                        temp = arr[i];
                        arr[i] = arr[i - 1];
                        arr[i - 1] = temp;
                        count++;
                    }
                }
                leftSide++;
            } while (leftSide < rightSide);
            System.out.println("Steps of sortShaker is - "+count);
        }

        public void sortSelect() {
            for (int i = 0; i < size; i++) {
                int flag = i;
                for (int j = i + 1; j < size; j++) {
                    if (arr[j] < arr[flag])
                        flag = j;
                }
                swap(i, flag);
            }
            isSorted = true;
        }

        public void sortInsert() {
            for (int out = 1; out < size; out++) {
                int temp = arr[out];
                int in = out;
                while (in > 0 && arr[in - 1] >= temp) {
                    arr[in] = arr[in - 1];
                    in--;
                }
                arr[in] = temp;
            }
            isSorted = true;
        }



        @Override
        public String toString() {
            if (arr == null)
                return "null";
            int iMax = size - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(arr[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }
    }

    public static void main(String[] args) {
        Array array = new Array(9,2,3,6,4,5,6,1,8,0,7,7,7,1,2,5,9,8,11,15,21,23,36,56,45,23,45,78,15,16);
        System.out.println(array);
//     array.delete();
//     array.delete();
//     System.out.println(array);
//     array.delete(2);
//     System.out.println(array);
       array.sortBubble();
       System.out.println(array);
       Array array1 = array.insert(29,100);
       System.out.println(array1);
      //  array.sortShaker();
      //  System.out.println(array);
       // System.out.println(array.hasValue(7));
        // array.isValuesInArray(45);
     //  array.deleteAll(5);
     // System.out.println(array);
//        array.clearAll();
//        System.out.println(array);
//        array.deleteAll();
//        System.out.println(array);

    }

    private static void standardArrayThings() {
        int[] arr;
        arr = new int[]{1,2,3,'_',5,6,7};
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));

    }



}