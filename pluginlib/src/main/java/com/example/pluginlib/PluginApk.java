package com.example.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Company    :
 * Author     : 田高攀
 * Date       : 2019/5/11 20:43
 */
public class PluginApk {

    //插件apk的实体对象
    public PackageInfo mPackageInfo;
    //加载插件的资源文件
    public Resources mResources;
    public AssetManager mAssetManager;
    //实际加载apk需要使用classloader去load .
    public DexClassLoader mClassLoader;

    public PluginApk(PackageInfo mPackageInfo, Resources mResources, DexClassLoader mClassLoader) {
        this.mPackageInfo = mPackageInfo;
        this.mResources = mResources;
        this.mClassLoader = mClassLoader;
        mAssetManager= mResources.getAssets();
    }
}
