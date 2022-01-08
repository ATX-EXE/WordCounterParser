package local.nefedov;

import java.io.*;
import java.util.*;

public class WordCount {
    private String inFile;
    private String outFile;
    private Hashtable<String, Integer> words = new Hashtable<String, Integer>();
    static private String testString;
    private String delim = " \t\n\r/,.;:*%$^\"_-–!?#[]'{}()";
    private Vector<IWordCounter> listeners = new Vector<>();

    static {
        testString = "Meet my family. There are five of us – my parents, my elder brother, " +
                "my baby sister and me. First, meet my mum and dad, Jane and Michael. My mum enjoys reading and " +
                "my dad enjoys playing chess with my brother Ken. My mum is slim and rather tall. She has long " +
                "red hair and big brown eyes. She has a very pleasant smile and a soft voice. My mother is very " +
                "kind and understanding. We are real friends. She is a housewife. As she has three children, she " +
                "is always busy around the house. She takes care of my baby sister Meg, who is only three months old. " +
                "My sister is very small and funny. She sleeps, eats and sometimes cries. We all help our mother " +
                "and let her have a rest in the evening. Then she usually reads a book or just watches TV. My " +
                "father is a doctor. He is tall and handsome. He has short dark hair and gray eyes. He is a " +
                "very hardworking man. He is rather strict with us, but always fair. My elder brother Ken is " +
                "thirteen, and he is very clever. He is good at Maths and always helps me with it, because " +
                "I can hardly understand all these sums and problems. Ken has red hair and brown eyes. " +
                "My name is Jessica. I am eleven. I have long dark hair and brown eyes. I am not as clever " +
                "as my brother, though I try to do my best at school too. I am fond of dancing. Our dancing studio " +
                "won The Best Dancing Studio 2015 competition last month. I am very proud of it. I also like to " +
                "help my mother with my little sister very much. Our family is very united. We love each other " +
                "and always try to spend more time together.";
    }

    public void addListener(IWordCounter listener) {
        listeners.add(listener);
    }

    public void delListener(IWordCounter listener) {
        listeners.remove(listener);
    }

    protected void fireCounter(int size) throws IOException {
        for (IWordCounter listener: listeners) {
            listener.event(listener, size);
        }
    }

    /**
     * Main constructor
     *
     * @param inFile
     * @param outFile
     */
    public WordCount(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }



    /**
     * Accessing the words collection
     *
     * @return hashtable<String, Integer>
     */
    public Hashtable getWords() {
        return words;
    }

    /**
     * Counter method
     * Populates the hashtable "words"
     *
     * @throws IOException
     */
    void countWords() throws IOException {
        Reader reader;
        // Проверка входного файла
        if (inFile == null) {
            reader = new StringReader(testString);
        } else {
            try {
                reader = new FileReader(inFile);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage() + "\n--------\n");
                reader = new StringReader(testString);
            }
        }

        //Счетчик
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st;
        String token;
//        Object val;

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            st = new StringTokenizer(line, delim);
            while (st.hasMoreTokens()) {
                token = st.nextToken();
//                System.out.println(token);
                if (words.containsKey(token.toLowerCase())) {
//                    val = words.get(token.toLowerCase());
//                    int n = (int) val;
//                    n++;
//                    words.put(token.toLowerCase(), n);
                    //Упрощение
                    words.replace(token.toLowerCase(), words.get(token.toLowerCase()) + 1);
                } else {
                    words.put(token.toLowerCase(), 1);
                }
            }
        }
        br.close();
        fireCounter(words.size());
    }

    /**
     * Geterate out file in key @param outFile
     * default "src/out.txt"
     */
    void writeInFile() {
        if (outFile == null) outFile = "src/out.txt";
        try (FileWriter fileWriter = new FileWriter(outFile)) {
            fileWriter.write(hashtableToString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Generates string from hashtable
     *
     * @return String
     */
    String hashtableToString() {
        StringBuilder result = new StringBuilder(); //возвращаемый результат

        TreeMap<String, Integer> sortedwords = new TreeMap<String, Integer>(words);

        if (!sortedwords.isEmpty()) {
            sortedwords.forEach((k, v) -> {
                result.append(k.toString()).append(": ").append(v.toString()).append("\n");
            });
            return result.toString();
        } else return "Коллекция пуста.";
    }

    /**
     * Hashtable output to console
     */
    void viewHashtable() {
        System.out.println(hashtableToString());
    }

}
