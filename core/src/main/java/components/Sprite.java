package components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.xml.soap.Text;

public class Sprite extends Component {

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


    public float r, g, b, a, scaleX, scaleY, rotation;
    public int x, y, width, height;
    public TextureRegion region;
    public Layer layer;
    public int index;

    public String name;


    public Sprite(String name) {
        this.name = name;
        this.r = 1f;
        this.g = 1f;
        this.a = 1f;
        this.b = 1f;
        this.scaleX = 1f;
        this.scaleY = 1f;
        this.rotation = 0f;
        index = 0;
    }

}
