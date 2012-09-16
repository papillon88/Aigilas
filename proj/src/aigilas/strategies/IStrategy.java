package aigilas.strategies;

import aigilas.creatures.ICreature;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.RNG;
import spx.paths.Path;
import spx.paths.PathFinder;

import java.util.List;

public abstract class IStrategy {
    protected TargetSet _targets;
    protected ICreature _parent;

    protected static final int throttleMin = 10;
    protected static final int throttleMax = 20;
    protected int throttle = 0;

    protected ICreature opponent;
    protected Path targetPath = new Path();
    protected Point2 nextMove = new Point2(0, 0);

    protected Strategy _strategyId;

    protected IStrategy(ICreature parent, Strategy strategyId) {
        _targets = new TargetSet(parent);
        _parent = parent;
        _strategyId = strategyId;
    }

    public abstract void Act();

    public TargetSet GetTargets() {
        return _targets;
    }

    public void AddTargets(ICreature source)

    {
        _targets.AddTargets(source);
    }

    protected boolean AbleToMove() {
        throttle--;
        if (throttle <= 0) {
            opponent = _targets.FindClosest();
            // Every player is dead
            if (null != opponent) {
                targetPath.Copy(PathFinder.FindNextMove(_parent.GetLocation(), opponent.GetLocation()));
            }
            throttle = RNG.Next(throttleMin, throttleMax);
        }
        if (null != targetPath) {
            if (targetPath.HasMoves() && _parent.IsCooledDown()) {
                return true;
            }
        }
        return false;
    }

    protected Point2 diff = new Point2(0, 0);

    protected Point2 CalculateTargetVector(Point2 source, Point2 dest) {
        diff.SetX(source.GridX - dest.GridX);
        diff.SetY(source.GridY - dest.GridY);
        if (diff.GridY == 0) {
            if (diff.GridX > 0) {
                diff.SetX(-1f);
            } else {
                diff.SetX(1f);
            }
        } else if (diff.GridX == 0) {
            if (diff.GridY > 0) {
                diff.SetY(-1f);
            } else {
                diff.SetY(1f);
            }
        } else {
            diff.SetX(0);
            diff.SetY(0);
        }
        return diff;
    }

    public Strategy GetId() {
        return _strategyId;
    }

    public List<ActorType> GetTargetActorTypes()

    {
        return _targets.GetTargetActorTypes();
    }
}
