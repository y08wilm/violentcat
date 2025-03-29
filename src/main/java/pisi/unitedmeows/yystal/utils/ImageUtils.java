/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
    public static byte[] toByteArray(BufferedImage bi, ImageType type) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write((RenderedImage)bi, type.type(), baos);
        }
        catch (IOException e) {
            return null;
        }
        return baos.toByteArray();
    }

    public static BufferedImage toBufferedImage(byte[] bytes) {
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            return ImageIO.read(is);
        }
        catch (IOException ex) {
            return null;
        }
    }

    public static enum ImageType {
        PNG("png"),
        JPG("jpg"),
        GIF("gif");

        String type;

        private ImageType(String type) {
            this.type = type;
        }

        public String type() {
            return this.type;
        }
    }
}

