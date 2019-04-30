<!DOCTYPE html>
<html lang="en">

<head>
    <%--解决界面乱码--%>
    <meta charset="utf-8">
    <%--解决JSP乱码，JSP靠java代码输出--%>
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
                <div class="row  am-cf" id="loadMyCards">
                </div>

               

 <div class="am-u-sm-12 am-u-md-12 am-u-lg-6">
                        <div class="widget am-cf">
                            <div class="widget-head am-cf">
                                <div class="widget-title am-fl">最近十笔流水</div>
                                <div class="widget-function am-fr">
                                    
                                </div>
                            </div>
                            <div class="widget-body  widget-body-lg am-fr">

                                <table width="100%" class="am-table am-table-compact am-table-bordered am-table-radius am-table-striped tpl-table-black " id="example-r">
                                    <thead>
                                        <tr>
                                            <th>卡号</th>
                                            <th>金额</th>
                                            <th>备注</th>
                                            <th>时间</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="listFlowTop10">

                                        
                                        <!-- more data -->
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>



            </div>
        </div>
    </div>
    </div>
    <script src="/assets/js/amazeui.min.js"></script>
    <script src="/assets/js/amazeui.datatables.min.js"></script>
    <script src="/assets/js/dataTables.responsive.min.js"></script>
    <script src="/assets/js/app.js"></script>

    <script>
        $(document).ready(function () {
            listFlowTop10()
            loadMyCards()
        });
        
        function loadMyCards() {
            $.ajax({
                url:"/card/listMyCard.do",
                type:"POST",
                dataType: "json",
                data:{

                },
                success: function (data) {
                    if (data.code != 1000) {
                        alert(data.message)
                        return
                    }
                    msg = ''
                    for (var i=0; i < data.data.length; i++) {
                        msg += '<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">\n' +
                            '                        <div class="widget widget-primary am-cf">\n' +
                            '                            <div class="widget-statistic-header">\n' +
                            '                                '+ data.data[i].cardNumber+'\n' +
                            '                            </div>\n' +
                            '                            <div class="widget-statistic-body">\n' +
                            '                                <div class="widget-statistic-value">\n' +
                            '                                    '+ data.data[i].amount +'\n' +
                            '                                </div>\n' +
                            '                                <div class="widget-statistic-description">\n' +
                            '                                    \n' +
                            '                                </div>\n' +
                            '                                <span class="widget-statistic-icon am-icon-credit-card-alt"></span>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                    </div>'
                    }
                    $('#loadMyCards').html(msg)
                },
                error: function (e) {
                    console.log(e)
                    alert("error")
                }
            })
        }
        
        function listFlowTop10() {
            $.ajax({
                url: "/card/listFlowTop10.do",
                type: "POST",
                dataType: "json",
                data: {},
                success: function (data) {
                    if (data.code != 1000) {
                        alert(data.message)
                        return
                    }
                    msg = ''
                    for (var i = 0; i < data.data.length; i++) {
                        msg += ' <tr class="gradeX">\n' +
                            '                                            <td>' + data.data[i].cardNumber + '</td>\n' +
                            '                                            <td>' + data.data[i].amount + '</td>\n' +
                            '                                            <td>' + data.data[i].flowDesc + '</td>\n' +
                            '                                            <td>' + data.data[i].flowCreateTime + '</td>\n' +
                            '                                        </tr>'
                    }
                    $('#listFlowTop10').html(msg)
                },
                error: function (e) {
                    console.log(e)
                    alert("error")
                }
            })
        }
    </script>
</body>

</html>