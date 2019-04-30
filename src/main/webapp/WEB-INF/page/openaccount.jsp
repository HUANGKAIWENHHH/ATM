<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <%@ page  pageEncoding="utf-8"%>

</head>

<body data-type="index">
    <div class="am-g tpl-g">
        <!-- 头部 -->
        <jsp:include page="common/head.jsp"></jsp:include>
        <!-- 风格切换 -->
        
        <!-- 侧边导航栏 -->
        <jsp:include page="common/left.jsp"></jsp:include>


        <!-- 内容区域 -->
        <div class="tpl-content-wrapper">

            <div class="row-content am-cf">
                <div class="row  am-cf">
                    
                    <div class="row">

                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">银行卡开户</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">
                                    <div class="am-form-group">
                                        <label for="password" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="password" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="confirmPassword" class="am-u-sm-3 am-form-label">确认密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="confirmPassword" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button type="button" id="open" value="开户" class="am-btn am-btn-primary tpl-btn-bg-color-success" onclick="openaccount()">开户</button>
                                        </div>
                                    </div>
                                </form>
                                <audio autoplay="autoplay" id="speech" src="" onended="javascript:window.location.href = '/user/toHome.do'"></audio>
                            </div>
                        </div>
                    </div>
                </div>

        

                    
                    
                </div>

               

 
                </div>



            </div>
        </div>
    </div>
    </div>

<script>
    $(document).ready(function () {
        $("ul li a").removeClass("active")
        $("#openAccount").addClass("active")

    });

    function openaccount() {
        $.ajax({
            url:"/card/openAccount.do",
            data:{
                //ID选择器，选择页面上边的password，并赋值进data，
                // .val()返回或设置被选元素的 value 属性
                password: $('#password').val(),
                confirmPassword: $('#confirmPassword').val()
            },
            dataType:"json",
            type:"POST",
            success:function (data) {
                if (data.code != 1000) {
                    alert(data.message)
                    return
                }
                // 对象用于获得当前页面的地址 (URL)，并把浏览器重定向到新的页面,windows可省略
                alert('开户成功')
                $("#speech").attr('src', '/speech?word=开户成功')
                // window.location.href = '/user/toHome.do'
            },
            error:function (e) {
                // 调试器中看传值情况
                console.log(e)
                alert('error')
            }
        });
    }

</script>


</body>

</html>