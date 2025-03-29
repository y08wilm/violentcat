/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package pisi.unitedmeows.yystal.ui;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.ui.YWindow;
import pisi.unitedmeows.yystal.ui.texture.YTexture;
import pisi.unitedmeows.yystal.ui.utils.YShader;
import pisi.unitedmeows.yystal.utils.Vector2f;

public class YUI {
    private static final HashMap<Thread, YWindow> windowMap;
    private static Map<Thread, Map<String, YTexture>> loadedTextures;

    public static Map<String, YTexture> textureMap() {
        return loadedTextures.compute(Thread.currentThread(), (thread, stringYTextureMap) -> new HashMap());
    }

    public static YTexture texture(String name) {
        return YUI.textureMap().getOrDefault(name, null);
    }

    public static YTexture loadTexture(String name, BufferedImage image) {
        YTexture texture = YUI.loadTexture(image);
        YUI.textureMap().put(name, texture);
        return texture;
    }

    public static YTexture loadTextureRGB(String name, BufferedImage image) {
        YTexture texture = YUI.loadTextureRGB(image);
        YUI.textureMap().put(name, texture);
        return texture;
    }

    public static void freeTexture(int textureId) {
        GL11.glDeleteTextures((int)textureId);
    }

    public static YTexture loadTextureRGB(BufferedImage image) {
        ByteBuffer buffer = YUI.createBufferFromImageRGB(image);
        int textureID = GL11.glGenTextures();
        GL11.glBindTexture((int)3553, (int)textureID);
        GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)image.getWidth(), (int)image.getHeight(), (int)0, (int)6407, (int)5121, (ByteBuffer)buffer);
        return new YTexture(textureID);
    }

    public static YTexture loadTexture(BufferedImage image) {
        ByteBuffer buffer = YUI.createBufferFromImageARGB(image);
        int textureID = GL11.glGenTextures();
        GL11.glBindTexture((int)3553, (int)textureID);
        GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
        GL11.glTexParameteri((int)3553, (int)10241, (int)9728);
        GL11.glTexParameteri((int)3553, (int)10240, (int)9728);
        GL11.glTexImage2D((int)3553, (int)0, (int)32856, (int)image.getWidth(), (int)image.getHeight(), (int)0, (int)6408, (int)5121, (ByteBuffer)buffer);
        return new YTexture(textureID);
    }

    public static ByteBuffer createBufferFromImageARGB(BufferedImage image) {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        ByteBuffer buffer = BufferUtils.createByteBuffer((int)(image.getWidth() * image.getHeight() * 4));
        for (int y = 0; y < image.getHeight(); ++y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte)(pixel >> 16 & 0xFF));
                buffer.put((byte)(pixel >> 8 & 0xFF));
                buffer.put((byte)(pixel & 0xFF));
                buffer.put((byte)(pixel >> 24 & 0xFF));
            }
        }
        buffer.flip();
        return buffer;
    }

    public static ByteBuffer createBufferFromImageRGB(BufferedImage image) {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        ByteBuffer buffer = BufferUtils.createByteBuffer((int)(image.getWidth() * image.getHeight() * 3));
        for (int y = 0; y < image.getHeight(); ++y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte)(pixel >> 16 & 0xFF));
                buffer.put((byte)(pixel >> 8 & 0xFF));
                buffer.put((byte)(pixel & 0xFF));
            }
        }
        buffer.flip();
        return buffer;
    }

    public static YShader loadShader(String name) {
        YShader shader = new YShader();
        shader.load(YUI.readShader(name + ".vs"), YUI.readShader(name + ".fs"));
        return shader;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static String readShader(String name) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            String line;
            String parent = (String)YYStal.setting(YSettings.YUI_SHADER_LOCATION);
            reader = parent.equals("$") ? new BufferedReader(new InputStreamReader(YUI.class.getResourceAsStream("/shaders" + name))) : new BufferedReader(new InputStreamReader(new FileInputStream(parent + name)));
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }
        catch (Exception exception) {
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException iOException) {}
            }
        }
        return builder.toString();
    }

    public Vector2f mousePosition() {
        return new Vector2f(YUI.mouseX(), YUI.mouseY());
    }

    public static float mouseX() {
        return YYStal.currentWindow().mouseX();
    }

    public static float mouseY() {
        return YYStal.currentWindow().mouseY();
    }

    public static YWindow currentWindow() {
        return windowMap.getOrDefault(Thread.currentThread(), null);
    }

    public static void registerWindow(YWindow window) {
        windowMap.put(Thread.currentThread(), window);
    }

    public static boolean unregisterWindow() {
        return windowMap.remove(Thread.currentThread()) != null;
    }

    public static boolean unregisterWindow(YWindow window) {
        Iterator<Map.Entry<Thread, YWindow>> iterator = windowMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Thread, YWindow> entry = iterator.next();
            if (entry.getValue() != window) continue;
            iterator.remove();
            return true;
        }
        return false;
    }

    static {
        loadedTextures = new HashMap<Thread, Map<String, YTexture>>();
        windowMap = new HashMap();
    }
}

