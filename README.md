# Cordova Mbtiles Server Plugin

Mbtiles服务插件，通过web服务接口访问mbtiles地图数据。

## 快速开始

cordova工程安装本插件后, 调用`start`接口，传入mbtiles文件绝对路径与服务端口

```js
cordova.plugins.MbtileServer.start(filePath, port, success, fail)
```

成功后即可通过`http://localhost:port/z/x/y.png`接口访问瓦片地图数据。

注意配置文件读写和网络权限。

## 如何使用

见 [使用说明](docs/usage.md)

## 插件接口

见 [接口规范](docs/api.md)

## 常见问题

见[常见问题](docs/faq.md)

## 本工程搭建过程记录

1. 安装plugman
    ```bash
    $npm install -g plugman
    $plugman -v platform
    3.0.1
    ```

1. 创建插件
    ```bash
    plugman create --name MbtileServer --plugin_id cordova-plugin-mbtileserver --plugin_version 0.1.0
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

1. 修改添加必要的参数

## 参考引用

* [mbtiles-server](https://github.com/tobinbradley/mbtiles-server)
* [cordova plugin doc](https://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)
* [cordova-plugin-webserver](https://github.com/bykof/cordova-plugin-webserver)
