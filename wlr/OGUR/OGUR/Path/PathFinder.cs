﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using OGUR.Dungeons;
using OGUR.GameObjects;

namespace OGUR.Path
{    
    public static class PathFinder
    {
        private static readonly PriorityQueue queue = new PriorityQueue();
        private static Point2 node = new Point2(0, 0);
        private static Path path;
        private static List<Point2> neighbors = new List<Point2>();

        private static readonly Random rand = new Random();

        public static Point2 FindNextMove(Point2 start,Point2 destination,bool nextMoveOnly = true)
        {
            queue.Clear();
            start.Reset(start.GridX,start.GridY);
            destination.Reset(destination.GridX, destination.GridY);
            queue.Enqueue(0, PathFactory.Create(start,destination));
            while (!queue.IsEmpty)
            {
                path = queue.Dequeue();
                break;}
                /*
                if (path.IsDone())
                {
                    return path.GetNextMove();
                }
                neighbors.Clear();
                neighbors = path.GetNeighbors();
                if(neighbors.Count==0)
                {
                    continue;
                }
                foreach(var neighbor in neighbors)
                {
                    neighbor.SetWeight(HitTest.GetDistanceSquare(neighbor, destination));
                }
                
                while(neighbors.Count>0)
                {
                    node = neighbors[rand.Next(0, neighbors.Count())];
                    neighbors.Remove(node);
                    if (!CoordVerifier.IsBlocked(node) || node.IsSameSpot(destination))
                    {
                        node.SetWeight(Point2.CalculateDistanceSquared(node, path.GetLastStep()));
                        var newPath = PathFactory.Create(path);
                        if(newPath.Add(node))
                        {
                            queue.Enqueue(newPath.GetCost(), newPath);
                            break;    
                        }
                    }
                }
            }
             * */
            return Point2.m_locations[3,3];
        }
    }
}