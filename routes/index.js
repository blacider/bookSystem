var express = require('express');
var router = express.Router();
var movieDao = require("../modal/dao/movieDao.js");
var theaterDao = require("../modal/dao/theaterDao.js");
var movieCatlogDao = require("../modal/dao/movieCatlogDao.js")
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
  movie = {}
  theaters = []
  movieFinish = false
  theatersFinish = false
  movieDao.queryUserNumById(req.query.id, function(err, result){
    for (var i in result[0]) {
      eval("movie."+i+"=result[0][i]");
    }
    movieFinish = true
    if (theatersFinish && movieFinish) {
      res.render('book', { 
        title: 'Express',
        movie: movie,
        theaters: theaters });
    }
  });

  movieCatlogDao.queryMovieCatlogByMovieId(req.query.id, function(err, result){
    theaterIds = []
    for (var i in result) {
      console.log(result[i]);
      theaterIds.push(result[i]["theaterId"])
    }
    finish = 0
    for (var i = 0; i < theaterIds.length; i++) {
      theaterDao.queryTheaterById(theaterIds[i], function(err, result) {
        finish = finish+1;
        theater = {}
        for (var i in result[0]) {
          eval("theater."+i+"=result[0][i]");
        }
        theaters.push(theater)
        if (finish == theaterIds.length) {
          theatersFinish = true
        }
        if (theatersFinish && movieFinish) {
          console.log(theaters)
          res.render('book', { 
            title: 'Express',
            movie: movie,
            theaters:theaters});
        }
      });
    }
  });
});


module.exports = router;
