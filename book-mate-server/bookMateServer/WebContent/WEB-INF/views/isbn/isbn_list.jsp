<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>图书登记后台管理</title>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
    <% int i = 1;%>
    <style>
        th {
            text-align: center;
        }

        td {
            text-align: center;
        }
        .pd-30{
         padding-right: 30px;
        }
    </style>

</head>

<body data-type="widgets">

<!-- 加载顶部导航栏 -->
<jsp:include page="../index/header.jsp"/>

<!-- 加载侧边导航栏 -->
<jsp:include page="../index/list.jsp"/>


<!-- 内容区域 -->
<div class="tpl-content-wrapper">
    <div class="row-content am-cf">
        <div class="row">
            <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                <div class="widget am-cf">
                    <div class="widget-head am-cf">
                        <div class="widget-title  am-cf">书籍ISBN列表</div>
                    </div>
                    <div class="widget-body  am-fr">
                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                        <div class="am-form-group">
                            <a href="${pageContext.request.contextPath }/admin-isbn-list-show?status=1" class="pd-30">已爬取数据</a>
                            <a href="${pageContext.request.contextPath }/admin-isbn-list-show?status=0" class="pd-30">未爬虫数据</a>
                            <a href="${pageContext.request.contextPath }/admin-isbn-list-show?status=2" class="pd-30">爬取失败</a>
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                        <div class="am-form-group tpl-table-list-select">
                        </div>
                    </div>
                    <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                        <form action="${pageContext.request.contextPath }/admin-userid-searchuser-show"
                              method="get">
                            <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                <input type="text" class="am-form-field" name="username">
                                <span class="am-input-group-btn">
            								<button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
                                                    type="submit"></button>
         								</span>
                            </div>
                        </form>
                    </div>
                    <div class="am-u-sm-12">
                        <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black ">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>ISBN</th>

                                <th>书名</th>
                                <th>提交者用户名</th>
                                <th>提交者</th>
                                <th>提交时间</th>
                                <th>状态</th>
                                <th>备注</th>
                                <th>爬取时间</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.isbn }" var="isb">
                                <tr class="gradeX">
                                    <td class="am-text-middle"><%=i++ %></td>
                                    <td class="am-text-middle">${isb.isbn}</td>
                                    <td class="am-text-middle">${isb.bookName}</td>
                                    <td class="am-text-middle">${isb.user.userUsername}</td>
                                    <td class="am-text-middle">${isb.user.realName}</td>
                                    <td class="am-text-middle">
                                        <fmt:formatDate value="${isb.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td class="am-text-middle">${isb.status ==0?"未爬取":(isb.status ==1?"已取得":"爬取失败")}</td>
                                    <td class="am-text-middle">${isb.comment}</td>
                                    <td class="am-text-middle">
                                        <fmt:formatDate value="${isb.getTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                        <%--<td class="am-text-middle">--%>
                                        <%--<div class="tpl-table-black-operation">--%>
                                        <%--<a href="${pageContext.request.contextPath }/admin-userid-resetcredit-execute/${isb.id }">信用重置</a>--%>
                                        <%--<a href="admin-admin-removeuser-execute/${users.userId}"--%>
                                        <%--class="tpl-table-black-operation-del">--%>
                                        <%--删除--%>
                                        <%--</a>--%>
                                        <%--</div>--%>
                                        <%--</td>--%>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
<script type="text/javascript">

    <%--$(function () {--%>
        <%--$("#skipBtn").on("click", function () {--%>
            <%--var page = $("#skipBtn > input").val();--%>
            <%--if (page == "") {--%>
                <%--page = 1;--%>
            <%--}--%>
            <%--window.location = "${pageContext.request.contextPath}/admin-userid-list-show?page=" + page;--%>
        <%--});--%>
        <%--$("#skipBtn > input").on("click", function () {--%>
            <%--return false;--%>
        <%--});--%>
    <%--})--%>

</script>

</body>

</html>
