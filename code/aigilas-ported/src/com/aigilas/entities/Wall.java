package com.aigilas.entities;
    public class Wall  extends  Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, com.aigilas.EntityType.WALL,com.aigilas.Depth.Wall);
            _isBlocking = true;
        }
    }