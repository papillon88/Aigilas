package spx.entities;

import spx.bridge.ActorType;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.RNG;
import spx.core.Settings;
import spx.core.SpxManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityManager {
    private static List<IEntity> _contents = new ArrayList<IEntity>();
    private static HashMap<Point2, List<IEntity>> _gridContents = new HashMap<Point2, List<IEntity>>();

    public static IEntity addObject(IEntity Entity) {
        Entity.LoadContent();
        _contents.add(Entity);
        addToGrid(Entity);
        return Entity;
    }

    public static IEntity GetObject(EntityType type) {
        if (_contents != null) {
            for (IEntity entity : _contents) {
                if (entity.GetEntityType() == type) {
                    return entity;
                }
            }
        }
        return null;
    }

    private static List<IEntity> gopResults = new ArrayList<IEntity>();

    public static List<IEntity> GetEntities(EntityType type, Point2 target)

    {
        if (_contents != null) {
            goResults.clear();
            goResults.addAll(GetObjects(type));
            gopResults.clear();
            for (int ii = 0; ii < goResults.size(); ii++) {
                if (goResults.get(ii).contains(target)) {
                    gopResults.add(goResults.get(ii));
                }
            }
            return gopResults;
        }
        return null;
    }

    private static List<IEntity> goResults = new ArrayList<IEntity>();

    public static List<IEntity> GetObjects(EntityType type) {
        goResults.clear();
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).GetEntityType() == type) {
                goResults.add(_contents.get(ii));
            }
        }
        return goResults;
    }

    // CT Accessors
    public static IActor GetActor(ActorType type) {
        if (_contents == null) {
            return null;
        }
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).GetEntityType() == EntityType.ACTOR && ((IActor) _contents.get(ii)).GetActorType() == type) {
                return ((IActor) _contents.get(ii));
            }
        }
        return null;
    }

    private static List<IActor> creatures = new ArrayList<IActor>();

    public static List<IActor> GetActors(ActorType type) {
        creatures.clear();
        if (type != ActorType.NONPLAYER) {
            for (IEntity elem : _contents) {
                if (elem.GetEntityType() == EntityType.ACTOR) {
                    if (((IActor) elem).GetActorType() == type) {
                        creatures.add(((IActor) elem));
                    }
                }
            }
        } else {
            for (IEntity elem : _contents) {
                if (elem.GetEntityType() == EntityType.ACTOR) {
                    if (((IActor) elem).GetActorType() != ActorType.PLAYER) {
                        creatures.add(((IActor) elem));
                    }
                }
            }
        }
        return creatures;
    }

    private static IActor _nextResult;

    public static List<IActor> GetActorsAt(Point2 target, ActorType actorType) {
        creatures.clear();
        for (IEntity elem : _gridContents.get(target)) {
            if (elem.GetEntityType() == EntityType.ACTOR) {
                _nextResult = (IActor) elem;
                if (actorType == null || _nextResult.GetActorType() == actorType || (actorType == ActorType.NONPLAYER && _nextResult.GetActorType() != ActorType.PLAYER)) {
                    creatures.add(_nextResult);
                }
            }
        }
        return creatures;
    }

    public static List<IActor> GetActorsAt(Point2 target) {
        return GetActorsAt(target, null);
    }

    private static List<IActor> creatures2 = new ArrayList<IActor>();

    public static List<IActor> GetActorsSurrounding(Point2 target, int distance)

    {
        creatures2.clear();
        for (int ii = -distance; ii < distance + 1; ii++) {
            for (int jj = -distance; jj < distance + 1; jj++) {
                if (ii != 0 || jj != 0) {
                    for (IActor creature : GetActorsAt(target.Add(new Point2(ii, jj)), null)) {
                        creatures2.add(creature);
                    }
                }
            }
        }
        return creatures2;
    }

    public static boolean IsLocationBlocked(Point2 location) {
        for (IEntity elem : _gridContents.get(location)) {
            if (elem.IsBlocking()) {
                return true;
            }
        }
        return false;
    }

    private static List<IEntity> gotcResults;

    public static List<IEntity> GetObjectsToCache() {
        gotcResults.clear();
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).GetEntityType() != EntityType.FLOOR) {
                gotcResults.add(_contents.get(ii));
            }
        }
        return gotcResults;
    }

    public static IActor GetNearestPlayer(IEntity target) {
        List<IActor> actors = GetActors(ActorType.PLAYER);
        if (actors.size() > 0) {
            IEntity closest = actors.get(0);
            IEntity player = null;
            for (int ii = 0; ii < actors.size(); ii++) {
                player = actors.get(ii);
                if (HitTest.GetDistanceSquare(target, player) < HitTest.GetDistanceSquare(target, closest)) {
                    closest = player;
                }
            }
            return (IActor) closest;
        }
        return null;
    }

    public static IActor GetNearestPlayer(IActor target)

    {
        return GetNearestPlayer((IEntity) target);
    }

    public static void addObjects(List<IEntity> cache) {
        _contents.addAll(cache);
    }

    public static boolean AnyContains(Point2 target, EntityType type) {
        for (IEntity entity : _gridContents.get(target)) {
            if (entity.GetEntityType() == type) {
                return true;
            }
        }
        return false;
    }

    public static void RemoveObject(IEntity target) {
        _contents.remove(target);
    }

    public static void Clear() {
        _contents = new ArrayList<IEntity>();
        _gridContents = new HashMap<Point2, List<IEntity>>();
    }

    public static void Reset() {
        _contents = new ArrayList<IEntity>();
        _gridContents = new HashMap<Point2, List<IEntity>>();
        creatures.clear();
        LoadContent();
    }

    public static void Update() {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (ii >= _contents.size()) {
                return;
            }
            if (!_contents.get(ii).IsActive()) {
                _gridContents.get(_contents.get(ii).GetLocation()).remove(_contents.get(ii));
                _contents.remove(_contents.get(ii));
                ii--;
                continue;
            }
            _contents.get(ii).Update();
        }
    }

    public static void Draw() {
        if (SpxManager.Renderer != null) {
            for (IEntity component : _contents) {
                component.Draw();
            }
        }
    }

    public static void LoadContent() {
        if (SpxManager.Renderer != null) {
            for (IEntity component : _contents) {
                component.LoadContent();
            }
        }
    }

    private static void addToGrid(IEntity entity) {
        if (!_gridContents.containsKey(entity.GetLocation())) {
            _gridContents.put(entity.GetLocation(), new ArrayList<IEntity>());
        }
        _gridContents.get(entity.GetLocation()).add(entity);
    }

    public static void UpdateGridLocation(IEntity Entity, Point2 oldLocation) {
        if (_gridContents != null && oldLocation != null) {
            _gridContents.get(oldLocation).remove(Entity);
            addToGrid(Entity);
        }
    }

    private static List<IActor> _players = new ArrayList<IActor>();

    public static List<IActor> GetPlayers() {
        _players.clear();
        for (IEntity tile : _contents) {
            if (tile.GetEntityType() == EntityType.ACTOR && ((IActor) tile).GetActorType() == ActorType.PLAYER) {
                _players.add((IActor) tile);
            }
        }
        return _players;
    }

    public static Point2 GetEmptyLocation() {
        List<Point2> emptyLocations = new ArrayList<Point2>();
        for (Point2 location : _gridContents.keySet()) {
            if (location.GridX > 0 && location.GridY > 0 && location.GridX < Settings.Get().tileMapWidth - 1 && location.GridY < Settings.Get().tileMapHeight - 1) {
                boolean exclude = false;
                for (int ii = 0; ii < _gridContents.get(location).size(); ii++) {
                    if (_gridContents.get(location).get(ii).GetEntityType() == EntityType.ACTOR) {
                        exclude = true;
                    }
                }
                if (!exclude) {
                    emptyLocations.add(location);
                }
            }
        }
        return emptyLocations.get(RNG.Next(0, emptyLocations.size()));
    }

    public static List<IEntity> GetEntitiesToCache() {
        List<IEntity> results = new ArrayList<IEntity>();
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).GetEntityType() != EntityType.FLOOR) {
                results.add(_contents.get(ii));
            }
        }
        return results;
    }

    public static IActor GetTouchingCreature(IEntity entity) {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).GetEntityType() == EntityType.ACTOR) {
                if (((IActor) _contents.get(ii)).contains(entity.GetLocation())) {
                    return (IActor) _contents.get(ii);
                }
            }
        }
        return null;
    }
}
