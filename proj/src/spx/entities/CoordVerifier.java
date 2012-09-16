package spx.entities;

import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.SpxManager;

public class CoordVerifier {
    public static boolean IsValid(Point2 position) {
        return (position.PosX >= 0 && position.PosY >= 0 && position.PosX < SpxManager.WindowWidth && position.PosY < SpxManager.WindowHeight);
    }

    public static boolean IsBlocked(Point2 target)

    {
        return EntityManager.IsLocationBlocked(target);
    }

    public static boolean contains(Point2 target, EntityType type)

    {
        return EntityManager.AnyContains(target, type);
    }
}