<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- 侧边导航栏 -->
<div class="left-sidebar">
    <!-- 用户信息 -->
    <div class="tpl-sidebar-user-panel">
        <div class="tpl-user-panel-slide-toggleable">
            <div class="tpl-user-panel-profile-picture">
                <img src="${pageContext.request.contextPath }/adminPhoto/photo.jpg" alt="">
            </div>
            <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              ${sessionScope.admin.adminUsername }
          </span>
            <a href="${pageContext.request.contextPath }/admin-index-edit-show" class="tpl-user-panel-action-link">
                <span class="am-icon-pencil"></span> 账号设置</a>
        </div>
    </div>

    <!-- 菜单 -->
    <ul class="sidebar-nav">
        <li class="sidebar-nav-link">
            <a href="${pageContext.request.contextPath }/admin-index-index-show" id="index">
                <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="${pageContext.request.contextPath }/admin-order-list-show?page=1&status=4" id="order">
                <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 预定

            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="${pageContext.request.contextPath }/admin-book-list-show?page=1&status=4" id="book">
                <i class="am-icon-book sidebar-nav-link-logo"></i> 图书
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="${pageContext.request.contextPath }/admin-isbn-list-show?status=all" id="isbn">
                <i class="am-icon-star sidebar-nav-link-logo"></i> ISBN
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="${pageContext.request.contextPath }/admin-user-list-show?page=1" id="user">
                <i class="am-icon-user sidebar-nav-link-logo"></i> 用户
            </a>
        </li>
        <li class="sidebar-nav-link">
            <a href="javascript:;" class="sidebar-nav-sub-title">
                <i class="am-icon-street-view sidebar-nav-link-logo"></i> 管理员
                <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
            </a>
            <ul class="sidebar-nav sidebar-nav-sub">
                <li class="sidebar-nav-link">
                    <a href="${pageContext.request.contextPath }/admin-admin-list-show?page=1" id="admin-list">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 管理员列表
                    </a>
                </li>
                <li class="sidebar-nav-link">
                    <a href="${pageContext.request.contextPath }/admin-admin-register-show" id="admin-register">
                        <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 添加管理员
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</div>


<script type="text/javascript">
    var nav = "${requestScope.nav}";
    var elem = "#" + nav;
    if (nav == "admin-list" || nav == "admin-message") {
        $(elem).attr("class", "sub-active");
    } else {
        $(elem).attr("class", "active");
    }
</script>

