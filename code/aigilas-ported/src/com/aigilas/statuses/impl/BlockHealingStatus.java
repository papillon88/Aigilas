package com.aigilas.statuses.impl;import com.aigilas.creatures.CreatureAction;import com.aigilas.creatures.ICreature;import com.aigilas.statuses.IStatus;    public class BlockHealingStatus  extends  IStatus    {        public BlockHealingStatus(ICreature target)            { super(target);            _prevents.add(CreatureAction.ReceiveHealing);        }    }