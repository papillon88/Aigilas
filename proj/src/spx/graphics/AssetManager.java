package spx.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetManager {
    private static final String assetPath = "assets";
    private static AssetManager instance;

    public static AssetManager Get() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    private static String Graphic(String fileName) {
        return assetPath + "/graphics/" + fileName;
    }

    private AssetManager() {
    }

    public Texture GetImage(String fileName) {
        return new Texture(Graphic(fileName));
    }

    public BitmapFont GetFont(String string) {
        return new BitmapFont();
    }
}