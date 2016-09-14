package me.yifeiyuan.climb.tools.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by 程序亦非猿 on 16/8/19.
 *
 * http://tech.meituan.com/mt-apk-packaging.html
 */
public class ChannelUtil {

    private static final String TAG = "ChannelUtil";
    private static final String CHANNEL_KEY = "channel";

    private static String sChannel;

    /**
     * @param context
     *
     * @return 渠道 默认为空
     */
    public static String getChannel(Context context) {
        return getChannel(context, "");
    }

    /**
     * @param context
     * @param defaultChannel 默认值
     *
     * @return 渠道
     */
    public static String getChannel(Context context, String defaultChannel) {
        //内存中获取
        if (!TextUtils.isEmpty(sChannel)) {
            return sChannel;
        }
        //从apk中获取
        sChannel = getChannelFromApk(context, CHANNEL_KEY);
        //全部获取失败
        return defaultChannel;
    }

    /**
     * 从apk中获取版本信息
     *
     * @param context
     * @param channelKey
     *
     * @return
     */
    private static String getChannelFromApk(Context context, String channelKey) {
        //从apk包中获取
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        //默认放在meta-inf/里
        String key = "META-INF/" + channelKey;
        String ret = "";
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.startsWith(key)) {
                    ret = entryName;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String[] split = ret.split("_");
        String channel = "";
        if (split.length >= 2) {
            channel = ret.substring(split[0].length() + 1);
        }
        Log.i(TAG, "getChannelFromApk: " + channel);
        return channel;
    }
}
