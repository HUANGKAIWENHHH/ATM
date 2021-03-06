/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.2.26.v20180806
 * Generated at: 2019-04-28 06:32:51 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.page.common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class head_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<title>Amaze UI Admin index Examples</title>\n");
      out.write("<meta name=\"description\" content=\"这是一个 index 页面\">\n");
      out.write("<meta name=\"keywords\" content=\"index\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<meta name=\"renderer\" content=\"webkit\">\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\n");
      out.write("<link rel=\"icon\" type=\"image/png\" href=\"/assets/i/favicon.png\">\n");
      out.write("<link rel=\"apple-touch-icon-precomposed\" href=\"/assets/i/app-icon72x72@2x.png\">\n");
      out.write("<meta name=\"apple-mobile-web-app-title\" content=\"Amaze UI\" />\n");
      out.write("<script src=\"/assets/js/echarts.min.js\"></script>\n");
      out.write("<link rel=\"stylesheet\" href=\"/assets/css/amazeui.min.css\" />\n");
      out.write("<link rel=\"stylesheet\" href=\"/assets/css/amazeui.datatables.min.css\" />\n");
      out.write("<link rel=\"stylesheet\" href=\"/assets/css/app.css\">\n");
      out.write("<script src=\"/assets/js/jquery.min.js\"></script>\n");
      out.write("<script src=\"/assets/js/theme.js\"></script>\n");
      out.write("<script src=\"/assets/js/amazeui.min.js\"></script>\n");
      out.write("<script src=\"/assets/js/amazeui.datatables.min.js\"></script>\n");
      out.write("<script src=\"/assets/js/dataTables.responsive.min.js\"></script>\n");
      out.write("<script src=\"/assets/js/app.js\"></script>\n");
      out.write("<script src=\"/js/atm.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("    <!-- logo -->\n");
      out.write("    <div class=\"am-fl tpl-header-logo\">\n");
      out.write("        <a href=\"javascript:;\">简易ATM系统</a>\n");
      out.write("    </div>\n");
      out.write("    <!-- 右侧内容 -->\n");
      out.write("    <div class=\"tpl-header-fluid\">\n");
      out.write("        <!-- 侧边切换 -->\n");
      out.write("\n");
      out.write("        <!-- 搜索 -->\n");
      out.write("\n");
      out.write("        <!-- 其它功能-->\n");
      out.write("        <div class=\"am-fr tpl-header-navbar\">\n");
      out.write("            <ul>\n");
      out.write("                <!-- 欢迎语 -->\n");
      out.write("                <li class=\"am-text-sm tpl-header-navbar-welcome\">\n");
      out.write("                    <a href=\"javascript:;\">欢迎你, <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginUser.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</span> </a>\n");
      out.write("                </li>\n");
      out.write("\n");
      out.write("                <!-- 新邮件 -->\n");
      out.write("\n");
      out.write("                <!-- 新提示 -->\n");
      out.write("                <li class=\"am-dropdown\" data-am-dropdown=\"\">\n");
      out.write("                    <a href=\"javascript:;\" class=\"am-dropdown-toggle\" data-am-dropdown-toggle=\"\">\n");
      out.write("                        <i class=\"am-icon-bell\"></i>\n");
      out.write("                        <span class=\"am-badge am-badge-warning am-round item-feed-badge\">5</span>\n");
      out.write("                    </a>\n");
      out.write("\n");
      out.write("                    <!-- 弹出列表 -->\n");
      out.write("                    <ul class=\"am-dropdown-content tpl-dropdown-content\">\n");
      out.write("                        <li class=\"tpl-dropdown-menu-notifications\">\n");
      out.write("                            <a href=\"javascript:;\" class=\"tpl-dropdown-menu-notifications-item am-cf\">\n");
      out.write("                                <div class=\"tpl-dropdown-menu-notifications-title\">\n");
      out.write("                                    <i class=\"am-icon-line-chart\"></i>\n");
      out.write("                                    <span> 有6笔新的销售订单</span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"tpl-dropdown-menu-notifications-time\">\n");
      out.write("                                    12分钟前\n");
      out.write("                                </div>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"tpl-dropdown-menu-notifications\">\n");
      out.write("                            <a href=\"javascript:;\" class=\"tpl-dropdown-menu-notifications-item am-cf\">\n");
      out.write("                                <div class=\"tpl-dropdown-menu-notifications-title\">\n");
      out.write("                                    <i class=\"am-icon-star\"></i>\n");
      out.write("                                    <span> 有3个来自人事部的消息</span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"tpl-dropdown-menu-notifications-time\">\n");
      out.write("                                    30分钟前\n");
      out.write("                                </div>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                        <li class=\"tpl-dropdown-menu-notifications\">\n");
      out.write("                            <a href=\"javascript:;\" class=\"tpl-dropdown-menu-notifications-item am-cf\">\n");
      out.write("                                <div class=\"tpl-dropdown-menu-notifications-title\">\n");
      out.write("                                    <i class=\"am-icon-folder-o\"></i>\n");
      out.write("                                    <span> 上午开会记录存档</span>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"tpl-dropdown-menu-notifications-time\">\n");
      out.write("                                    1天前\n");
      out.write("                                </div>\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <li class=\"tpl-dropdown-menu-notifications\">\n");
      out.write("                            <a href=\"javascript:;\" class=\"tpl-dropdown-menu-notifications-item am-cf\">\n");
      out.write("                                <i class=\"am-icon-bell\"></i> 进入列表…\n");
      out.write("                            </a>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </li>\n");
      out.write("\n");
      out.write("                <!-- 退出 -->\n");
      out.write("                <li class=\"am-text-sm\">\n");
      out.write("                    <a href=\"/toLogin.do\">\n");
      out.write("                        <span class=\"am-icon-sign-out\"></span> 退出\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("</header>");
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
