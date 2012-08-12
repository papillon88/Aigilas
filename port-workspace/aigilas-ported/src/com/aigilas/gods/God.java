package com.aigilas.gods;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.items.*;import com.aigilas.classes.*;
    public class God
    {
        public static God Get(int name)
        {        	for(int ii = 0;ii < __gods.size();ii++){        		if(__gods.get(ii)._name == name){        			return __gods.get(ii);        		}        	}        	return null;
        }


        private static List<God> __gods;        static {        	        	__gods = new ArrayList<God>();        	__gods.add(new God(Color.Pink, GodId.LUST, ItemClass.Leggings, ItemClass.Melee_Weapon));            __gods.add(new God(Color.Gold, GodId.GREED, ItemClass.Head_Gear, ItemClass.Gloves));            __gods.add(new God(Color.Silver, GodId.SLOTH, ItemClass.Shield, ItemClass.Head_Gear));            __gods.add(new God(Color.LightGoldenrodYellow, GodId.ENVY, ItemClass.Torso_Garb, ItemClass.Ranged_Weapon));            __gods.add(new God(Color.Red, GodId.WRATH, ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo));            __gods.add(new God(Color.LightGreen, GodId.GLUTTONY, ItemClass.Gloves, ItemClass.Torso_Garb));            __gods.add(new God(Color.LightBlue, GodId.PRIDE, ItemClass.Ring, ItemClass.Feet));        }                                     

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
