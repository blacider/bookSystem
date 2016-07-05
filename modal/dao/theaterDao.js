// dao/theaterDao.js
// theater抽象类


var Table = require('./table.js');

var theaterTable = new Table("theaters");

function Theater(theater) {
    this.name = theater.name;
}
module.exports = Theater;


Theater.prototype.save = function(callback) {
    var theater = {
        theaterName : this.name,
    };
    theaterTable.insert(theater, callback);
};

Theater.queryAll = function(callback) {
    theaterTable.queryAll(callback);
};

Theater.queryTheaterById = function(id,callback) {
     theaterTable.queryByAttr("theaterId", id, callback);
} 
