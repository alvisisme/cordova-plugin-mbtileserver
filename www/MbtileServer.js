var exec = require('cordova/exec');

const MBTILESERVER_CLASS = 'MbtileServer';
const START_FUNCTION = 'start';
const STOP_FUNCTION = 'stop';

exports.start = function (file, port, success, error) {
    let params = [];
    if (typeof file === 'string') {
      params.push(file);
    } else {
      if (typeof error === 'function') {
        error('Arguments Error')
        return
      } else {
        throw new Error('Arguments Error')
      }
    }
    if (typeof port === 'number') {
      params.push(port);
    }
    exec(success, error, MBTILESERVER_CLASS, START_FUNCTION, params);
};

exports.stop = function (success, error) {
    exec(success, error, MBTILESERVER_CLASS, STOP_FUNCTION, []);
};
