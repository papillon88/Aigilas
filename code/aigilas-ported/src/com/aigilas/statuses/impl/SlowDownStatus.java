package com.aigilas.statuses.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatBuff;import com.aigilas.creatures.StatType;import com.aigilas.statuses.IStatus;    public class SlowDownStatus  extends  IStatus    {        public SlowDownStatus(ICreature target)            { super(target);            _buff = new StatBuff(StatType.MOVE_COOL_DOWN, 10);            _buffMax = true;            Setup();        }    }