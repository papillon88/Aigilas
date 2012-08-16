package com.aigilas.skills.animations;import com.aigilas.creatures.ICreature;import com.aigilas.entities.SkillEffect;import com.spx.core.Point2;public     class RotateAnimation  extends  SkillAnimation    {        private Point2 rotation;        private Point2 location = new Point2(0, 0);@Override        public  void Animate(SkillEffect skill, ICreature source, Point2 velocity)        {            if (rotation==null)            {                rotation = new Point2(source.GetSkillVector().GridX, source.GetSkillVector().GridY);            }            location.SetX(rotation.GridX + source.GetLocation().GridX);            location.SetY(rotation.GridY + source.GetLocation().GridY);            skill.SetLocation(location);            rotation.Copy(rotation.RotateClockwise());        }    }