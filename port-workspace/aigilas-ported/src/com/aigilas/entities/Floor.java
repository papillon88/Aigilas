package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.spx.entities.*;import com.aigilas.management.*;import com.spx.core.*;import com.aigilas.entities.*;
    public class Floor  extends Entity
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, com.aigilas.EntityType.FLOOR,com.aigilas.Depth.Floor);
        }
    }
