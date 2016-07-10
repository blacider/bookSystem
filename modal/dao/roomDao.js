// dao/roomDao.js
// room抽象类


var Table = require('./table.js');

var roomTable = new Table("rooms");

function Room(room) {
    this.id = room.roomId;
    this.name = room.roomName;
}
module.exports = Room;


Room.prototype.save = function(callback) {
    var room = {
        roomName : this.name,
        roomId : this.id
    };
    roomTable.insert(room, callback);
};

Room.queryAll = function(callback) {
    roomTable.queryAll(callback);
};

Room.queryRoomByRoomId = function(id,callback) {
     roomTable.queryByAttr("roomId", id, callback);
} 