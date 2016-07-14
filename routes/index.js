var express = require('express');
var router = express.Router();
var movieDao = require("../modal/dao/movieDao.js");
var theaterDao = require("../modal/dao/theaterDao.js");
var movieCatlogDao = require("../modal/dao/movieCatlogDao.js");
var showingDao = require("../modal/dao/showingDao.js");
var roomDao = require("../modal/dao/roomDao.js");
var seatDao = require("../modal/dao/seatDao.js");
var orderDao = require("../modal/dao/orderDao.js");
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
// 默认使用views文件夹
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
      // express记住了要解析的扩展名为html
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
    var finish = 0;
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

router.get('/test', function(req, res, next) {
  seatDao.queryCannotBuySeatByShowingId(1, function(error, result) {
  sSeatStringList=[];
  for (var i in result) {
    var sTempString = "";
    sTempString += result[i]['seatRow'].toString();
    sTempString += "_";
    sTempString += result[i]['seatCol'].toString();
    sSeatStringList.push(sTempString);
  }
  console.log(sSeatStringList);
  });
});

// 用户订单管理
router.get('/myorders', function(req, res, next) {
  console.log(req.query);
  console.log(req.session);
  var Orders = []
  orderDao.queryOrderByUserId(Number(req.session.userId), function(error, result) {
    for (i in result) {
      newOrders = {
        orderTime : result[i]["orderTime"].toLocaleString(),
        movieName : result[i]["movieName"],
        theaterName : result[i]["theaterName"],
        showingTime : result[i]["showingTime"].toLocaleString(),
        roomName : result[i]["roomName"],
        seatRow : result[i]["seatRow"],
        seatCol : result[i]["seatCol"],
        seatNum : result[i]["seatCol"].toString() + "_" +result[i]["seatRow"].toString(),
        showingPrice : result[i]["showingPrice"]
      };
      Orders.push(newOrders);
    }
    console.log(Orders)
    res.render('myorders', {
      title: 'My Orders',
      Orders: Orders
    });
  });
});


router.post('/chooseSeat', function(req, res, next) {
    var data={
      showingPrice: Number(req.body.showingPrice),
      roomId: Number(req.body.roomId),
      showingTime: req.body.showingTime,
      movieId: Number(req.body.movieId),
      showingId: Number(req.body.showingId),
      movieName: req.body.movieName
    }
    roomDao.queryRoomByRoomId(data.roomId, function(error, result) {
       mapString = result[0]['roomMap'];
       roomCol = result[0]['roomCol'];
       var roomRow = mapString.length/roomCol;
       var i = 0;
       map = [];
       while (i < roomRow) {
         var subString = mapString.substring(i*roomCol, (i+1)*roomCol);
         map.push(subString);
         i++;
       }
       console.log(map)
       showingData = {
         showingPrice:data.showingPrice,
         movieId:data.movieId,
         movieName:data.movieName,
         map:map,
         showingTime:data.showingTime,
         showingId:data.showingId
       }
       console.log(data.showingId);
       seatDao.queryCannotBuySeatByShowingId(data.showingId, function(error, result) {
         sSeatStringList=[];
         for (var i in result) {
           var sTempString = "";
           sTempString += result[i]['seatRow'].toString();
           sTempString += "_";
           sTempString += result[i]['seatCol'].toString();
           sSeatStringList.push(sTempString);
         }
         console.log(sSeatStringList);
         res.render('seats', {
           title:"Express",
           showingData: showingData,
           sSeatStringList:sSeatStringList
         });
       });
      });
    });

router.get('/getTimeTable', function(req, res, next) {
    //logger.log("/test" + JSON.stringify(req.body));
    var DayMap = ["周日","周一","周二","周三","周四","周五","周六"]
    showingDao.queryShowingByMovieIdAndTheaterId(req.query.movieId,req.query.theaterId,function(err, result) {
      var data = {}
      for (var i = 0; i < result.length;i++) {
        var myDate = result[i]['showingTime'];
        var sString = "";
        sString += myDate.getMonth();
        sString += ".";
        sString += myDate.getDate();
        sString += "(";
        sString += DayMap[myDate.getDay()];
        sString += ")";
        console.log(sString);
        newShowing = {
          showingTime: myDate,
          showingType: result[i]['showingType'],
          showingPrice: result[i]['showingPrice'],
          showingId: result[i]['showingId'],
          movieId:req.query.movieId,
          roomId: result[i]['RoomId']
        }
        if (data[sString] == undefined) {
          data[sString] = [];
          data[sString].push(newShowing);
        } else {
          data[sString].push(newShowing);
        }
      }
      logger.log(JSON.stringify(data));
      res.render('bookTable', {
        tableData: data
      });
    });
});

router.post('/doBooking', function(req, res, next) {
  seatsCor = req.body.seats;
  showingId = req.body.showingId;
  userId = req.session.userId;
  seatsList = seatsCor.split(',');
  var index = 0
  for (var i in seatsList) {
    var seatCor = seatsList[i].split('_');
    var corX = Number(seatCor[0]);
    var corY = Number(seatCor[1]);
    seatDao.queryByCorXandCorY(showingId, corX, corY,function(error, result) {
      seatDao.updateIsBought(result[0]["seatId"], function(error, result_) {
        var orderDate = new Date();
        orderDateString = orderDate.toLocaleDateString() + " " + orderDate.getHours() + ":" + orderDate.getMinutes() + ":" + orderDate.getSeconds();
        console.log(orderDateString);
        var order = new orderDao({
          showingId : Number(showingId),
          seatId : Number(result[0]["seatId"]),
          userId : Number(req.session.userId),
          orderTime : orderDateString
        });
        order.save(function(err, result) {
          index += 1;
          console.log(index)
          if (index >= seatsList.length) {
            res.redirect('/myorders');
          }
        });
      });
    });
  }
});

module.exports = router;
