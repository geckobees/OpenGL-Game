import org.lwjgl.system.MemoryUtil;

import java.io.InputStream;
import java.nio.IntBuffer;
import java.util.Scanner;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data).flip();
        return buffer;
    }

    public static String loadResource(String filename) throws Exception{
        String result;
        System.out.println("Loading resource: " + filename);
        InputStream in = Utils.class.getResourceAsStream(filename);
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8)) {
            result = scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
