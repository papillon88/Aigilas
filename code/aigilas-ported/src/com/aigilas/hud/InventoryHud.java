package com.aigilas.hud;import java.util.HashMap;import com.aigilas.creatures.ICreature;import com.aigilas.items.Equipment;import com.aigilas.items.GenericItem;import com.aigilas.items.Inventory;import com.aigilas.items.ItemClass;import com.aigilas.management.Commands;import com.spx.core.Depth;import com.spx.core.XnaManager;import com.spx.io.Input;import com.spx.util.StringSquisher;import com.spx.util.StringStorage;import com.xna.wrapper.Color;import com.xna.wrapper.Rectangle;import com.xna.wrapper.SpriteEffects;import com.xna.wrapper.Vector2;    public class InventoryHud extends IHud    {        private int _currentClass = ItemClass.Values[1];        private Inventory _inventory;        private Equipment _equipment;        private int _endingItem = 4;        private int _startingItem = 0;        private HashMap<GenericItem, Integer> _currentClassItems;        private GenericItem _currentSelectedItem = null;        private EquipmentHud _equipHud;        private DeltasHud _deltas;        public InventoryHud(ICreature owner,Inventory inventory,Equipment equipment){super(owner,XnaManager.WindowWidth/2,XnaManager.WindowHeight/2);            _inventory = inventory;            _equipment = equipment;            _deltas = new DeltasHud(owner,equipment);            _equipHud = new EquipmentHud(owner, equipment);            _currentClassItems = _inventory.GetItems(_currentClass);        }        public void Draw()        {            if (_isVisible)            {                XnaManager.Renderer.Draw(_menuBase,GetHudOrigin(), new Rectangle(0, 0, 1, 1), Color.Black, 0f, new Vector2(0,0), XnaManager.GetCenter(), SpriteEffects.None,Depth.HudBG);                _textHandler.Draw();                _deltas.Draw();                _equipHud.Draw();            }        }        private void HandleInput()        {            if (Input.IsActive(Commands.CycleLeft, _parent.GetPlayerIndex()))            {                _currentClass--;                if (_currentClass <= ItemClass.NULL)                {                    _currentClass = ItemClass.Values.length - 2;                }                _startingItem = 0;                _endingItem = 4;                forceRefresh = true;            }            if (Input.IsActive(Commands.CycleRight, _parent.GetPlayerIndex()))            {                _currentClass++;                if (_currentClass >= ItemClass.LAST)                {                    _currentClass = ItemClass.Values[1];                                    }                _startingItem = 0;                _endingItem = 4;                forceRefresh = true;            }            if (Input.IsActive(Commands.MoveDown, _parent.GetPlayerIndex()))            {                if (_startingItem < _currentClassItems.size() - 1)                {                    _startingItem++;                    _endingItem++;                    forceRefresh = true;                }            }            if (Input.IsActive(Commands.MoveUp, _parent.GetPlayerIndex()))            {                if (_startingItem > 0)                {                    _startingItem--;                    _endingItem--;                    forceRefresh = true;                }            }            if (Input.IsActive(Commands.Confirm, _parent.GetPlayerIndex()))            {                _parent.Equip(_currentSelectedItem);                forceRefresh = true;            }            if (Input.IsActive(Commands.Cancel, _parent.GetPlayerIndex()))            {                _parent.Drop(_currentSelectedItem);                forceRefresh = true;            }        }        public void Update()        {            if (_isVisible)            {                HandleInput();                _textHandler.Update();                _deltas.Update(_currentSelectedItem,forceRefresh);                _equipHud.Update(forceRefresh);                _textHandler.Clear();                UpdateInventoryDisplay();                if (forceRefresh)                {                    forceRefresh = false;                }            }        }@Override        public  void Toggle()        {            super.Toggle();            _deltas.Toggle();            _equipHud.Toggle();            forceRefresh = true;        }        private static HashMap<Integer, String> __classStrings = new HashMap<Integer, String>();        private String GetClassDisplay()        {            if(!__classStrings.containsKey(_currentClass))            {                __classStrings.put(_currentClass, ItemClass.Names.get(_currentClass));            }            return __classStrings.get(_currentClass);        }        private static final String __delim = ")";        private static final String __equipDelim = "~";        private static final String __seper = " x";        private static final String __newline = "\n";        private String displayString = "";        private boolean forceRefresh = false;        private void UpdateInventoryDisplay()        {            _textHandler.WriteDefault(GetClassDisplay(), 20, 30,GetHudOrigin());            _currentClassItems = _inventory.GetItems(_currentClass);            if (_currentClassItems.size() > 0)            {                int ii = 0;                if (forceRefresh)                {                    StringSquisher.Clear();                    for (GenericItem item:_currentClassItems.keySet())                    {                        if (ii == _startingItem)                        {                            _currentSelectedItem = item;                        }                        if(!_equipment.IsRegistered(item)&&_inventory.GetItemCount(item)<=0)                        {                            continue;                        }                                           if (ii >= _startingItem && ii < _endingItem && ii < _currentClassItems.keySet().size())                        {                            StringSquisher.Squish                            (                                StringStorage.Get(ii), __delim,                                (_equipment.IsRegistered(item)) ? __equipDelim : "",                                item.Name);                            if (_currentClassItems.get(item) > -1)                            {                                StringSquisher.Squish(__seper, StringStorage.Get(_currentClassItems.get(item)));                            }                            StringSquisher.Squish(__newline);                        }                        ii++;                    }                    displayString = StringSquisher.Flush();                       }                _textHandler.WriteDefault(displayString, 50, 60, GetHudOrigin());            }        }    }