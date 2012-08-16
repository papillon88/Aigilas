package com.aigilas.gods;
    public class God
    {
        public static God Get(int name)
        {
        }


        private static List<God> __gods;

        private Color _color;
        private int _name;
        private int _goodSacrificeClass;
        private int _badSacrificeClass;

        public String NameText;

        protected God(Color color, int name, int goodSacrifice, int badSacrifice)
        {
            _color = color;
            _name = name;
            _goodSacrificeClass = goodSacrifice;
            _badSacrificeClass = badSacrifice;
            NameText = GodId.Names[_name];
        }

        public Color GetColor()
        {
            return _color;
        }

        public CreatureClass GetClass()
        {
            switch (_name)
            {
                case GodId.ENVY: return new EnvyAcolyte();
                case GodId.GLUTTONY: return new GluttonyAcolyte();
                case GodId.GREED: return new GreedAcolyte();
                case GodId.LUST: return new LustAcolyte();
                case GodId.PRIDE: return new PrideAcolyte();
                case GodId.SLOTH: return new SlothAcolyte();
                case GodId.WRATH: return new WrathAcolyte();
            }
            return new NoClass();
        }

        public boolean IsGoodSacrifice(int sacrifice)
        {
            return sacrifice == _goodSacrificeClass;
        }

        public boolean IsBadSacrifice(int sacrifice)
        {
            return sacrifice == _badSacrificeClass;
        }
    }