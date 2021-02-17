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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/jquery.min.js"></script>
</head>

<body data-type="widgets">
<script src="assets/js/theme.js"></script>
<div class="am-g tpl-g">

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
                            <div class="widget-title  am-cf">书籍预定信息列表</div>
                        </div>
                        <div class="widget-body  am-fr">
                            <div class="am-u-sm-12 am-u-md-3 am-u-lg-3">
                                <div class="am-form-group">
                                    <c:if test="${requestScope.status == 2 }">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <span class="am-btn am-btn-default am-btn-success" id="all-pass"><span
                                                    class="am-icon-check"></span> 批量通过</span>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-9 am-u-lg-6">
                                <div class="am-form-group tpl-table-list-select">
                                    <a href="${pageContext.request.contextPath }/admin-order-list-show?page=1&status=4"
                                       style="padding-right: 30px">全部</a>
                                    <a href="${pageContext.request.contextPath }/admin-order-list-show?page=1&status=1"
                                       style="padding-right: 30px">正常（已入库）</a>
                                    <a href="${pageContext.request.contextPath }/admin-order-list-show?page=1&status=2"
                                       style="padding-right: 30px">待审批</a>
                                    <a href="${pageContext.request.contextPath }/admin-order-list-show?page=1&status=3"
                                       style="padding-right: 30px">驳回</a>
                                    <a href="${pageContext.request.contextPath }/admin-order-list-show?page=1&status=0"
                                       style="padding-right: 30px">已删除</a>
                                </div>
                            </div>
                            <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                                <form action="${pageContext.request.contextPath }/admin-order-searchorder-show"
                                      method="get">
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <input type="text" class="am-form-field" name="content">
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
                                        <c:if test="${requestScope.status == 2 }">
                                            <th style="width:5%">
                                                <button type="button" id="selectAll"
                                                        class="am-btn am-btn-default am-radius"
                                                        style="font-size: 14px;padding: 0px;margin: 0px">全选/反选
                                                </button>
                                            </th>
                                        </c:if>
                                        <th>序号</th>
                                        <th>预订人</th>
                                        <th>预定图书</th>
                                        <th>预定时间</th>
                                        <th>预定数量</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${requestScope.pageOrders }" var="order">
                                        <tr class="gradeX">
                                            <c:if test="${requestScope.status == 2 }">
                                                <td class="am-text-middle"><input type="checkbox" class="check"
                                                                                  value="${order.id}"
                                                                                  data-am-ucheck>
                                                </td>
                                            </c:if>
                                            <td class="am-text-middle">${order.id }</td>
                                            <td class="am-text-middle">${order.user.userUsername }</td>
                                            <td class="am-text-middle">${order.book.bookName }</td>
                                            <td class="am-text-middle">
                                                <fmt:formatDate value="${order.createTime }"
                                                                pattern="yyyy-MM-dd HH:mm:ss"/>
                                            </td>
                                            <td class="am-text-middle">${order.num }</td>
                                            <td class="am-text-middle">
                                                <c:if test="${order.status == 0 }">
                                                    作废
                                                </c:if>
                                                <c:if test="${order.status == 1 }">
                                                    正常
                                                </c:if>
                                                <c:if test="${order.status == 2 }">
                                                    未审批
                                                </c:if>
                                                <c:if test="${order.status == 3 }">
                                                    拒绝
                                                </c:if>
                                            </td>
                                            <td class="am-text-middle">
                                                <div class="tpl-table-black-operation">

                                                    <a href="${pageContext.request.contextPath }/admin-order-edit-show/${order.id }">
                                                        <i class="am-icon-pencil"></i> 编辑
                                                    </a>
                                                    <c:if test="${order.status == 2 }">
                                                        <a href="${pageContext.request.contextPath }/admin-order-check-show?id=${order.id}&status=1">
                                                            <i class="am-icon-pencil"></i> 通过
                                                        </a>
                                                        <a href="${pageContext.request.contextPath }/admin-order-check-show?id=${order.id}&status=3">
                                                            <i class="am-icon-pencil"></i> 拒绝
                                                        </a>
                                                    </c:if>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    <!-- more data -->
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${requestScope.pagePoint != null}">
                                <div class="am-u-lg-12 am-cf" style="font-size:14px;">
                                    <div class="am-fr">
                                        <ul class="am-pagination tpl-pagination">
                                            <li>
                                                <a href="${pageContext.request.contextPath }/admin-order-list-show?page=${requestScope.pagePoint - 1 }&status=${requestScope.status}">«</a>
                                            </li>
                                            <li><a>当前第${requestScope.pagePoint }页(共${requestScope.pageMax }页)</a></li>
                                            <li><a href="#!" id="skipBtn"> 跳转到&nbsp;<input type="text" name="pagePoint"
                                                                                           style="width:20px;height: 14px; color:black; font-size: 10px;">&nbsp;页</a>
                                            </li>
                                            <li>
                                                <a href="${pageContext.request.contextPath }/admin-order-list-show?page=${requestScope.pagePoint + 1 }&status=${requestScope.status}">»</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </c:if>
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

    $(function () {
        $("#skipBtn").on("click", function () {
            var page = $("#skipBtn>input").val();
            if (page == "") {
                page = 1;
            }
            window.location = "${pageContext.request.contextPath}/admin-order-list-show?page=" + page + "&status=${requestScope.status}";
        });
        $("#skipBtn > input").on("click", function () {
            return false;
        });
        $("#all-pass").on("click", function () {
            var bookIds =[];
            $("input[type='checkbox']:checked").each(function(){
                bookIds.push(this.value);
            });
            if(bookIds.length == 0){
                alert("您未选择项目！");
                return;
            }
            $.ajax({
                type: 'POST',
                url: 'admin-order-check-all-pass',
                data: JSON.stringify(bookIds),
                dataType:'json',
                contentType:'application/json',
                success: function (data) {
                    window.location = "${pageContext.request.contextPath}/admin-order-list-show?page=1&status=1";
                },
                error:function(data){
                    window.location = "${pageContext.request.contextPath}/admin-order-list-show?page=1&status=1";
                }

            });
        });
        $("#selectAll").on("click", function () {
            $(".check").uCheck('toggle');
        });
    })

</script>
<style>
    .check {
        position: inherit;
        opacity: inherit;
    }
</style>
</body>

</html>
