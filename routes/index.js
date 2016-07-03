var express = require('express');
var router = express.Router();
var movieDao = require("../modal/dao/movieDao.js");
var logger = require("../util/logger.js");
/* GET home page. */
router.get('/', function(req, res, next) {
  movies = [];
  movieDao.queryAll(function(err, result) {
      for (var i = 0; i < result.length;i++) {
        newMovie = {
          movieName:result[i]['movieName'],
          movieId:result[i]['movieId'],
          moviePictureUrl:result[i]['moviePictureUrl']
        }
        movies.push(newMovie);
      }
      res.render('index', {
        title: 'Express',
        movies: movies 
      });
    });
});

router.get('/book', function(req, res, next) {
  console.log(req.query.id);
  movieDao.queryUserNumById(req.query.id, function(err, result){
    movie = {}
    for (var i in result[0]) {
      eval("movie."+i+"=result[0][i]");
    }
    res.render('book', { 
      title: 'Express',
      movie: movie });
  });
});

module.exports = router;