package by.it.karpovich.jd01_06;

import by.it.karpovich.jd01_06.Poem;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskA2 {
    public static final String PATTERN = "[а-яёА-ЯЁ]+";
    private static String[] words = new String[0];
    private static int[] counts = new int[0];

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(Poem.text);
        while (matcher.find()) {
            String word = matcher.group();
            for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
                String currentWord = words[i];
                if (currentWord.equals(word)) {
                    counts[i]++;
                }
            }
            words = Arrays.copyOf(words, words.length + 1);
            words[words.length - 1] = word;
            counts = Arrays.copyOf(counts, counts.length + 1);
            counts[counts.length - 1] = 1;
        }
        for (int i = 0; i < words.length; i++) {
            System.out.printf("%s=%d\n", words[i], counts[i]);
        }
    }
}
