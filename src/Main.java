import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static AtomicInteger beautiful3 = new AtomicInteger();
    static AtomicInteger beautiful4 = new AtomicInteger();
    static AtomicInteger beautiful5 = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }
        Thread thread1 = new Thread(() -> {beautifulParm1(texts);});
        Thread thread2 = new Thread(() -> {beautifulParm2(texts);});
        Thread thread3 = new Thread(() -> {beautifulParm3(texts);});
        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(3000);
        System.out.println("Красивых слов с длиной 3: " + beautiful3 + " шт");
        System.out.println("Красивых слов с длиной 4: " + beautiful4 + " шт");
        System.out.println("Красивых слов с длиной 5: " + beautiful5 + " шт");
                }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    public static void beautifulParm1(String[] texts){
        for (String text :texts){
            char[] chars = text.toCharArray();
            boolean buffer = true;
            for (int i = 0; i < chars.length - 1;i++){
                if (chars[i] != chars[i + 1]){
                    buffer = false;
                    break;
                }
            }
            if (buffer){
                switch (text.length()) {
                    case 3 -> beautiful3.getAndDecrement();
                    case 4 -> beautiful4.getAndDecrement();
                    case 5 -> beautiful5.getAndDecrement();
                }
            }
        }
    }

    public static void beautifulParm2(String[] texts){
        for (String text :texts){
            StringBuffer buffer = new StringBuffer(text);
            if (buffer.reverse().toString().equals(text)){
                switch (text.length()) {
                    case 3 -> beautiful3.getAndIncrement();
                    case 4 -> beautiful4.getAndIncrement();
                    case 5 -> beautiful5.getAndIncrement();
                }
            }
        }

    }

    public static void beautifulParm3(String[] texts){
        for (String text :texts){
            char[] chars = text.toCharArray();
            boolean buffer = true;
            for (int i = 0; i < chars.length - 1;i++){
                if (chars[i] > chars[i + 1]){
                    buffer = false;
                    break;
                }
            }
            if (buffer){
                switch (text.length()) {
                    case 3 -> beautiful3.getAndIncrement();
                    case 4 -> beautiful4.getAndIncrement();
                    case 5 -> beautiful5.getAndIncrement();
                }
            }
        }

    }
}