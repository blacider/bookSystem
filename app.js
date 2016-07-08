var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');
var routes = require('./routes/index');
var users = require('./routes/users');
//var timetable = require('./routes/timetable')

var app = express();

var ejs = require('ejs');


// view engine setup

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.engine('.html', ejs.__express);
app.set('view engine', 'html');


// app.set('views', path.join(__dirname, 'views'));
// app.set('view engine', 'jade');


// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


app.use(session({
  secret: 'secret',
  resave: true,
  saveUninitialized: false
}));


//登录拦截
app.use(function(req, res, next) {
  var error = "";
  if (req.session["error"]) {
    error = req.session["error"];
    req.session["error"] = "";
  }
  res.locals.url = req.originalUrl;
  res.locals["error"] = error;
  // var url = req.originalUrl;
  if (!req.session.hasOwnProperty("name")) {
    res.locals.isLogin = false;
    // if (url != "/login" && url != "/signup")
      // return res.redirect("/login");
  } else {
    // if (url == "/login" || url == "/signup") {
      // return res.redirect("/");
    // }
    res.locals.isLogin = true;
    res.locals.username = req.session.name;
  }
  next();
});


app.use('/', routes);
app.use('/users', users);
//app.use('/timetable', timetable)

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handlers

// development error handler
// will print stacktrace
if (app.get('env') === 'development') {
  app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
      message: err.message,
      error: err
    });
  });
}

// production error handler
// no stacktraces leaked to user
app.use(function(err, req, res, next) {
  res.status(err.status || 500);
  res.render('error', {
    message: err.message,
    error: {}
  });
});


module.exports = app;
