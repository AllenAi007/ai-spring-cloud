function setCookie(cookieName, cookieValue, expireDays) {
    var date = new Date();
    date.setTime(date.getTime() + (expireDays * 24 * 60 * 60 * 1000));
    var expires = 'expires=' + date.toUTCString();
    document.cookie = cookieName + '=' + cookieValue + ';' + expires + ';path=/';
}

function getCookie(cookieName) {
    var name = cookieName + '=';
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return '';
}

$(document).ready(function () {
    $('.button-collapse').sideNav({
            menuWidth: 300, // Default is 300
            edge: 'left', // Choose the horizontal origin
            closeOnClick: true, // Closes side-nav on <a> clicks, useful for Angular/Meteor
            draggable: true, // Choose whether you can drag to open on touch screens,
            onOpen: function (el) { /* Do Stuff*/
            }, // A function to be called when sideNav is opened
            onClose: function (el) { /* Do Stuff*/
            }, // A function to be called when sideNav is closed
        }
    );
    $('.parallax').parallax();


    $('#loginForm').submit(function (e) {
        console.log('submit form');
        e.preventDefault();
        console.log($('#loginForm').serialize());
        $.post('/sso/doLogin',
            {userId: $('#userId').val(), password: $('#password').val()},
            function (data) {
                console.log('success, data: ' + data);
                setCookie("Authorization", data, 1);
            })
            .fail(function (data) {
                console.log('fail, data: ' + data.responseText);
            })
    });
});
