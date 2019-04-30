<%@ page  pageEncoding="utf-8"%>

<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">

            <%--enctype：规定在发送表单数据之前如何对其进行编码。--%>
            <%--multipart/form-data:不对字符编码，在使用包含文件上传空间的表单时，必须使用该值--%>
            <%--target：目标 ，form表单就会跳转到target，用子页面接收--%>
            <form id="uploadForm" action="" method="post" enctype="multipart/form-data" target="avatarIframe">

                <div class="am-form-group am-form-file">
                    <div class="tpl-user-panel-profile-picture">
                        <img id="avatarImg" src="/user/loadMyAvatar.do" alt="">
                    </div>

                    <%--<input type="text" name="name" value="tom">--%>
                    <input id="avatarFile" type="file" multiple="" name="avatar" onchange="uploadAvatar()">
                </div>
            </form>



            <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
        </div>
    </div>

    <!-- 菜单 -->
    <ul class="sidebar-nav">

        <li class="sidebar-nav-link">
            <a href="/user/toHome.do" class="active">
                <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/user/toOpenAccount.do" id="openAccount">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/user/toWithdrawal.do" id="withrawal">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 取款
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/user/toDeposit.do" id="deposit">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款

            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="/user/toTransfer.do" id="transfer">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

            </a>
        </li>

        <li class="sidebar-nav-link">
            <a href="/user/toFlow.do" id="flow">
                <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 流水

            </a>
        </li>

    </ul>
</div>

<%--用子页面提交表单，这样父页面就不会跳转--%>
<%--display:none:隐藏显示--%>
<iframe name="avatarIframe" style="display:none"></iframe>

<script>
    function uploadAvatar() {
        $('#uploadForm').attr('action', '/user/uploadAvatar2.do')
        $('#uploadForm').submit()
    }

    function showAvatar() {
        // url地址要与之前的不一样，因为是get请求，有缓存，浏览器会加载缓存,为了防止缓存，加个？和时间，这样就每次地址都会变
        $("#avatarImg").attr('src', "/user/loadMyAvatar.do?" + new Date().getTime())
    }

</script>