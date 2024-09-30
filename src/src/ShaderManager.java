import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ShaderManager {

    private final int programID;
    private int vertexShaderID, fragmentShaderID;

    public ShaderManager() throws Exception{
        programID = GL30.glCreateProgram();
        if (programID == 0){
            throw new Exception("Could not create the Shader.");
        }
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderID = createShader(shaderCode, GL30.GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        vertexShaderID = createShader(shaderCode, GL30.GL_VERTEX_SHADER);
    }

    public int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderID = GL30.glCreateShader(shaderType);
        if(shaderID == 0){
            throw new Exception("Error Creating Shader");
        }

        GL30.glShaderSource(shaderID, shaderCode);
        GL30.glCompileShader(shaderID);

        if (GL30.glGetShaderi(shaderID, GL30.GL_COMPILE_STATUS) == 0){
            throw new Exception("Error compiling shader code: TYPE: " + shaderType + " info " +
                    GL30.glGetShaderInfoLog(shaderID, 1024));
        }
        GL30.glAttachShader(programID, shaderID);
        return shaderID;
    }

    public void link() throws Exception {
        GL30.glLinkProgram(programID);
        if (GL30.glGetProgrami(programID, GL30.GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking shader code: TYPE: " + " info " +
                    GL30.glGetProgramInfoLog(programID, 1024));
        }

        if (vertexShaderID != 0){
            GL30.glDetachShader(programID, vertexShaderID);
        }

        if (fragmentShaderID != 0){
            GL30.glDetachShader(programID, fragmentShaderID);
        }

        GL30.glValidateProgram(programID);
        if (GL30.glGetProgrami(programID, GL30.GL_VALIDATE_STATUS) == 0){
            throw new Exception("Error validating program: " + GL30.glGetProgramInfoLog(programID, 1024));
        }

    }

    public void bind() {
        GL30.glUseProgram(programID);
    }

    public void unbind(){
        GL30.glUseProgram(0);
    }

    public void cleanup(){
        unbind();
        if (programID != 0){
            GL30.glDeleteProgram(programID);
        }
    }
}
