var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var routes = require('./routes/index');
var mysql = require('mysql');

var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'ex_db'
});
var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'public'));
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', routes);

connection.connect(function (err) {
    if (!err) {
        console.log("Database is connected ... \n\n");
    } else {
        console.log("Error connecting database ... \n\n" + err);
    }
});

//login
app.post('/member', function (req, res) {
    connection.query('SELECT member.name,member.pass FROM member where member.name = "' + req.body.username + '" and member.pass = "' + req.body.password + '";', function (err, rows, fields) {
        if (!err) {
            console.log('The solution is: ', rows);
            if (rows.length == 1 && rows[0].name == req.body.username && rows[0].pass == req.body.password) {
                res.send("OK");
            } else {
                res.send("Wrong credentials");
            }
        } else {
            console.log('Error while performing Query.');
            res.send("Error: SQLException, try later...");
        }
    });
});

//registration
app.post('/members/add', function (req, res) {
    var status = true;
    var n = 0;
    console.log("init - 0");
    connection.query('SELECT member.id, member.name FROM member;', function (err, rows, fields) {
        if (!err) {
            console.log("select all - 1");
            for (var i = 0; i < rows.length; i++) {
                if (rows[i].name == req.body.username) {
                    res.send("User is alredy exist");
                    status = false;
                }
            }
            console.log(status + " - 2");
            n = rows[rows.length - 1].id;
            if (status) {
                n++;
                console.log("hmmm... - 3");
                connection.query('INSERT INTO `ex_db`.`member` (`id`, `name`, `pass`) VALUES ("' + n + '","' + req.body.username + '","' + req.body.password + '");', function (err, rows, fields) {
                    if (!err) {
                        console.log("ok - 4");
                        res.send("OK");
                    } else {
                        console.log('Error while performing Query.');
                        res.send("SQL error, try later...");
                    }
                });
            }
        } else {
            console.log('Error while performing Query.');
            res.send("SQL error, try later...");
        }
    });
});

//fill the table
app.post('/users/all', function (req, res) {
    connection.query('SELECT user.id,user.name FROM user;', function (err, rows, fields) {
        if (!err) {
            var str = "";
            for (var i = 0; i < rows.length; i++) {
                str += rows[i].id + ":" + rows[i].name + ";";
            }
            res.send(str);
        } else
            console.log('Error while performing Query.');
    });
});

//get user by id
app.post('/user', function (req, res) {
    connection.query('SELECT user.id,user.name FROM user where user.id = "' + req.body.id + '";', function (err, rows, fields) {
        if (!err) {
            if (rows[0].id == req.body.id) {
                res.send(rows[0].id + ":" + rows[0].name);
            }
        } else
            console.log('Error while performing Query.');
    });
});

//add user
app.post('/users/add', function (req, res) {
    connection.query('INSERT INTO `ex_db`.`user` (`id`,`name`) VALUES ("' + req.body.id + '","' + req.body.username + '");', function (err, rows, fields) {
        if (!err) {
            res.send("OK");
        } else {
            console.log('Error while performing Query.');
            res.send("Can't add user...");
        }
    });
});

//update user
app.post('/users/update', function (req, res) {
    connection.query('UPDATE `ex_db`.`user` SET `name`= "' + req.body.username + '" WHERE `id`="' + req.body.id + '";', function (err, rows, fields) {
        if (!err) {
            res.send("OK");
        } else {
            console.log('Error while performing Query.');
            res.send("Can't save user...");
        }
    });
});

//delete user
app.post('/users/delete', function (req, res) {
    connection.query('DELETE FROM `ex_db`.`user` WHERE `id`="' + req.body.id + '";', function (err, rows, fields) {
        if (!err) {
            res.send("OK");
        } else {
            console.log('Error while performing Query.');
            res.send("Can't delete user...");
        }
    });
});

// error handlers


module.exports = app;
