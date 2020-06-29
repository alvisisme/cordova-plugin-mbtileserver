# 常见问题

1. Android端Cesium无法正常显示瓦片，但是PC端可以。

    Android 6.0 (API level 23)之后对明文网络请求(如http)进行了限制，并在9.0(API level 28)版本默认禁止访问http明文请求，对未加密流量不再信任，直接放弃请求，因此http的url均无法在webview中加载，而https 不受影响。

    可以在`AndroidManifest.xml`配置文件中加入参数解除该限制。

    配置如下
    ```xml
    <manifest ...>
        <application
            ...
            android:usesCleartextTraffic="true"
            ...>
            ...
        </application>
    </manifest>
    ```

    cordova工程config.xml配置样例如下:
    ```xml
    <platform name="android">
        <edit-config file="AndroidManifest.xml" target="/manifest/application" mode="merge">
            <application android:usesCleartextTraffic="true"/>
        </edit-config>
    </platform>
    ```

    参考引用
    
    * [Android WebView 加载失败（net::ERR_CLEARTEXT_NOT_PERMITTED）](https://blog.csdn.net/geofferysun/article/details/88575504)
    * [usesCleartextTraffic](https://developer.android.com/guide/topics/manifest/application-element#usesCleartextTraffic)
