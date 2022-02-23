package org.shurupov.game.service.process;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_EXTENSIONS;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GraphicSystemService {

    private static final int WIDTH = 1800;
    private static final int HEIGHT = 1000;

    public static final float WHK = ((float) WIDTH) / HEIGHT;

    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private long window;

    public void init() {
        System.out.println("Hello LWJGL3 " + Version.getVersion() + "!");

        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        if (glfwInit() != GLFW_TRUE)
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(WIDTH, HEIGHT, "Sky LWJGL3", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
            }
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - WIDTH) / 2, (vidmode.height() - HEIGHT) / 2);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        glfwShowWindow(window);

        GL.createCapabilities();
        System.out.println("----------------------------");
        System.out.println("OpenGL Version : " + glGetString(GL_VERSION));
        System.out.println("OpenGL Max Texture Size : " + glGetInteger(GL_MAX_TEXTURE_SIZE));
        System.out.println("OpenGL Vendor : " + glGetString(GL_VENDOR));
        System.out.println("OpenGL Renderer : " + glGetString(GL_RENDERER));
        System.out.println("OpenGL Extensions supported by your card : ");
        String extensions = glGetString(GL_EXTENSIONS);
        String[] extArr = extensions.split("\\ ");
        for (int i = 0; i < extArr.length; i++) {
            System.out.println(extArr[i]);
        }
        System.out.println("----------------------------");
    }

    public void beforeUpdate() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void afterUpdate() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void gracefulShutdown() {

    }

    public void forceShutdown() {

    }

    public boolean isRequestedToClose() {
        return glfwWindowShouldClose(window) == GLFW_TRUE;
    }
}
