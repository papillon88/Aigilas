package spx.graphics;

import spx.core.Settings;

public class SpriteInfo {
    public int X, Y, SpriteIndex, MaxFrame;

    public SpriteInfo(int spriteIndex, int maxFrame) {
        X = Settings.Get().spriteWidth;
        Y = Settings.Get().spriteHeight;
        SpriteIndex = spriteIndex;
        MaxFrame = maxFrame;
    }
}
