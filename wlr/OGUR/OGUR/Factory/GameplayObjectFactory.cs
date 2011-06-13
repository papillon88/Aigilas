﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Factory
{
    class GameplayObjectFactory
    {
        static private int s_playerCount = 0;
        static public GameplayObject Create(GameObjectType type, int x, int y)
        {
            switch (type)
            {
                case GameObjectType.PLAYER:
                    return new Player(x, y,s_playerCount++);
                case GameObjectType.FLOOR:
                    return new Floor(x, y);
                case GameObjectType.SPIKE:
                    return new Spike(x, y);
                case GameObjectType.WALL:
                    return new Wall(x, y);
                case GameObjectType.DOWNSTAIRS:
                    return new Downstairs(x, y);
                case GameObjectType.UPSTAIRS:
                    return new Upstairs(x, y);
                default:
                    throw new Exception("An undefined GameObjectType case was passed into the GameplayObjectFactory.");
            }
        }
        static public void ResetPlayerCount()
        {
            s_playerCount = 0;
        }
        static public void IncreasePlayerCount()
        {
            s_playerCount ++;
        }
    }
}