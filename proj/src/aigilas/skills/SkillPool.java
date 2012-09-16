package aigilas.skills;

import aigilas.creatures.ICreature;
import aigilas.management.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillPool {
    private List<SkillId> _skills = new ArrayList<SkillId>();
    private int _currentSkillSlot = 0;
    private ICreature _owner;
    private HashMap<SkillId, Integer> _usageCounter = new HashMap<>();
    private HashMap<Commands, SkillId> _hotSkills = new HashMap<>();

    public SkillPool(ICreature owner) {
        _owner = owner;
    }

    public void Add(SkillId skill) {
        if (skill.equals(null) && _skills.size() == 0) {
            _skills.add(SkillId.NO_SKILL);
            FindCurrent();
            return;
        }
        if (!_skills.contains(skill)) {
            _skills.add(skill);
        }
        if (_skills.contains(SkillId.NO_SKILL)) {
            _skills.remove(SkillId.NO_SKILL);
            _currentSkillSlot = _skills.indexOf(skill);
        }
    }

    private SkillId FindCurrent() {
        return _skills.get(_currentSkillSlot);
    }

    public void Add(List<SkillId> getLevelSkills) {
        if (getLevelSkills.size() == 0) {
            _skills.add(SkillId.NO_SKILL);
            return;
        }
        for (SkillId skill : getLevelSkills) {
            if (!_skills.contains(skill)) {
                _skills.add(skill);
            }
        }
    }

    public void Cycle(int velocity) {
        _currentSkillSlot = (_currentSkillSlot + velocity) % _skills.size();
        if (_currentSkillSlot < 0) {
            _currentSkillSlot = _skills.size() - 1;
        }
        FindCurrent();
    }

    public String GetActiveName() {
        return _skills.size() > 0 ? FindCurrent().Name : SkillId.NO_SKILL.Name;
    }

    private void RemoveNone() {
        _skills.remove(SkillId.NO_SKILL);
    }

    public void UseActive() {
        if (FindCurrent() == SkillId.NO_SKILL) {
            RemoveNone();
            _currentSkillSlot = 0;
        }
        if (_skills.size() > 0) {
            UseSkill(FindCurrent());
        }
    }

    private void UseSkill(SkillId skillId) {
        SkillFactory.Create(FindCurrent()).Activate(_owner);
        if (!_usageCounter.containsKey(skillId)) {
            _usageCounter.put(skillId, 0);
        }
        _usageCounter.put(skillId, _usageCounter.get(skillId) + 1);
    }

    SkillId _leastUsed;

    public void RemoveLeastUsed() {
        for (SkillId skillId : _skills) {
            if (!_usageCounter.containsKey(skillId)) {
                _usageCounter.put(skillId, 0);
            }
        }
        _leastUsed = null;
        for (SkillId key : _usageCounter.keySet()) {
            if ((_leastUsed == null || _leastUsed == SkillId.FORGET_SKILL || _usageCounter.get(key) < _usageCounter.get(_leastUsed)) && key != SkillId.FORGET_SKILL) {
                _leastUsed = key;
            }
        }
        if (_leastUsed != SkillId.FORGET_SKILL) {
            _skills.remove(_leastUsed);
        }
    }

    public int Count() {
        return _skills.size();
    }

    public void MakeActiveSkillHot(Commands hotSkillSlot) {
        if (!_hotSkills.containsKey(hotSkillSlot)) {
            _hotSkills.put(hotSkillSlot, FindCurrent());
        }
        _hotSkills.put(hotSkillSlot, FindCurrent());
    }

    public boolean SetHotSkillsActive(Commands hotkey) {
        if (_hotSkills.containsKey(hotkey)) {
            for (int ii = 0; ii < _skills.size(); ii++) {
                if (_skills.get(ii) == _hotSkills.get(hotkey)) {
                    _currentSkillSlot = ii;
                    FindCurrent();
                    return true;
                }
            }
        }
        return false;
    }

    public String GetHotSkillName(Commands hotSkillSlot) {
        if (_hotSkills.containsKey(hotSkillSlot)) {
            return _hotSkills.get(hotSkillSlot).Name;
        }
        return SkillId.NO_SKILL.Name;
    }

    public float GetCurrentCost() {
        return SkillFactory.GetCost(FindCurrent());
    }

    public SkillId GetActive() {
        return FindCurrent();
    }
}