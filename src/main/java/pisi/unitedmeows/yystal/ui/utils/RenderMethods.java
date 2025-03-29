/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package pisi.unitedmeows.yystal.ui.utils;

import org.lwjgl.opengl.GL11;

public class RenderMethods {
    public static void drawRect(float startX, float startY, float endX, float endY, int startColor, int endColor) {
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)((float)(startColor >> 16 & 0xFF) / 255.0f), (float)((float)(startColor >> 8 & 0xFF) / 255.0f), (float)((float)(startColor & 0xFF) / 255.0f), (float)((float)(startColor >> 24 & 0xFF) / 255.0f));
        GL11.glVertex2d((double)startX, (double)startY);
        GL11.glColor4f((float)((float)(endColor >> 16 & 0xFF) / 255.0f), (float)((float)(endColor >> 8 & 0xFF) / 255.0f), (float)((float)(endColor & 0xFF) / 255.0f), (float)((float)(endColor >> 24 & 0xFF) / 255.0f));
        GL11.glVertex2d((double)startX, (double)endY);
        GL11.glVertex2d((double)endX, (double)endY);
        GL11.glColor4f((float)((float)(startColor >> 16 & 0xFF) / 255.0f), (float)((float)(startColor >> 8 & 0xFF) / 255.0f), (float)((float)(startColor & 0xFF) / 255.0f), (float)((float)(startColor >> 24 & 0xFF) / 255.0f));
        GL11.glVertex2d((double)endX, (double)startY);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void drawRect(float startX, float startY, float endX, float endY, int color) {
        GL11.glEnable((int)2848);
        GL11.glShadeModel((int)7425);
        GL11.glPushMatrix();
        GL11.glBegin((int)7);
        GL11.glColor4f((float)((float)(color >> 16 & 0xFF) / 255.0f), (float)((float)(color >> 8 & 0xFF) / 255.0f), (float)((float)(color & 0xFF) / 255.0f), (float)((float)(color >> 24 & 0xFF) / 255.0f));
        GL11.glVertex2d((double)startX, (double)startY);
        GL11.glVertex2d((double)startX, (double)endY);
        GL11.glVertex2d((double)endX, (double)endY);
        GL11.glVertex2d((double)endX, (double)startY);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable((int)2848);
        GL11.glShadeModel((int)7424);
    }

    public static void drawBorderedRect(float startX, float startY, float endX, float endY, float borderSize, int insideColor, int borderColor) {
        RenderMethods.drawRect(startX + borderSize, startY + borderSize, endX - borderSize, endY - borderSize, insideColor);
        RenderMethods.drawRect(startX + borderSize, startY, endX - borderSize, startY + borderSize, borderColor);
        RenderMethods.drawRect(startX, startY, startX + borderSize, endY, borderColor);
        RenderMethods.drawRect(endX - borderSize, startY, endX, endY, borderColor);
        RenderMethods.drawRect(startX + borderSize, endY - borderSize, endX - borderSize, endY, borderColor);
    }

    public static void drawRoundedRect(float x2, float y2, float x1, float y1, int color) {
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, color);
        RenderMethods.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, color);
        RenderMethods.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, color);
        RenderMethods.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, color);
        RenderMethods.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, color);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, color);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, color);
        RenderMethods.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, color);
        GL11.glPushMatrix();
        RenderMethods.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, color);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public static void drawRoundedRect(float x2, float y2, float x1, float y1, int color, int color2) {
        GL11.glPushMatrix();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, color, color2);
        RenderMethods.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, color, color2);
        RenderMethods.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, color, color2);
        RenderMethods.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, color, color2);
        RenderMethods.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, color, color2);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, color, color2);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, color, color2);
        RenderMethods.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, color, color2);
        GL11.glPushMatrix();
        RenderMethods.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, color, color2);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public static void drawRoundedBorderedRect(float startX, float startY, float endX, float endY, float borderSize, int insideC, int borderC) {
        RenderMethods.drawRoundedRect(startX - borderSize, startY - borderSize, endX + borderSize, endY + borderSize, borderC);
        RenderMethods.drawRoundedRect(startX, startY, endX, endY, insideC);
    }

    public static void drawRoundedBorderedRect(float startX, float startY, float endX, float endY, float borderSize, int insideC, int insideC2, int borderC) {
        RenderMethods.drawRoundedRect(startX - borderSize, startY - borderSize, endX + borderSize, endY + borderSize, borderC);
        RenderMethods.drawRoundedRect(startX, startY, endX, endY, insideC, insideC2);
    }

    public static void drawRoundedBorderedRect(float x2, float y2, float x1, float y1, int borderC, int insideC) {
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        RenderMethods.drawVLine(x2 *= 2.0f, (y2 *= 2.0f) + 1.0f, (y1 *= 2.0f) - 2.0f, borderC);
        RenderMethods.drawVLine((x1 *= 2.0f) - 1.0f, y2 + 1.0f, y1 - 2.0f, borderC);
        RenderMethods.drawHLine(x2 + 2.0f, x1 - 3.0f, y2, borderC);
        RenderMethods.drawHLine(x2 + 2.0f, x1 - 3.0f, y1 - 1.0f, borderC);
        RenderMethods.drawHLine(x2 + 1.0f, x2 + 1.0f, y2 + 1.0f, borderC);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y2 + 1.0f, borderC);
        RenderMethods.drawHLine(x1 - 2.0f, x1 - 2.0f, y1 - 2.0f, borderC);
        RenderMethods.drawHLine(x2 + 1.0f, x2 + 1.0f, y1 - 2.0f, borderC);
        RenderMethods.drawRect(x2 + 1.0f, y2 + 1.0f, x1 - 1.0f, y1 - 1.0f, insideC);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }

    public static void drawImage(int textureId, float x, float y, float width, float height) {
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBindTexture((int)3553, (int)textureId);
        GL11.glTranslatef((float)x, (float)y, (float)0.0f);
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)0.0f, (float)0.0f);
        GL11.glVertex2f((float)0.0f, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)0.0f);
        GL11.glVertex2f((float)width, (float)0.0f);
        GL11.glTexCoord2f((float)1.0f, (float)1.0f);
        GL11.glVertex2f((float)width, (float)height);
        GL11.glTexCoord2f((float)0.0f, (float)1.0f);
        GL11.glVertex2f((float)0.0f, (float)height);
        GL11.glEnd();
        GL11.glLoadIdentity();
        GL11.glDisable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static void drawCircle(float cx, float cy, float r, int num_segments, int c) {
        r *= 2.0f;
        cx *= 2.0f;
        cy *= 2.0f;
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        float theta = (float)(6.2831852 / (double)num_segments);
        float p = (float)Math.cos(theta);
        float s = (float)Math.sin(theta);
        float x = r;
        float y = 0.0f;
        RenderMethods.enableGL2D();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)f1, (float)f2, (float)f3, (float)f);
        GL11.glBegin((int)2);
        for (int ii = 0; ii < num_segments; ++ii) {
            GL11.glVertex2f((float)(x + cx), (float)(y + cy));
            float t = x;
            x = p * x - s * y;
            y = s * t + p * y;
        }
        GL11.glEnd();
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        RenderMethods.disableGL2D();
    }

    public static void drawHLine(float x, float y, float x1, int color) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }
        RenderMethods.drawRect(x, x1, y + 1.0f, x1 + 1.0f, color);
    }

    public static void drawVLine(float x, float y, float x1, int color) {
        if (x1 < y) {
            float var5 = y;
            y = x1;
            x1 = var5;
        }
        RenderMethods.drawRect(x, y + 1.0f, x + 1.0f, x1, color);
    }

    public static void drawHLine(float x, float y, float x1, int color, int color2) {
        if (y < x) {
            float var5 = x;
            x = y;
            y = var5;
        }
        RenderMethods.drawRect(x, x1, y + 1.0f, x1 + 1.0f, color, color2);
    }

    public static void drawVLine(float x, float y, float x1, int color, int color2) {
        if (x1 < y) {
            float var5 = y;
            y = x1;
            x1 = var5;
        }
        RenderMethods.drawRect(x, y + 1.0f, x + 1.0f, x1, color, color2);
    }

    public static void enableGL2D() {
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
    }

    public static void disableGL2D() {
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }
}

