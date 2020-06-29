# 使用说明

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
