package local.nefedov.comLineParser;

import java.io.IOException;
import java.util.Locale;

public class ParserMain {
    public static void main(String[] args) throws IOException {
        SimpleParser sm = new SimpleParser(new String[]{"W", "R", "?"});
        sm.parse(args);
        System.out.println("In file: " + sm.getInFile());
        System.out.println("Out file: " + sm.getOutFile());
        System.out.println("--------\n");

        WordCount wordCount = new WordCount(sm.getInFile(), sm.getOutFile());

        wordCount.addListener(new IWordCounter() {
            @Override
            public void event(Object sender, int size) {
                System.out.println("Client get result: " + size + "\n");
            }
        });

        wordCount.countWords();
        wordCount.viewHashtable();

        wordCount.writeInFile();
    }
}
