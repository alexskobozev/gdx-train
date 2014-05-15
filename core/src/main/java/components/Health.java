package components;

import com.artemis.Component;

public class Health extends Component {

    public float health;
    public float maxHealth;

    public Health(float health, float maxHealth) {
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public Health() {
        this(0, 0);
    }
}
