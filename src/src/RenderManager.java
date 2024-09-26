import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class RenderManager {
    private final WindowManager window;

    public RenderManager(){
        window = Main.getWindow();
    }

    public void init() throws Exception{

    }


    public void render(){

    }

    public void clear(){
        GL15.glClear(GL15.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup(){

    }
}

