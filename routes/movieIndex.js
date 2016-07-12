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
      res.render('myorders', {
        title: 'Express',
        movies: movies 
      });
    });
});

router.get('/book', function(req, res, next) {
  res.render('book', { title: 'Express' });
});

router.get('/test', function(req, res, next) {
    //logger.log("/test" + JSON.stringify(req.body));
    movieDao.queryAll(function(err, result) {
      for (var i = 0; i < result.length;i++) {
        console.log(result[i]['movieId']);
        console.log(result[i]['movieName'])
      }
    })
});

module.exports = router;
