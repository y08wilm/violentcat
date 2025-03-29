/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.violentcat.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import pisi.unitedmeows.yystal.parallel.Async;
import pisi.unitedmeows.yystal.parallel.Future;
import pisi.unitedmeows.yystal.web.client.YWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClientBuilder;
import pisi.unitedmeows.yystal.web.client.YWebResponse;

public class DiscordHelper {
    private static YWebClient webClient = YWebClientBuilder.build();

    public static String route(String endPoint) {
        return String.format("https://discord.com/api/v10/%s", endPoint);
    }

    public static String route(String endPoint, String ... data) {
        return String.format(DiscordHelper.route(endPoint), data);
    }

    public static Future<BufferedImage> avatar(String userID, String avatarID) {
        return DiscordHelper.avatar(userID, avatarID, 0);
    }

    public static Future<BufferedImage> avatar(String userID, String avatarID, int size) {
        return Async.async(() -> {
            String url = DiscordHelper.avatarURL(userID, avatarID, size);
            YWebResponse response = webClient.get(url).run();
            byte[] imageData = response.raw();
            System.out.println(imageData.length);
            try {
                return ImageIO.read(new ByteArrayInputStream(imageData));
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    public static String avatarURL(String userID, String avatarID) {
        return DiscordHelper.avatarURL(userID, avatarID, 0);
    }

    public static String avatarURL(String userID, String avatarID, int size) {
        String url = String.format("https://cdn.discordapp.com/avatars/%s/%s.png", userID, avatarID);
        if (size != 0) {
            url = url + "?size=" + size;
        }
        return url;
    }

    static {
        webClient.header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_3; rv:95.0) Gecko/20010101 Firefox/95.0");
    }
}

