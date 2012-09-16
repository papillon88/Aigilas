package spx.util;

public class MathHelper {

    public static int Clamp(float value, int min, int max) {
        return (int) Math.max(Math.min(value, max), min);
    }
}