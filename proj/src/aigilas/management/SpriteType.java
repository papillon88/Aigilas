package aigilas.management;

public enum SpriteType {
    EMPTY(0),
    PLAYER_STAND(1),
    FLOOR(2),
    SPIKE(3),
    WALL(4),
    UPSTAIRS(5),
    DOWNSTAIRS(6),
    CREATURE(7),
    ITEM(8),
    SKILL_EFFECT(9),
    MINION(10),
    ALTAR(11),
    ZORB(12),
    COMBO_MARKER(13),
    WRATH(14),
    PRIDE(15),
    ENVY(16),
    GLUTTONY(17),
    LUST(18),
    GREED(19),
    SLOTH(20),
    HAND(21),
    WHEEL(22);

    public final int Index;

    private SpriteType(int index) {
        Index = index;
    }
}
