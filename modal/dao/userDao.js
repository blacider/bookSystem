// dao/userDao.js
// user抽象类


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
