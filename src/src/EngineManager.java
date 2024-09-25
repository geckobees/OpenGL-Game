import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

public class EngineManager {
    public static final long NANOSECOND = 1000000000L;// one second in nanoseconds
    public static final float FRAMERATE = 1000;

    public static int fps;
    private static float framerate = 1.0f / FRAMERATE;

    private boolean isRunning;

    private WindowManager window;
    private GLFWErrorCallback errorCallback;

    private void init() throws Exception {
        GLFW.glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        window = Main.getWindow();
        window.init();
    }

    public void start() throws Exception {
        init();
        if (isRunning)
            return;
        run();
    }
    private void run(){
        this.isRunning = true;
        int frames = 0;
        long frameCounter = 0;
        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (isRunning){
            boolean render = false;
            long startTime = System.nanoTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime; //sets the lastTime to the startTime for the next loop

            unprocessedTime += passedTime / (double) NANOSECOND;
            frameCounter += passedTime;

            input();

            while (unprocessedTime > framerate){
                render = true;
                unprocessedTime -= framerate;

                if (window.windowShouldClose())
                    stop();

                if (frameCounter >= NANOSECOND) {
                    setFps(frames);
                    window.setTitle("Game Engine FPS: " + getFps());
                    frames = 0;
                    frameCounter = 0;
                }
            }

            if (render) {
                update();
                render();
                frames++;
            }
        }
        cleanup();
    }

    private void stop(){
        if (!isRunning)
            return;
        isRunning = false;
    }

    private void input() {

    }

    private void render(){
        window.update();
    }

    private void update(){

    }

    private void cleanup(){
        window.cleanup();
        errorCallback.free();
        GLFW.glfwTerminate();
    }

    public static int getFps() {
        return fps;
    }

    public static void setFps(int fps) {
        EngineManager.fps = fps;
    }
}
