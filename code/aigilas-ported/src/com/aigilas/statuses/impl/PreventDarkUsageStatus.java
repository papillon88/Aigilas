package com.aigilas.statuses.impl;import com.aigilas.creatures.ICreature;import com.aigilas.entities.Elements;import com.aigilas.statuses.IStatus;    public class PreventDarkUsageStatus  extends  IStatus    {        public PreventDarkUsageStatus(ICreature target)            { super(target);            _blockedElements.add(Elements.DARK);        }    }