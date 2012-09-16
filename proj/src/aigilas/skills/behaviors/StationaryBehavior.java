package aigilas.skills.behaviors;

import aigilas.creatures.ICreature;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import spx.core.Point2;

public class StationaryBehavior extends SkillBehavior {
    public StationaryBehavior(SpriteType effectGraphic, ISkill parentSkill) {

        super(effectGraphic, AnimationType.STATIONARY, parentSkill);
    }

    @Override
    public void Activate(ICreature target)

    {

        if (SubtractCost(target)) {
            if (_parent.StartOffCenter) {
                Point2 location = new Point2(target.GetLocation().GridX + target.GetSkillVector().GridX, target.GetLocation().GridY + target.GetSkillVector().GridY);
                _sideEffects.Generate(location, new Point2(0, 0), target);
            } else {
                _sideEffects.Generate(target.GetLocation(), new Point2(0, 0), target);
            }
        }
    }
}