package top.chumi.oa.utils;

import org.apache.commons.codec.digest.DigestUtils;
import sun.applet.Main;

public class MD5Utils {
    public static String md5Digest(String source) {
        return DigestUtils.md5Hex(source);
    }

    /**
     * 对源数据加盐混淆后生成MD5摘要
     *
     * @param source 源数据
     * @param salt   盐值
     * @return MD5摘要
     */
    public static String md5Digest(String source, Integer salt) {
        char[] ca = source.toCharArray();
        for (int i = 0; i < ca.length; i++) {
            ca[i] = (char) (ca[i] + salt);
        }
        String target = new String(ca);
        String md5 = DigestUtils.md5Hex(target);
        return md5;
    }
}
