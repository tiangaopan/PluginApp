package com.example.testapp;

import android.os.Bundle;

import com.example.pluginlib.PluginActivity;

/**
 * Description:
 * Copyright  : Copyright (c) 2018
 * Company    :
 * Author     : 田高攀
 * Date       : 2019/5/12 11:22
 */
public class TestActivity extends PluginActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.other_tv);
    }
}