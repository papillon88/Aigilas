package com.aigilas.creatures;
public     class VaporCloud  extends  Minion
    {
        private ICreature _host = null;
        public VaporCloud()
            {
            Add(SkillId.VAPOR_CLOUD);
            _composition.add(Elements.WATER);
        }
@Override
        {
            super.Update();
            if (_host == null)
            {
                for (IActor creature:EntityManager.GetActorsAt(_location))
                {
                    if (creature != this)
                    {
                        _host = (ICreature)creature;
                    }
                }
                if (_host == null)
                {
                    SetInactive();
                }
            }
            if (_host != null)
            {
                SetLocation(_host.GetLocation());
            }
        }
    }