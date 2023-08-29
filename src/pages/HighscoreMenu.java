package pages;

import file.HighscoreFile;

import java.util.ArrayList;

public class HighscoreMenu {
    public HighscoreMenu(){
        HighscoreFile.getInstance().writeHighscore("7alvin", 1);

        ArrayList<String> content = HighscoreFile.getInstance().getHighscore();

        for(String line : content) {
            System.out.println(line);
        }
    }
}
