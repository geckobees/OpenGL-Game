import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL30;

public class TestGame implements ILogic{


    private int direction = 0;

    private float color = 0.0f;

    private final RenderManager renderer;
    public final ObjectLoader loader;
    private final WindowManager window;

    private Model model;

    public TestGame() {
        window = Main.getWindow();
        renderer = new RenderManager();
        loader = new ObjectLoader();
    }

    @Override
    public void init() throws Exception{
        renderer.init();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        model = loader.loadModel(vertices, indices);
        System.out.println("STILL WORKING");

    }

    @Override
    public void update() {
        color += direction * 0.01f;
        if (color > 1)
            color = 1.0f;
        else if (color <= 0)
            color = 0.0f;
    }

    @Override
    public void render() {
        if (window.isResize()){
            GL30.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(true);

        }
        window.setClearColor(color, color, color, 0.0f);
        renderer.render(model);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }

    @Override
    public void input() {
        if (window.isKeyPressed(GLFW.GLFW_KEY_W))
            direction = 1;
        else if (window.isKeyPressed(GLFW.GLFW_KEY_S))
            direction = -1;
        else
            direction = 0;
    }
}
