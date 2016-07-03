// dao/table.js
// 实现与 mysql 交互的 table 类

var mysql = require('mysql');
var $conf = require('../conf/db');
var $util = require('../util/util');
var logger = require('../../util/logger.js');

// 使用连接池，提升性能
var pool  = mysql.createPool($util.extend({}, $conf.mysql));


function Table(name) {
    this.name = name;
}

module.exports = Table;

var query = function(sqlText, callback) {
    pool.getConnection(function(err, connection) {
        connection.query(sqlText, function(err, result) {
            connection.release();
            logger.log("sql:" + sqlText + "\nresults: " + JSON.stringify(result) + "\nerror: " + JSON.stringify(err));
            callback(err, result);
        });
    });
}

Table.prototype.queryAll = function(callback) {
    var sqlText = "SELECT * FROM " + this.name;
    query(sqlText, callback);
};

Table.prototype.queryNumByAttr = function(attr, value, callback) {
    var sqlText = "SELECT count(*) FROM " + this.name + " WHERE " + attr + " = ";
    sqlText += typeof value == "string" ? ("'"+ value +"'") : (value);
    query(sqlText, callback);
};

Table.prototype.queryByAttr = function(attr, value, callback) {
    var sqlText = "SELECT * FROM " + this.name + " WHERE " + attr + " = ";
    sqlText += typeof value == "string" ? ("'"+ value +"'") : (value);
    query(sqlText, callback);
};

Table.prototype.insert = function(obj, callback) {
    var attrs = [], values = [];
    for (item in obj) {
        attrs.push(item);
        values.push((typeof obj[item] == "string")?("'"+obj[item]+"'"):(obj[item]));
    }
    var sqlText = "INSERT INTO " + this.name + "(" + attrs.join(",") + ") VALUES("+ values.join(",") + ")";
    query(sqlText, callback);
};

Table.prototype.delete = function(callback) {

};

Table.prototype.update = function() {

};