package utils;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class ImagePacker {

    public static void run() {
//        TexturePacker.Settings settings = new TexturePacker.Settings();
//        settings.padding = 2;
//        settings.incremental = true;
//        settings.stripWhitespace = false;
//        settings.minHeight = 1024;
//        settings.minWidth = 1024;
//        settings.defaultFilterMin = Texture.TextureFilter.Linear;
//        settings.defaultFilterMag = Texture.TextureFilter.Linear;
//        TexturePacker.process(settings, "textures-original", "resources/textures");


            TexturePacker2.Settings settings = new TexturePacker2.Settings();
            settings.filterMin = Texture.TextureFilter.Linear;
            settings.filterMag = Texture.TextureFilter.Linear;

            settings.pot = false;
            TexturePacker2.process(settings, "textures-original", "resources/textures", "pack");
    }
}
