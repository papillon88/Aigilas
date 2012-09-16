package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.statuses.IStatus;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import spx.bridge.ActorType;

public class BoilStatus extends IStatus {
    private Strategy previousStrategy;
    private float _previousHealth = 0;
    private boolean _countDownFailed = false;
    private static final int _countdownMax = 10;
    private int _countdown = _countdownMax;

    public BoilStatus(ICreature target)

    {
        super(target);

    }

    @Override
    public void Setup() {
        super.Setup();
        previousStrategy = _target.GetStrategyId();
        _target.SetStrategy(StrategyFactory.Create(null, _target));
        _target.GetTargets().AddTargetTypes(ActorType.PLAYER);
    }

    @Override
    public void Cleanup() {
        super.Cleanup();
        if (!_countDownFailed) {
            _target.GetTargets().FindClosest().ApplyDamage(30);
        }
        _target.SetStrategy(StrategyFactory.Create(previousStrategy, _target));
    }

    @Override
    public void Update() {
        super.Update();
        if (_target.IsCooledDown()) {
            _countdown--;
            _target.Write(spx.util.StringStorage.Get(_strength));
            if (_countdown <= 0) {
                _countdown = _countdownMax;
            }
        }
        if (_target.Get(StatType.HEALTH) < _previousHealth) {
            _countDownFailed = true;
            Cleanup();
        }
        _previousHealth = _target.Get(StatType.HEALTH);
    }
}