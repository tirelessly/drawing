package Manager;

import Bean.Shape;

import java.util.ArrayList;
import java.util.List;

public class IdObserver extends Observer {

    public IdObserver(EditorManager editorManager) {
        this.editorManager = editorManager;
        this.editorManager.attach(this);

    }
    @Override
    public void update() {
        listId = new ArrayList<>();
        for(Shape shape: editorManager.getShapes()) {
            this.listId.add(shape.getId());
        }
        //System.out.println("observer" + this.listId.size());
    }
}