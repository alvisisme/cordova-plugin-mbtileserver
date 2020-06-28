# Cordova Mbtiles Server Plugin

Mbtiles服务插件，通过web服务接口访问mbtiles地图数据。

## 如何使用

1. 创建一个cordova测试工程

    ```bash
    cordova create mapapp --template git+https://github.com/alvisisme/cordova-template-cesium.git
    cd mapapp
    yarn
    yarn build
    cordova platform add android
    ```

2. 安装插件

    通过git仓库方式安装

    ```bash
    cordova plugin add git+https://github.com/alvisisme/cordova-plugin-mbtileserver.git
    ```

    通过本地文件方式安装

    ```bash
    git clone https://github.com/alvisisme/cordova-plugin-mbtileserver.git
    cordova plugin add ./cordova-plugin-mbtileserver
    ```                                                                                                                

3. 接口测试

    使用cesium接口测试服务是否能正常调用

    TODO

4. 安装支持平台

    ```bash
    cordova platform add android
    ```

5. 构建应用

    ```bash
    cordova build
    ```    

6. 运行应用

    ```bash
    cordova run
    ```

## 本工程搭建过程记录

1. 安装plugman
    ```bash
    $npm install -g plugman
    $plugman -v platform
    3.0.1
    ```

1. 创建插件
    ```bash
    plugman create --name MbtileServer --plugin_id com.plugin.mbtileserver --plugin_version 0.1.0
    ```

1. 添加插件平台
    ```bash
    mv MbtileServer cordova-plugin-mbtileserver
    cd cordova-plugin-mbtileserver
    plugman platform add --platform_name android
    ```

1. 生成package.json
    ```bash
    plugman createpackagejson .
    ```


## 参考引用

* [Cordova Plugin Doc](https://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)
* [cordova-plugin-webserver](https://github.com/bykof/cordova-plugin-webserver)
