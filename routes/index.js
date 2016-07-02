var express = require('express');
var router = express.Router();
var logger = require("../util/logger.js");
/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/book', function(req, res, next) {
  res.render('book', { title: 'Express' });
});


module.exports = router;
