package com.example.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Company    :
 * Author     : 田高攀
 * Date       : 2019/5/11 20:48
 */
public class PluginManager {

    private static final PluginManager instance = new PluginManager();

    public static PluginManager getInstance(){
        return instance;
    }

    private PluginManager(){

    }

    private Context mContext;
    private PluginApk mPluginApk;

    public void init(Context context){
        mContext = context.getApplicationContext();
    }

    public PluginApk getPluginApk(){
        return mPluginApk;
    }

    //加载Apk文件
    public void loadApk(String apkPath){
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath,
                PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if(packageInfo == null){
          return;
        }
        DexClassLoader classLoader = createDexClassLoader(apkPath);
        AssetManager am = createAssertManager(apkPath);
        Resources resources = createResources(am);
        mPluginApk = new PluginApk(packageInfo, resources, classLoader);
    }

    private Resources createResources(AssetManager am) {
        Resources res = mContext.getResources();
        return new Resources(am, res.getDisplayMetrics(), res.getConfiguration());
    }

    private AssetManager createAssertManager(String apkPath) {
        try {
            AssetManager am = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(am, apkPath);
            return am;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(), null, mContext.getClassLoader());
    }
}
