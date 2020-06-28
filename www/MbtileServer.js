var exec = require('cordova/exec');

const MBTILESERVER_CLASS = 'MbtileServer';
const START_FUNCTION = 'start';
const STOP_FUNCTION = 'stop';

exports.start = function (file, port, success, error) {
    let params = [];
    if (file) {
      params.push(file);
    }
    if (port) {
      params.push(port);
    }
    exec(success, error, MBTILESERVER_CLASS, START_FUNCTION, params);
};

exports.stop = function (success, error) {
    exec(success, error, MBTILESERVER_CLASS, STOP_FUNCTION, []);
};
