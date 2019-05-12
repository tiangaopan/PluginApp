package com.example.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

/**
 * Description: 代理，管理插件activity的生命周期 .
 * Copyright  : Copyright (c) 2019
 * Company    :
 * Author     : 田高攀
 * Date       : 2019/5/11 21:35
 */
public class ProxyActivity extends Activity {
    //插件化类的完整名称 .
    private String    mClassName;
    private PluginApk mPluginApk;
    private IPlugin   mIplugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {
        if (mPluginApk == null) {
            throw new RuntimeException("Loading your apk file first please");
        }
        try {
            Class<?> clazz = mPluginApk.mClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                mIplugin = (IPlugin) object;
                mIplugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                mIplugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIplugin != null){
            mIplugin.onResume();
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        if(mIplugin != null){
            mIplugin.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(mIplugin != null){
            mIplugin.onRestart();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mIplugin != null){
            mIplugin.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mIplugin != null){
            mIplugin.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIplugin != null){
            mIplugin.onDestroy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mIplugin != null){
            mIplugin.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public Resources getResources() {
        return mPluginApk != null ? mPluginApk.mResources : super.getResources();
    }

    @Override
    public AssetManager getAssets() {
        return mPluginApk != null ? mPluginApk.mAssetManager : super.getAssets();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.mClassLoader : super.getClassLoader();
    }
}
