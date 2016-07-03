var express = require('express');
var router = express.Router();
var userDao = require("../modal/dao/userDao.js");
var logger = require("../util/logger.js");
/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});


router.get('/logout', function(req, res, next) {
    req.session.destroy();
    res.locals.isLogin = false;
    res.redirect("/");
});

router.post('/login', function(req, res, next) {
    logger.log("/login" + JSON.stringify(req.body));
    userDao.queryUserNumByName(req.body.name, function(err, result) {
        logger.log("/signup:" + JSON.stringify(result));
        if (result[0]["count(*)"] == 0) {
            req.session.error = "请输入正确用户名";
            res.redirect(req.body.url);
        } else {
            userDao.queryUserByName(req.body.name, function(err, result) {
                if (result.pass != req.body.pass) {
                    req.session.error = "密码不正确";
                    res.redirect("/login");
                } else {
                    req.session.name = req.body.name;
                    res.redirect(req.body.url);
                }
            });
        }
    });
});

router.post('/signup', function(req, res, next) {
    var user = new userDao({
        name:req.body.name,
        pass:req.body.password
    });
    userDao.queryUserNumByName(req.body.name, function(err, result) {
        logger.log("/signup:" + JSON.stringify(result));
        if (result[0]['count(*)'] != 0) {
            req.session.error = "用户名已存在,请重新注册";
            res.redirect(req.body.url);
        } else {
            user.save(function(err, result) {
                logger.log("/signup:" + JSON.stringify(result));
                req.session.error = "注册成功,请登录";
                res.redirect(req.body.url);
            });
        }
    });
});

module.exports = router;
