package local.nefedov;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Парсим аргументы программы
        SimpleParser sm = new SimpleParser(new String[]{"W", "R", "?"});
        sm.parse(args);
        // Выводим в консоль распарсенные данные
        System.out.println("In file: " + sm.getInFile());
        System.out.println("Out file: " + sm.getOutFile());
        System.out.println("--------\n");

        // Создание экземпляра класса WordCounter
        WordCount wordCount = new WordCount(sm.getInFile(), sm.getOutFile());

        // Создаем экземпляр слушателя и ждем ответа
        wordCount.addListener(new IWordCounter() {
            @Override
            public void event(Object sender, int size) {
                System.out.println("Client get result: " + size + "\n");
            }
        });

        // TODO Переделать на получение через патерн "издатель-подписчик"
        // Получаем список слов
        wordCount.countWords();
        // Выводим в консоль
        wordCount.viewHashtable();

        //Запись в файл
        wordCount.writeInFile();
    }
}
