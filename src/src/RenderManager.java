import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderManager {
    private final WindowManager window;

    public RenderManager(){
        window = Main.getWindow();
    }

    public void init() throws Exception{

    }


    public void render(Model model){
        clear();
        GL30.glBindVertexArray(model.getId());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL15.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }

    public void clear(){
        GL15.glClear(GL15.GL_COLOR_BUFFER_BIT | GL15.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup(){

    }
}

