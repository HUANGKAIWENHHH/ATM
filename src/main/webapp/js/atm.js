var atm = {}

atm.ajax = function (url, parm, success, error) {

    $.ajax({
        url:url,
        type:"post",
        dataType:"json",
        data:parm,
        // success里的data是json对象
        success:function (data) {
            if (data.code != 1000) {
                alert(data.message)
                if (data.code == 5000) {
                    location.href = "/toLogin.do"
                }
                return
            }
            // 防御式编程，如果success为null 那就执行function，保证不会报错，最多就什么都不执行，success不为null就执行success
            var sf = success || function () {}
            sf(data)
        },
        error:function (e) {
            console.log(e)
            alert('error')

            var ef = error || function () {}
            ef()
        }
    });

};

atm.loadCard = function () {
    $.ajax({
        url: "/card/listMyCard.do",
        date: {},
        dataType: "json",
        type: "POST",
        success: function (data) {
            if (data.code != 1000) {
                alert(data.message)
                return;
            }
            var msg = '<option value="no">请选择银行卡</option>'
            var cards = data.data
            for (var i = 0; i < cards.length; i++) {
                msg += '<option value='  + cards[i].id +  '>  ' + cards[i].cardNumber + '  </option>'
            }

            $('#cards').html(msg)
        },
        error: function (e) {
            console.log(e)
            alert("error")
        }
    });
}


