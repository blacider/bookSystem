// dao/orderDao.js
// order抽象类


var Table = require('./table.js');

var orderTable = new Table("orders");

function Order(order) {
    this.orderTime = order.orderTime;
    this.showingId = order.showingId;
    this.seatId = order.seatId;
    this.userId = order.userId;
}
module.exports = Order;


Order.prototype.save = function(callback) {
    var order = {
        showingId : this.showingId,
        seatId : this.seatId,
        userId : this.userId,
        orderTime : this.orderTime,      
    };
    orderTable.insert(order, callback);
};

Order.queryOrderByUserId = function(userId, callback) {
    orderTable.connectquery(userId, callback);
};
