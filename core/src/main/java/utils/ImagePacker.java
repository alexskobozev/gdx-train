package utils;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class ImagePacker {

    public static void run() {
        TexturePacker2.Settings settings = new TexturePacker2.Settings();
        settings.filterMin = Texture.TextureFilter.Linear;
        settings.filterMag = Texture.TextureFilter.Linear;

        settings.pot = false;
        TexturePacker2.process(settings, "textures-original", "resources/textures", "pack");
    }
}
