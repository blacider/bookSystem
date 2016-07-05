// dao/movieCatlogDao.js
// movieCatlog抽象类


var Table = require('./table.js');

var movieCatlogTable = new Table("moviecatalog");

function MovieCatlog(moviecatlog) {
    this.id = moviecatlog.id;
}
module.exports = MovieCatlog;


MovieCatlog.prototype.save = function(callback) {
    var moviecatlog = {
        moviecatlogId : this.id,
    };
    movieCatlogTable.insert(moviecatlog, callback);
};

MovieCatlog.queryAll = function(callback) {
    movieCatlogTable.queryAll(callback);
};

MovieCatlog.queryMovieCatlogByMovieId = function(id,callback) {
     movieCatlogTable.queryByAttr("movieId", id, callback);
} 
