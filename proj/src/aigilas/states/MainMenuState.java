package aigilas.states;

import aigilas.management.Commands;
import spx.io.Contexts;
import spx.io.Input;
import spx.net.Client;
import spx.states.State;
import spx.states.StateManager;
import spx.text.ActionTextHandler;

public class MainMenuState implements State {
    private ActionTextHandler _text = new ActionTextHandler();

    private static final String PlayText = "Play Game";
    private static final String OptionsText = "Options";
    private static final String QuitText = "Quit Game";
    private static final String SelectionText = "--<";

    private int _selection = 2;

    public MainMenuState() {
        Input.SetContext(Contexts.Nonfree, Client.Get().GetFirstPlayerIndex());
    }

    @Override
    public void Update() {
        _text.WriteAction(PlayText, 1, 300, 300);
        _text.WriteAction(OptionsText, 1, 300, 200);
        _text.WriteAction(QuitText, 1, 300, 100);

        _selection += (Input.IsActive(Commands.MoveUp, Client.Get().GetFirstPlayerIndex()) ? 1 : 0) + (Input.IsActive(Commands.MoveDown, Client.Get().GetFirstPlayerIndex()) ? -1 : 0);
        _selection %= 3;
        if (_selection < 0) {
            _selection = 0;
        }

        if (Client.Get().IsGameStarting()) {
            for (int ii = 0; ii < Client.Get().GetPlayerCount(); ii++) {
                Input.SetContext(Contexts.Free, ii);
            }

            StateManager.LoadState(new GameplayState());
        } else {
            if (Input.IsActive(Commands.Confirm, Client.Get().GetFirstPlayerIndex())) {
                switch (_selection) {
                    case 2:
                        System.out.println("Starting the game");
                        Client.Get().StartGame();
                        return;
                    case 1:
                        StateManager.LoadState(new OptionsState());
                        return;
                    case 0:
                        System.exit(0);
                        return;
                    default:
                        break;
                }
            }
        }

        _text.WriteAction(SelectionText, 1, 225, 100 * (_selection + 1));
    }

    @Override
    public void LoadContent() {

    }

    @Override
    public void Draw() {
        _text.Draw();
        _text.Clear();
    }
}
