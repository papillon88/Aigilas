package aigilas.skills.behaviors;

import aigilas.creatures.ICreature;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import spx.core.Point2;

public class CloudBehavior extends SkillBehavior {
    public CloudBehavior(SpriteType effectGraphic, ISkill parentSkill) {
        super(effectGraphic, AnimationType.CLOUD, parentSkill);
    }

    @Override
    public void Activate(ICreature target) {
        if (SubtractCost(target)) {
            Point2 referencePoint = target.GetLocation();
            for (int ii = -1; ii < 2; ii++) {
                for (int jj = -1; jj < 2; jj++) {
                    if (ii != 0 || jj != 0) {
                        Point2 cloudPosition = new Point2(referencePoint.GridX + ii, referencePoint.GridY + jj);
                        _sideEffects.Generate(cloudPosition, new Point2(0, 0), target);
                    }
                }
            }
        }
    }
}
