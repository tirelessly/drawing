package Manager;

public class FactoryProducer {

    public static AbstractFactory getFactory(String choice) {

        if (choice.equalsIgnoreCase("SHAPE")) {
            return new EditorManager();
        }
        return null;
    }
}
