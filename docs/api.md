# 接口规范

## start

启动mbtiles解析服务。

```js
cordova.plugins.MbtileServer.start(filePath, port, success, fail)
```

|参数|类型|可选|描述|
|--|--|--|--|
|filePath|String|否|mbtiles文件的绝对路径|
|port|Number|是|服务开启端口，默认8080|
|success|Function|是|开启成功回调函数|
|fail|Function|是|开启失败回调函数|

样例代码

```js
function success() {
  console.log('success')
}
function fail(error) {
  console.log(error)
}
const filePath = '/sdcard/test.mbtiles'
cordova.plugins.MbtileServer.start(filePath, 8080, success, fail)
```

## stop

关闭mbtiles解析服务。

```js
cordova.plugins.MbtileServer.stop(success, fail)
```

|参数|类型|可选|描述|
|--|--|--|--|
|success|Function|是|关闭成功回调函数|
|fail|Function|是|关闭失败回调函数|

样例代码

```js
function success() {
  console.log('success')
}
function fail(error) {
  console.log(error)
}
cordova.plugins.MbtileServer.stop(success, fail)
```

