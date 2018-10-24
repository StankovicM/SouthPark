package raf.game.elements.world;

import raf.game.util.GameUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class World {

    private static final String WORLD_PATH = "src/raf/game/assets/world.wrl";

    public static String[][] tilemap;
    public static HashMap<String, Tile> tileSet = new HashMap<>();
    public int rows, cols;

    public static int width;
    public static int height;

    public World() {

        rows = GameUtil.getLineCount(new File(WORLD_PATH));
        tilemap = new String[rows][];

    }

    public void load() {

        File f = new File(WORLD_PATH);
        if (!f.exists()) {
            return;
        }

        int i = 0, j = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(WORLD_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] row = line.split(";");
                j = row.length;
                tilemap[i] = new String[j];
                copyRow(row, tilemap[i], j);
                i += 1;
                for (String s : row) {
                    if (!tileSet.containsKey(s)) {
                        Tile t = new Tile("src/raf/game/assets/" + s + ".png");
                        tileSet.put(s, t);
                    }
                }
            }

            cols = j;
            width = cols * Tile.TILE_W;
            height = rows * Tile.TILE_H;
            reader.close();
        } catch (IOException e) {
            System.err.println("Failed to load the world.");
            e.printStackTrace();
        }

        /*for (i = 0; i < rows; ++i) {
            for (j = 0; j < cols; ++j) {
                System.out.print(tilemap[i][j] + " ");
            }
            System.out.println();
        }*/

    }

    private void copyRow(String[] src, String[] tar, int n) {

        for (int i = 0; i < n; i++)
            tar[i] = src[i];

    }

}
