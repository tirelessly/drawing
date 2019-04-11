package Manager;

import Bean.Shape;

import java.util.ArrayList;
import java.util.List;

public abstract class Observer {
    protected EditorManager editorManager;
    protected List<Integer> listId = new ArrayList<>();
    public abstract void update();
}
