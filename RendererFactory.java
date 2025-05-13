/**
 * The Renderer Factory, make the renderer we want
 * We can choose a normal one that we see
 * or a void one(we can't see it)
 */
public class RendererFactory {
    /**
     * a default constructor to the RendererFactory
     */
    RendererFactory(){
    }

    /**
     * decide on which renderer to play
     * @param type of renderer
     * @param size the size of it
     * @return
     */
    public Renderer buildRenderer(String type,int size){
        if (type == null || size <= 0){
            return null;
        }
        Renderer renderer = null;
        type = type.toLowerCase();
        switch (type)
        {
            case "console":
                renderer = new ConsoleRenderer(size);
                break;
            case "none":
                renderer = new VoidRenderer();
                break;
            default:
                renderer = null;
        }
        return renderer;
    }
}
