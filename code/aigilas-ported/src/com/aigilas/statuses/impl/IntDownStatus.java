package com.aigilas.statuses.impl;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatBuff;import com.aigilas.creatures.StatType;import com.aigilas.statuses.IStatus;    public class IntDownStatus  extends  IStatus    {        public IntDownStatus(ICreature target)            { super(target);            _buff = new StatBuff(StatType.WISDOM, -10);            Setup();        }    }