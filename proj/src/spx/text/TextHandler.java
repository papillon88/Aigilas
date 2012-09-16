package spx.text;

import spx.core.Point2;
import spx.core.SpxManager;

import java.util.ArrayList;
import java.util.List;

public class TextHandler {
    private final DefaultHudText[] defaultPool = new DefaultHudText[100];
    private int defaultIndex = 0;

    private final List<Text> _contents = new ArrayList<Text>();

    public TextHandler() {
        for (int ii = 0; ii < defaultPool.length; ii++) {
            defaultPool[ii] = new DefaultHudText(1f);
        }
    }

    public void WriteDefault(String contents, int x, int y, Point2 origin) {
        defaultPool[defaultIndex].Reset(contents, x, y, origin);
        Add(defaultPool[defaultIndex]);
        defaultIndex = (defaultIndex + 1) % defaultPool.length;
    }

    public void Add(Text textToAdd) {
        if (!_contents.contains(textToAdd)) {
            _contents.add(textToAdd);
        }
    }

    public void Clear() {
        _contents.clear();
    }

    public void Update() {
        for (int ii = 0; ii < _contents.size(); ii++) {
            if (_contents.get(ii).Update() <= 0) {
                _contents.remove(_contents.get(ii));
                ii--;
            }
        }
    }

    public void Draw() {

        if (SpxManager.Renderer != null) {
            for (Text component : _contents) {
                component.Draw();
            }
        }
    }
}
