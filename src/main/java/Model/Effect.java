package Model;

import Controller.EffectController;

public interface Effect {
    public void enableEffect(Card card);
    public void disableEffect(Card card);

    public String getEffectName();

    public static Effect getEffectByName(String name) {
        for (Effect effect : EffectController.effects) {
            if (effect.getEffectName().equals(name))
                return effect;
        }
        return null;
    }
}
