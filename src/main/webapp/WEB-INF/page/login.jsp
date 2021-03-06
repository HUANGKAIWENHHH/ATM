<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <%@ page  pageEncoding="utf-8"%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Amaze UI Admin index Examples</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="/assets/css/app.css">
    <script src="/assets/js/jquery.min.js"></script>
    <script src="/js/atm.js"></script>

</head>
<body data-type="login">
    <script src="/assets/js/theme.js"></script>
    <div class="am-g tpl-g">
        <!-- 风格切换 -->
        <div class="tpl-skiner">
            <div class="tpl-skiner-toggle am-icon-cog">
            </div>
            <div class="tpl-skiner-content">
                <div class="tpl-skiner-content-title">
                    选择主题
                </div>
                <div class="tpl-skiner-content-bar">
                    <span class="skiner-color skiner-white" data-color="theme-white"></span>
                    <span class="skiner-color skiner-black" data-color="theme-black"></span>
                </div>
            </div>
        </div>
        <div class="tpl-login">
            <div class="tpl-login-content">
                <div class="tpl-login-logo">

                </div>



                <form class="am-form tpl-form-line-form">
                    <div class="am-form-group">
                        <input type="text" class="tpl-form-input" id="username" placeholder="请输入账号">

                    </div>

                    <div class="am-form-group">
                        <input type="password" class="tpl-form-input" id="password" placeholder="请输入密码">

                    </div>
                  


                    <div class="am-form-group">

                        <button onclick="userLogin()" type="button" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录</button>
                        <button onclick="javascript:location.href = '/toRegister.do'" id="register" type="button" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">注册</button>
                        <button onclick="getWxLogin()" id="WxLogin" type="button" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">微信登录</button>

                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/app.js"></script>
    <script>

        function getWxLogin() {
            atm.ajax("/wx/getQrconnectUrl",
                {},
                function (data) {
                    location.href=data.data
                }
            )
        }

        function userLogin() {
            $.ajax({
                url:"/login.do",
                type:"POST",
                dataType:"json",
                data:{
                    username:$('#username').val(),
                    password:$('#password').val()
                },
                success:function(data) {
                    if (data.code != 1000) {
                        alert(data.message)
                        return
                    }
                    //window可省略
                    location.href = '/user/toHome.do'
                },
                error:function (e) {
                    console.log(e)
                    alert('error')
                }
            })
        }

    </script>
</body>

</html>