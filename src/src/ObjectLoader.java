import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30C;

import javax.xml.crypto.Data;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class ObjectLoader {
    private List<Integer> vaos = new ArrayList<>();// vertex array object
    private List<Integer> vbos = new ArrayList<>();// vertex buffer object

    public Model loadModel(float[] vertices){
        int id = createVAO();
        storeDataInAttribList(0, 3, vertices);
        unbind();
        return new Model(id, vertices.length / 3);

    }

    private int createVAO(){
        int id = GL30.glGenVertexArrays();
        vaos.add(id);
        GL30.glBindVertexArray(id);
        return id;
    }
    private void storeDataInAttribList(int attribNo /* attribute number */ , int vertexCount, float[] data) {
        int vbo = GL30.glGenBuffers();
        vbos.add(vbo);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vbo);
        FloatBuffer buffer = Utils.storeDataInFloatBuffer(data);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, buffer, GL30.GL_STATIC_DRAW);
        GL30.glVertexAttribPointer(attribNo, vertexCount, GL30.GL_FLOAT, false, 0, 0);
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, 0);
    }
    public void unbind(){
        GL30.glBindVertexArray(0);
    }
    public void cleanup(){
        for (int vao : vaos){
            GL30.glDeleteVertexArrays(vao);
        }
        for (int vbo : vaos){
            GL30.glDeleteBuffers(vbo);
        }
    }
}
