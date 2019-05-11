package com.example.pluginlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Description:
 * Copyright  : Copyright (c) 2019
 * Company    :
 * Author     : 田高攀
 * Date       : 2019/5/11 21:04
 */
public interface IPlugin {
    //内部的插件
    int FROM_INTERNAL = 0;
    //外部插件
    int FROM_EXTERNAL = 1;

    /**
     * 生命周期是依赖于系统的，现在宿主相当于系统，需要管理下生命周期 .
     * 管理插件的生命周期 .
     * @param proxyActivity
     */

    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onStart();

    void onRestart();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
