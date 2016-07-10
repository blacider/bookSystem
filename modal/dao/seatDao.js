// dao/seatDao.js
// seat抽象类


var Table = require('./table.js');

var seatTable = new Table("seats");

function Seat(seat) {
    this.id = seat.seatId;
}
module.exports = Seat;


Seat.prototype.save = function(callback) {
    var seat = {
        seatId : this.id
    };
    seatTable.insert(seat, callback);
};

Seat.queryAll = function(callback) {
    seatTable.queryAll(callback);
};

Seat.queryCannotBuySeatByShowingId = function(id,callback) {
     var oNewObj = {"showingId":Number(id), "seatIsBought":1}
     seatTable.queryByAttrs(oNewObj, 'and', callback);
}

Seat.updateSeatBySeatIsBought