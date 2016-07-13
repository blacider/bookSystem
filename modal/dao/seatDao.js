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

Seat.queryByCorXandCorY = function(showingId, CorX, CorY, callback) {
    var oNewObj = {"showingId": Number(showingId), "SeatRow":CorX, "SeatCol":CorY}
    seatTable.queryByAttrs(oNewObj, 'and', callback);
}

Seat.updateIsBought = function(seatId, callback) {
    seatTable.update("seatIsBought", 1, "seatId", seatId, callback);
}


Seat.queryCannotBuySeatByShowingId = function(id,callback) {
     var oNewObj = {"showingId":Number(id), "seatIsBought":1}
     seatTable.queryByAttrs(oNewObj, 'and', callback);
}
