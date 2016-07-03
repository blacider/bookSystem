// dao/caseDao.js
// 实现与MySQL交互
// var mysql = require('mysql');
// var $conf = require('../conf/db');
// var $util = require('../util/util');
// var $sql = {
//     save:"INSERT INTO users(userName,password) VALUES(?,?)",
//     queryUserByName: 'SELECT * FROM users WHERE userName = ?',
//     queryUserNumByName: "SELECT count(*) FROM users WHERE userName = ?"
// };

// // 使用连接池，提升性能
// var pool  = mysql.createPool($util.extend({}, $conf.mysql));

var Table = require('./table.js');

var userTable = new Table("users");

function User(user) {
    this.name = user.name;
    this.pass = user.pass;
}
module.exports = User;


User.prototype.save = function(callback) {
    var user = {
        userName : this.name,
        password : this.pass
    };
    userTable.insert(user, callback);
};

User.queryUserByName = function(name, callback) {
    userTable.queryByAttr("userName", name, callback);
};
User.queryUserNumByName = function(name, callback) {
    userTable.queryNumByAttr("userName", name, callback);
};
