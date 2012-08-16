package com.aigilas.entities;
    public class Extensions
    {
        public static ICreature IsCreature(IEntity entity)
        {
            if (entity.GetEntityType() == com.aigilas.EntityType.ACTOR)
            {
                return (ICreature)entity;
            }
            return null;
        }
    }