package org.example.lesson1.task2;

//2. Написати метод, який на вхід приймає список рядків-текстів,
// в яких можуть бути хеш-теги (слова, що починаються на знак "#").
// Як результат, метод повинен повертати top-5 найчастіше згаданих хеш-тегів
// із зазначенням кількості згадки кожного з них.
// Декілька однакових хеш-тегів в одному рядку повинні вважатися однією згадкою.
// Написати unit-тести для цього методу.

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("#AAA #BBB #CCC #DDD #EEE #AAA");
        list.add("#AAA #BBB #CCC #DDD #AAA #BBB #CCC #DDD #EEE #AAA");
        list.add("#AAA #BBB #CCC #AAA #BBB #CCC #DDD #EEE #AAA");
        list.add("#AAA #BBB");
        list.add("#AAA");
        list.add("#FFF");
        Logic.printTop5Hashtags(list);
    }
}
