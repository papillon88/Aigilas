package com.aigilas.statuses.impl;import com.aigilas.creatures.ICreature;import com.aigilas.statuses.IStatus;import com.aigilas.statuses.Status;import com.aigilas.statuses.StatusComponent;    public class ElectrifyStatus extends  IStatus    {        public ElectrifyStatus(ICreature target)            { super(target);            Add(Status.Zap,StatusComponent.Passive );        }    }