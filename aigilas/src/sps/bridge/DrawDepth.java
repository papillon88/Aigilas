package sps.bridge;

public enum DrawDepth {
    Default(0f),
    AnimatedTexture(0f),
    Floor(0f),
    Wall(.1f),
    Altar(.2f),
    Stairs(.4f),
    Item(.45f),
    Creature(.5f),
    Player(.7f),
    BaseSkillEffect(.7f),
    SkillEffect(.75f),
    ComboMarker(.999f),
    HudBG(.95f),
    DefaultHudText(.96f),
    ActionTextBG(0.98f),
    ActionText(0.985f),
    DevConsole(.9993f),
    DevConsoleText(.9995f),
    Particle(.99999f), Debug(.999999999f);

    private final float DrawDepth;

    private DrawDepth(float DrawDepth) {
        this.DrawDepth = DrawDepth;
    }

    public float value() {
        return DrawDepth;
    }
}