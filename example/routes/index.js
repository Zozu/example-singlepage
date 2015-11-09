var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
    renderIndex(req, res, next);
});

router.get('/login', function (req, res, next) {
    renderIndex(req, res, next);
});

router.get('/registration', function (req, res, next) {
    renderIndex(req, res, next);
});

router.get('/table', function (req, res, next) {
    renderIndex(req, res, next);
});

function renderIndex(req, res, next) {
    res.render('index.html');
}

module.exports = router;
