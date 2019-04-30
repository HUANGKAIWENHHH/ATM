<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <%@ page  pageEncoding="utf-8"%>

</head>

<body data-type="index">
    <%--<script src="assets/js/theme.js"></script>--%>
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
                                <div class="widget-title am-fl">转账操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form class="am-form tpl-form-line-form">


                                <div class="am-form-group">
                                        <label for="cards" class="am-u-sm-3 am-form-label">银行卡 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <select id="cards" data-am-selected="{searchBox: 1}" style="display: none;">

                                            </select>

                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="cardInNum" class="am-u-sm-3 am-form-label">转入卡号 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="cardInNum" placeholder="请输入收款卡号">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="password" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="password" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="amount" class="am-u-sm-3 am-form-label">金额 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="amount" placeholder="请输入转账金额">
                                            <small></small>
                                        </div>
                                    </div>

                                    <audio id="speech" src="" autoplay="autoplay" onended="javascript:window.location.href='/user/toHome.do'"></audio>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button onclick="transfer()" type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success ">转账</button>
                                        </div>
                                    </div>
                                </form>
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
    // $:jQuery对象名，document：当前页面文档
    // 当前页面文档加载全部加载完毕后调用function
    $(document).ready(function() {
        atm.loadCard()
        $("ul li a").removeClass("active")
        $("#transfer").addClass("active")
    });

    function transfer(){
        $.ajax({
            url: "/card/transfer.do",
            data:{
                cardOutId: $('#cards').val(),
                password: $('#password').val(),
                amount: $('#amount').val(),
                cardInNum: $('#cardInNum').val()
            },
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.code != 1000) {
                    alert(data.message)
                    return
                }
                alert('转账成功')
                $("#speech").attr('src', '/speech?word=转账成功')
                // window.location.href = '/user/toHome.do'
            },
            error: function (e) {
                console.log(e)
                alert('error')
            }
        });

    }
</script>

</body>

</html>