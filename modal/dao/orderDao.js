// dao/orderDao.js
// order抽象类


var Table = require('./table.js');

var orderTable = new Table("orders");

function Order(order) {
    this.orderId = order.orderId;
    this.showingId = order.showingId;
    this.seatId = order.seatId;
    this.userId = order.userId;
}
module.exports = Order;


Order.prototype.save = function(callback) {
    var order = {
        orderId = order.orderId,
        showingId = order.showingId,
        seatId = order.seatId,
        userId = order.userId
    };
    orderTable.insert(order, callback);
};

Order.queryOrderByUserId = function(userId, callback) {
    orderTable.queryByAttr("userId", userId, callback);
};
User.queryUserNumByName = function(name, callback) {
    userTable.queryNumByAttr("userName", name, callback);
};
