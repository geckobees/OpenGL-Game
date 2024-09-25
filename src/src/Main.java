import org.lwjgl.Version;

public class Main {

    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        WindowManager window = new WindowManager(1600, "game", 900, false);
        window.init();

        while(!window.windowShouldClose()){
            window.update();
        }

        window.cleanup();
    }

}