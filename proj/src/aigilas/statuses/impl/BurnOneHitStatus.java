package aigilas.statuses.impl;

import aigilas.creatures.ICreature;
import aigilas.statuses.IStatus;
import aigilas.statuses.Status;
import aigilas.statuses.StatusComponent;

public class BurnOneHitStatus extends IStatus {
    public BurnOneHitStatus(ICreature target)

    {
        super(target);

        Add(Status.Burn, StatusComponent.Contagion);
    }

    @Override
    public void Update() {
        super.Update();
        if (_wasPassed) {
            _isActive = false;
        }
    }
}
