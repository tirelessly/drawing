package Manager;

import java.util.*;

public class LayerManager implements SVG {

    Map<Integer, AbstractFactory> abstractFactoryMap = new HashMap<>();
    Map<Integer, IdObserver> observersMap = new HashMap<>();
    int choice;
    String canvas;

    private RealSVG realSVG;


    /**
     * By default one Layer exists.
     * Key is an Id of Layer
     * Value is an Abstract Factory Object
     */
    LayerManager() {
        abstractFactoryMap.put(0, addAbstractFactory());
        observersMap.put(0, addObserver(abstractFactoryMap.get(0)));
        //canvas ="<svg height=\"800\" width=\"800\" xmlns=\"http://www.w3.org/2000/svg\">";
    }


    /**
     * Add Layer if does not exist
     * And set choice to user chosen Layer
     *
     * @param Id
     */
    public void chooseLayer(int Id) {
        abstractFactoryMap.putIfAbsent(Id, addAbstractFactory());
        observersMap.putIfAbsent(Id, addObserver(abstractFactoryMap.get(Id)));
        setChoice(Id);
    }

    /**
     * Abstract Factory object ShapeFactory is produced
     *
     * @return abstractFactory
     */
    private AbstractFactory addAbstractFactory() {
        AbstractFactory abstractFactory = FactoryProducer.getFactory("SHAPE");
        return abstractFactory;
    }

    /**
     * A new observer is added to the Abstract Factory
     *
     * @param abstractFactory
     * @return idObserver
     */
    private IdObserver addObserver(AbstractFactory abstractFactory) {
        IdObserver idObserver = new IdObserver((EditorManager) abstractFactory);
        return idObserver;
    }

    /**
     * @return values of a map
     */
    public Collection<AbstractFactory> getAbstractFactoryMap() {
        return abstractFactoryMap.values();
    }


    /**
     * get chosen layer id
     *
     * @return choice
     */
    public int getChoice() {
        return choice;
    }

    /**
     * set chosen layer id
     *
     * @param choice
     */
    public void setChoice(int choice) {
        this.choice = choice;
    }


    /**
     * Returns the Abstarct Factory object by chosen layer
     *
     * @return
     */
    public AbstractFactory getAbstractFactory() {
        System.out.println("getAbsFa = " + abstractFactoryMap.get(getChoice()).getShapes().size());
        return abstractFactoryMap.get(getChoice());
    }

    /**
     * @return observer adjusted to the corresponding layer
     */
    public IdObserver getObserver() {
        return observersMap.get(getChoice());
    }

    /**
     * it is method of the proxy pattern, generates html as a proxy image
     */
    public void updateProxySVG() {
        String figure="<svg height=\"800\" width=\"800\" xmlns=\"http://www.w3.org/2000/svg\">";
        for (AbstractFactory a : this.getAbstractFactoryMap()) {
            if (a.getShapes().size() > 0) {
                for (int i = 0; i < a.getShapes().size(); i++) {
                    figure=figure+a.getShapes().get(i).generateHtml();
                }
            }
        }
        this.canvas=figure;
    }

    /**
     * @return canvas
     */
    public String getCanvas() {
        return canvas;
    }

    /**
     * method of proxy pattern, implements SVG interface
     */
    @Override
    public void saveSVG() {
        realSVG = new RealSVG(this.canvas);
        realSVG.saveSVG();
    }
}



