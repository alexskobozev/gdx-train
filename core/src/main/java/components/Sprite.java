package components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;

public class Sprite extends Component {

    public Layer layer = Layer.DEFAULT;

    public enum Layer {
        DEFAULT,
        BACKGROUND,
        ACTORS_1,
        ACTORS_2,
        ACTORS_3,
        PARTICLES;

        public int getLayerId() {
            return ordinal();
        }
    }


    public float r = 1;
    public float g = 1;
    public float b = 1;
    public float a = 1;
    public float scaleX = 1;
    public float scaleY = 1;
    public float rotation;

    public Texture sprite;
    public String name;

    public Sprite(String name, Layer layer) {
        this.name = name;
        this.layer = layer;
    }

    public Sprite(String name) {
        this("default",Layer.DEFAULT);

    }

    public Sprite() {
        this("textures-original/fighter.png");
    }
}
