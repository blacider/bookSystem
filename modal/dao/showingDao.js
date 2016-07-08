// dao/theaterDao.js
// theater抽象类


var Table = require('./table.js');

var showingTable = new Table("showings");

function Showing(showing) {
    this.id = showing.id;
}
module.exports = Showing;


Showing.prototype.save = function(callback) {
    var showing = {
        showingId : this.id,
    };
    showingTable.insert(showing, callback);
};

Showing.queryAll = function(callback) {
    showingTable.queryAll(callback);
};

Showing.queryShowingByMovieIdAndTheaterId = function(movieId, theaterId, callback) {
    var oNewObj = {"movieId":Number(movieId), "theaterId":Number(theaterId)}
    showingTable.queryByAttrs(oNewObj, 'and', callback);
} 
