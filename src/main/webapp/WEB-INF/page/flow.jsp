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
                                <div class="widget-title am-fl">流水操作</div>
                                <div class="widget-function am-fr">
                                    <a href="javascript:;" class="am-icon-cog"></a>
                                </div>
                            </div>
                            <div class="widget-body am-fr">

                                <form id="downFlow" action="" class="am-form tpl-form-line-form">


                                <div class="am-form-group">
                                        <label for="cards" class="am-u-sm-3 am-form-label">银行卡 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <select id="cards" name="cardId" data-am-selected="{searchBox: 1}" style="display: none;">

                                            </select>
                                            <button onclick="listFlow()" type="button" class="am-btn am-btn-default am-radius">查询</button>
                                            <button onclick="downFlow()" type="button" class="am-btn am-btn-default am-radius">下载</button>
                                            <%--<a href="###" onclick="downFlow()">下载</a>--%>
                                        </div>

                                    </div>

                                    

                                    <div class="am-form-group">
                                        <label for="password"  class="am-u-sm-3 am-form-label">密码 <span class="tpl-form-line-small-title"></span></label>
                                        <div class="am-u-sm-9">
                                            <input type="password" name="password" class="tpl-form-input" id="password" placeholder="请输入6位银行卡密码">
                                            <small></small>
                                        </div>
                                    </div>


                                </form>



                                <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl"></div>
                                <div class="widget-function am-fr">
                                   
                                </div>
                            </div>
                            <div class="widget-body  widget-body-lg am-fr">

                                <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="example-r">
                                    <thead>
                                        <tr>
                                            <th>卡号</th>
                                            <th>金额</th>
                                            <th>备注</th>
                                            <th>时间</th>
                                      
                                        </tr>
                                    </thead>
                                    <tbody id="flows">

                                        <!-- more data -->
                                    </tbody>
                                </table>

                                <ul class="am-pagination">
                                  <li><a onclick="first()">首页 &raquo;</a></li>
								  <li><a onclick="pre()">&laquo; 上一页</a></li>
								  <li><a onclick="next()">下一页 &raquo;</a></li>
								  <li><a onclick="last()">尾页 &raquo;</a></li>
								  								<li id="pageinfo">
         
        							</li>
								</ul>



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
    </div>
    </div>


</body>
<script>

    $(document).ready(function () {
        atm.loadCard()
        $("ul li a").removeClass("active")
        $("#flow").addClass("active")

    });

    var currentPage = 1;
    var totalPage = 0;

    function downFlow() {
        $('#downFlow').attr("action", "/user/downFlow.do")
        $('#downFlow').submit()
    }

    // function downFlow() {
    //     $.ajax({
    //         url:"/user/downFlow.do",
    //         type:"get",
    //         data:{
    //         },
    //         // success里的data是json对象
    //         success:function (data) {
    //             if (data.code != 1000) {
    //                 alert(data.message)
    //                 return
    //             }
    //         },
    //         error:function (e) {
    //             console.log(e)
    //             alert('error')
    //         }
    //
    //     })
    // }
    function listFlow() {
        atm.ajax("/card/listFlow.do",
            {
                currentPage:currentPage,
                cardId:$('#cards').val(),
                password:$('#password').val()
            },
            function (data) {
                totalPage = data.data.totalPage;
                msg = ''
                for (var i = 0; i < data.data.data.length; i++) {
                    msg += '<tr class="gradeX">\n' +
                        '<td>'+ data.data.data[i].cardNumber +'</td>\n' +
                        '<td>'+ data.data.data[i].amount +'</td>\n' +
                        '<td>'+ data.data.data[i].flowDesc +'</td>\n' +
                        '<td>'+ data.data.data[i].flowCreateTime +'</td>\n' +
                        '</tr>'
                }
                $('#flows').html(msg)
                $('#pageinfo').html(currentPage + '/' + totalPage)
            },
            function () {
            }
        )
    }

    // 为了ajax重定向，将ajax封装，故无需下边的写法了
    // function listFlow() {
    //     $.ajax({
    //         url:"/card/listFlow.do",
    //         type:"post",
    //         dataType:"json",
    //         data:{
    //             currentPage:currentPage,
    //             cardId:$('#cards').val(),
    //             password:$('#password').val()
    //         },
    //         // success里的data是json对象
    //         success:function (data) {
    //             if (data.code != 1000) {
    //                 alert(data.message)
    //                 return
    //             }
    //             totalPage = data.data.totalPage;
    //             msg = ''
    //             for (var i = 0; i < data.data.data.length; i++) {
    //                     msg += '<tr class="gradeX">\n' +
    //                             '<td>'+ data.data.data[i].cardNumber +'</td>\n' +
    //                             '<td>'+ data.data.data[i].amount +'</td>\n' +
    //                             '<td>'+ data.data.data[i].flowDesc +'</td>\n' +
    //                             '<td>'+ data.data.data[i].flowCreateTime +'</td>\n' +
    //                             '</tr>'
    //             }
    //             $('#flows').html(msg)
    //             $('#pageinfo').html(currentPage + '/' + totalPage)
    //
    //         },
    //         error:function (e) {
    //             console.log(e)
    //             alert('error')
    //
    //         }
    //     });
    // }
    
    function next() {
        if (currentPage + 1 > totalPage) {
            return
        }
        currentPage++;
        listFlow()
    }
    
    function pre() {
        if (currentPage - 1 < 1) {
            return
        }
        currentPage--;
        listFlow()
    }

    function first() {
        currentPage = 1;
        listFlow()
    }

    function last() {
        currentPage = totalPage;
        listFlow()
    }
</script>

</html>