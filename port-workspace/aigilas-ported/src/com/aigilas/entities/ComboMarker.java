package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.skills.*;import com.spx.core.*;import com.spx.entities.*;import com.aigilas.management.*;import com.spx.particles.*;
    public class ComboMarker extends Entity
    {
        private ICreature _parent;
        private int _index;

        public ComboMarker(ICreature source,int elementId,int index)
        {
            Initialize(source.GetLocation(), SpriteType.COMBO_MARKER, com.aigilas.EntityType.COMBO_MARKER,com.aigilas.Depth.ComboMarker);
            _graphic.SetColor(Elements.Colors[elementId]);
            _graphic.SetAlpha(0);
            ParticleEngine.Emit(com.spx.particles.behaviors.RotateBehavior.GetInstance(), this, _graphic.GetColor());
            _parent = source;
            _index = index;                   
        }

        private static List<Point2> __dMults = Arrays.asList(
            new Point2(-1,0),
            new Point2(0,-1),
            new Point2(1,0));

@Override        public  void Update()
        {
            float dX = (GameManager.SpriteWidth / 16) * __dMults.get(_index).X;
            float dY = (GameManager.SpriteHeight / 16) * __dMults.get(_index).Y;
            SetLocation(new Point2(_parent.GetLocation().PosX + dX, _parent.GetLocation().PosY + dY));
        }

@Override        public  void Draw()
        {
            _graphic.Draw();
        }
    }
