package file;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HighscoreFile {
    private String path = "src/Highscore.txt";
    private static volatile HighscoreFile instance = null;

    private HighscoreFile() {

    }

    public static HighscoreFile getInstance() {
        if (instance != null) return instance;
        synchronized(HighscoreFile.class) {
            if (instance == null) instance = new HighscoreFile();
        }
        return instance;
    }

    public ArrayList <String> getHighscore() {
        ArrayList <String> fileContent = new ArrayList < String > ();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }

        } catch (Exception ignored) {

        }

        return fileContent;
    }

    public void writeHighscore(String name, int score) {
        boolean added = false;
        ArrayList < String > sorted = new ArrayList < String > ();
        ArrayList < String > fileContent = this.getHighscore();

        for (String line: fileContent) {
            int currScore = Integer.parseInt(line.split("#")[1]);

            if (score > currScore && !added) {
                sorted.add(name + "#" + score);
                sorted.add(line);
                added = true;
            } else {
                sorted.add(line);
            }
        }

        if (!added) sorted.add(name + "#" + score);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            int i = 0;
            for (String line: sorted) {
                if (i == 10) break;
                i++;
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception ignored) {

        }
    }

}