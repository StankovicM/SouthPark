package raf.game.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GameUtil {

    public static int getLineCount(File f) {

        if (!f.exists()) return -1;

        try {
            int lines = 0;
            BufferedReader br = new BufferedReader(new FileReader(f));
            String s;
            while ((s = br.readLine()) != null) { lines++; }
            br.close();
            return lines;
        } catch (IOException e) {
            return -1;
        }

    }

    public static int clamp(int x, int min, int max) {

        if (x < min) return min;

        if (x > max) return max;

        return x;

    }

}
