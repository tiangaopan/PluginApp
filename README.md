# PluginApp
插件化研究


 1.跳转的插件类需要继承PluginActivity
 
 
 
 2.需要将插件apk拷贝到主工程的目录下并进行加载
 
 
 
  String apkPath = Util.copyData(MainActivity.this, "testapp.apk");
	
	
  PluginManager.getInstance().loadApk(apkPath);
	
3.跳转时跳转到代理类中ProxyActivity，将真正要跳转的插件类全名称写入className


Intent intent = new Intent();


intent.setClass(MainActivity.this, ProxyActivity.class);


intent.putExtra("className","com.example.testapp.MainActivity");


startActivity(intent);
