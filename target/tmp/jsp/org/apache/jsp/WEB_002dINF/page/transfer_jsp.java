/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.2.26.v20180806
 * Generated at: 2019-04-28 06:33:30 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.page;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class transfer_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body data-type=\"index\">\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("    <div class=\"am-g tpl-g\">\r\n");
      out.write("        <!-- 头部 -->\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/head.jsp", out, false);
      out.write("\r\n");
      out.write("        <!-- 风格切换 -->\r\n");
      out.write("        \r\n");
      out.write("        <!-- 侧边导航栏 -->\r\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "common/left.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!-- 内容区域 -->\r\n");
      out.write("        <div class=\"tpl-content-wrapper\">\r\n");
      out.write("\r\n");
      out.write("            <div class=\"row-content am-cf\">\r\n");
      out.write("                <div class=\"row  am-cf\">\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"row\">\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"am-u-sm-12 am-u-md-12 am-u-lg-12\">\r\n");
      out.write("                        <div class=\"widget am-cf\">\r\n");
      out.write("                            <div class=\"widget-head am-cf\">\r\n");
      out.write("                                <div class=\"widget-title am-fl\">转账操作</div>\r\n");
      out.write("                                <div class=\"widget-function am-fr\">\r\n");
      out.write("                                    <a href=\"javascript:;\" class=\"am-icon-cog\"></a>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"widget-body am-fr\">\r\n");
      out.write("\r\n");
      out.write("                                <form class=\"am-form tpl-form-line-form\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("                                <div class=\"am-form-group\">\r\n");
      out.write("                                        <label for=\"cards\" class=\"am-u-sm-3 am-form-label\">银行卡 <span class=\"tpl-form-line-small-title\"></span></label>\r\n");
      out.write("                                        <div class=\"am-u-sm-9\">\r\n");
      out.write("                                            <select id=\"cards\" data-am-selected=\"{searchBox: 1}\" style=\"display: none;\">\r\n");
      out.write("\r\n");
      out.write("                                            </select>\r\n");
      out.write("\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"am-form-group\">\r\n");
      out.write("                                        <label for=\"cardInNum\" class=\"am-u-sm-3 am-form-label\">转入卡号 <span class=\"tpl-form-line-small-title\"></span></label>\r\n");
      out.write("                                        <div class=\"am-u-sm-9\">\r\n");
      out.write("                                            <input type=\"text\" class=\"tpl-form-input\" id=\"cardInNum\" placeholder=\"请输入收款卡号\">\r\n");
      out.write("                                            <small></small>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"am-form-group\">\r\n");
      out.write("                                        <label for=\"password\" class=\"am-u-sm-3 am-form-label\">密码 <span class=\"tpl-form-line-small-title\"></span></label>\r\n");
      out.write("                                        <div class=\"am-u-sm-9\">\r\n");
      out.write("                                            <input type=\"password\" class=\"tpl-form-input\" id=\"password\" placeholder=\"请输入6位银行卡密码\">\r\n");
      out.write("                                            <small></small>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"am-form-group\">\r\n");
      out.write("                                        <label for=\"amount\" class=\"am-u-sm-3 am-form-label\">金额 <span class=\"tpl-form-line-small-title\"></span></label>\r\n");
      out.write("                                        <div class=\"am-u-sm-9\">\r\n");
      out.write("                                            <input type=\"text\" class=\"tpl-form-input\" id=\"amount\" placeholder=\"请输入转账金额\">\r\n");
      out.write("                                            <small></small>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                    <audio id=\"speech\" src=\"\" autoplay=\"autoplay\" onended=\"javascript:window.location.href='/user/toHome.do'\"></audio>\r\n");
      out.write("\r\n");
      out.write("                                    <div class=\"am-form-group\">\r\n");
      out.write("                                        <div class=\"am-u-sm-9 am-u-sm-push-3\">\r\n");
      out.write("                                            <button onclick=\"transfer()\" type=\"button\" class=\"am-btn am-btn-primary tpl-btn-bg-color-success \">转账</button>\r\n");
      out.write("                                        </div>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                                </form>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("                    \r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("               \r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("    // $:jQuery对象名，document：当前页面文档\r\n");
      out.write("    // 当前页面文档加载全部加载完毕后调用function\r\n");
      out.write("    $(document).ready(function() {\r\n");
      out.write("        atm.loadCard()\r\n");
      out.write("        $(\"ul li a\").removeClass(\"active\")\r\n");
      out.write("        $(\"#transfer\").addClass(\"active\")\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    function transfer(){\r\n");
      out.write("        $.ajax({\r\n");
      out.write("            url: \"/card/transfer.do\",\r\n");
      out.write("            data:{\r\n");
      out.write("                cardOutId: $('#cards').val(),\r\n");
      out.write("                password: $('#password').val(),\r\n");
      out.write("                amount: $('#amount').val(),\r\n");
      out.write("                cardInNum: $('#cardInNum').val()\r\n");
      out.write("            },\r\n");
      out.write("            type: \"POST\",\r\n");
      out.write("            dataType: \"json\",\r\n");
      out.write("            success: function (data) {\r\n");
      out.write("                if (data.code != 1000) {\r\n");
      out.write("                    alert(data.message)\r\n");
      out.write("                    return\r\n");
      out.write("                }\r\n");
      out.write("                alert('转账成功')\r\n");
      out.write("                $(\"#speech\").attr('src', '/speech?word=转账成功')\r\n");
      out.write("                // window.location.href = '/user/toHome.do'\r\n");
      out.write("            },\r\n");
      out.write("            error: function (e) {\r\n");
      out.write("                console.log(e)\r\n");
      out.write("                alert('error')\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("    }\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
