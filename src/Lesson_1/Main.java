package Lesson_1;
/*
1. Описать простейшие алгоритмы
1.1. Возведение в степень *используя чётность степени*
1.2. Поиск минимального элемента в массиве
1.3. Нахождение среднего арифметического массива
2. Подсчитать сложность описанных алгоритмов
3. Какие правила подсчёта применялись, оставьте комментарии в коде
 */

public class Main {
    public static void main(String[] args) {
        int[] testArray = {30,5,7,4,10, 11,44,88,34,90,64,3,8,1,17,25};
        System.out.println(power(2,14));
        System.out.println("Min value - "+minValue(testArray));
        System.out.println("Average value - "+ averageValue(testArray));

    }

    public static int power (int value, int powValue){
           /*
        Для возведения в степень возможно использование бинарного возведения в степень -
        это приём, позволяющий возводить любое число в n-ую степень за O(log n)
        умножений (вместо n умножений при обычном подходе).
        Способ -  А в степени К = А в степени К\2 * А в степени К\2
        Это верно при четном значании степени,
        для нечетного значения используем (степень - 1) и результат домножаем еще раз.
         */

        int result = 1;
        if (powValue ==0){    // любое число в степени 0 - будет 1
            return result;
        }
        else if (powValue % 2 == 0){  // проверка на четность степени
            for (int i = 0; i < powValue/2; i++) {
                result = result*value;
            }
            result=result*result;
        }
        else {
            for (int i = 0; i < (powValue-1)/2; i++) {
                result = result*value;
            }
            result=result*result;
            result=result*value;
        }
        return result;
    }
    public static int minValue (int [] inputArr){
        /*
        Что бы  точно найти наименьшее число в случайном массиве требуется перебор
        каждого числа в поисках минимума. Поэтому сложность такого алгоритма – O(n).
        такой алгоритм будет линейными.
        Если допускается вероятностное нахождение этого значеия, то можно использовать
        алгоритм "The Secretary Problem", но это не входит в условие задачи.
        Альтернативным вариантом будет сортировка массива, где минимальный или максимальный элементы
        будет в крайних индексах.
         */
        int result=inputArr[0];
        for (int i = 1; i < inputArr.length; i++) {
            if(inputArr[i]<result){
                result=inputArr[i];
            }
        }
        return result;
    }
    public static double averageValue (int [] inputArr){
        /*
        Что бы  точно найти среднее арифметическое значение массива требуется перебор
        каждого значения этого массива. Поэтому сложность такого алгоритма – O(n).
        такой алгоритм будет линейными.
         */
        double result=0;
        for (int i = 0; i <inputArr.length ; i++) {
            result=result+inputArr[i];
        }
        result=result/inputArr.length;
        return result;
    }

}
