package aigilas.states;

import aigilas.management.Commands;
import com.badlogic.gdx.graphics.Texture;
import spx.core.Point2;
import spx.core.SpxManager;
import spx.io.Input;
import spx.states.State;
import spx.states.StateManager;

public class GameOverState implements State {
    private final Texture _menuBase;

    public GameOverState() {
        _menuBase = SpxManager.GetGameOverAsset();
    }

    @Override
    public void Draw() {
        float x = (SpxManager.WindowWidth - _menuBase.getWidth()) / 2;
        float y = (SpxManager.WindowHeight - _menuBase.getHeight()) / 2;

        SpxManager.Renderer.Draw(_menuBase, new Point2(x, y));
    }

    @Override
    public void Update() {
        if (Input.IsActive(Commands.Confirm, 0, true)) {
            StateManager.LoadState(new GameplayState());
        }
    }

    @Override
    public void LoadContent() {

    }
}
