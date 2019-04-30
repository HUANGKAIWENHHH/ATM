<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <%@ page  pageEncoding="utf-8"%>


</head>
<script src="/js/atm.js"></script>

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
                                <div class="widget-title am-fl">取款操作</div>
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
                                        <label for="password" class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" class="tpl-form-input" id="password" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <label for="amount" class="am-u-sm-3 am-form-label">金额 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="text" class="tpl-form-input" id="amount" placeholder="请输入取款金额">
                                            <small></small>
                                        </div>
                                    </div>

                                    <%--onended当声音播放完后重定向--%>
                                    <audio id="speech" src="" autoplay="autoplay" onended="javascript:window.location.href = '/user/toHome.do'"></audio>

                                    <div class="am-form-group">
                                        <div class="am-u-sm-9 am-u-sm-push-3">
                                            <button onclick="draw()" type="button" class="am-btn am-btn-primary tpl-btn-bg-color-success ">取款</button>
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

                        <div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">
      <a href="javascript: void(0)" class="am-close am-close-spin" data-am-modal-close>&times;</a>
    </div>
    <div class="am-modal-bd">
      操作成功
    </div>
  </div>
</div>

    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/amazeui.datatables.min.js"></script>
    <script src="/assets/js/dataTables.responsive.min.js"></script>
    <script src="/assets/js/app.js"></script>

    <script>
        // $:jQuery对象名，document：当前页面文档
        // 当前页面文档加载全部加载完毕后调用function
        $(document).ready(function() {
            // atm.speech()
            atm.loadCard()
            $("ul li a").removeClass("active")
            $("#withrawal").addClass("active")
        });

        function draw() {
            $.ajax({
                url:"/card/withdrawal.do",
                data:{
                    //ID选择器，选择页面上边的password，并赋值进data，
                    // .val()返回或设置被选元素的 value 属性
                    cardId: $('#cards').val(),
                    password: $('#password').val(),
                    amount:$('#amount').val()
                },
                dataType:"json",
                type:"POST",
                success:function (data) {
                    if (data.code != 1000) {
                        alert(data.message)
                        return;
                    }
                    alert('取款成功')
                    $("#speech").attr('src', '/speech?word=取款成功')
                    // 对象用于获得当前页面的地址 (URL)，并把浏览器重定向到新的页面,windows可省略
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