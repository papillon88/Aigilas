package aigilas.strategies;

import aigilas.creatures.BaseCreature;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.RNG;
import spx.paths.Path;
import spx.paths.PathFinder;

import java.util.List;

public abstract class BaseStrategy {
    protected final TargetSet _targets;
    protected final BaseCreature _parent;

    protected static final int throttleMin = 10;
    protected static final int throttleMax = 20;
    protected int throttle = 0;

    protected BaseCreature opponent;
    protected final Path targetPath = new Path();
    protected final Point2 nextMove = new Point2(0, 0);

    protected final Strategy _strategyId;

    protected BaseStrategy(BaseCreature parent, Strategy strategyId) {
        _targets = new TargetSet(parent);
        _parent = parent;
        _strategyId = strategyId;
    }

    public abstract void act();

    public TargetSet getTargets() {
        return _targets;
    }

    public void addTargets(BaseCreature source)

    {
        _targets.addTargets(source);
    }

    protected boolean AbleToMove() {
        throttle--;
        if (throttle <= 0) {
            opponent = _targets.findClosest();
            // Every player is dead
            if (null != opponent) {
                targetPath.copy(PathFinder.findNextMove(_parent.getLocation(), opponent.getLocation()));
            }
            throttle = RNG.next(throttleMin, throttleMax);
        }
        if (null != targetPath) {
            if (targetPath.hasMoves() && _parent.isCooledDown()) {
                return true;
            }
        }
        return false;
    }

    protected final Point2 diff = new Point2(0, 0);

    protected Point2 CalculateTargetVector(Point2 source, Point2 dest) {
        diff.setX(source.GridX - dest.GridX);
        diff.setY(source.GridY - dest.GridY);
        if (diff.GridY == 0) {
            if (diff.GridX > 0) {
                diff.setX(-1f);
            } else {
                diff.setX(1f);
            }
        } else if (diff.GridX == 0) {
            if (diff.GridY > 0) {
                diff.setY(-1f);
            } else {
                diff.setY(1f);
            }
        } else {
            diff.setX(0);
            diff.setY(0);
        }
        return diff;
    }

    public Strategy getId() {
        return _strategyId;
    }

    public List<ActorType> getTargetActorTypes()

    {
        return _targets.getTargetActorTypes();
    }
}