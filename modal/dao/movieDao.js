// dao/movieDao.js
// movie抽象类


var Table = require('./table.js');

var movieTable = new Table("movies");

function Movie(movie) {
    this.name = movie.name;
}
module.exports = Movie;


Movie.prototype.save = function(callback) {
    var movie = {
        movieName : this.name,
    };
    movieTable.insert(movie, callback);
};

Movie.queryAll = function(callback) {
    movieTable.queryAll(callback);
};

Movie.queryUserNumById = function(id,callback) {
     movieTable.queryByAttr("movieId", id, callback);
} 