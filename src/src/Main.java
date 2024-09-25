import org.lwjgl.Version;

public class Main {

    private static WindowManager window;
    private static EngineManager engine;

    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        window = new WindowManager(1600, Constants.TITLE, 900, false);
        engine = new EngineManager();

        try {
            engine.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        window.cleanup();
    }

    public static WindowManager getWindow(){
        return window;
    }

}