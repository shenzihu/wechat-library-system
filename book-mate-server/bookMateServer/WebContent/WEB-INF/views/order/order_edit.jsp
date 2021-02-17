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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/app.css">
    <script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
</head>

<body data-type="widgets">
<script src="${pageContext.request.contextPath }/assets/js/theme.js"></script>
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
                            <div class="widget-title am-fl">修改图书预定信息</div>
                            <div class="widget-function am-fr">
                            </div>
                        </div>
                        <div class="widget-body am-fr">
                            <form autocomplete="off" class="am-form tpl-form-line-form"
                                  action="${pageContext.request.contextPath }/admin-order-edit-execute"
                                  method="post">
                                <input type="hidden" name="id" value="${requestScope.order.id }">
                                <div class="am-form-group">
                                    <label for="order-user" class="am-u-sm-3 am-form-label">预定人</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" class="tpl-form-input" id="order-user" placeholder=""
                                               name="user.realName" value="${requestScope.order.user.realName}"
                                               readonly>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="order-book-name" class="am-u-sm-3 am-form-label">图书名</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" class="tpl-form-input" id="order-book-name" placeholder=""
                                               name="book.bookName" value="${requestScope.order.book.bookName }"
                                               readonly>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="order-createTime" class="am-u-sm-3 am-form-label">预定时间</label>
                                    <div class="am-u-sm-9">
                                        <input type="text" class="tpl-form-input" id="order-createTime" placeholder=""
                                               name="createTime"
                                               value="<fmt:formatDate value='${order.createTime }' pattern='yyyy-MM-dd HH:mm:ss'/>"
                                               readonly>
                                    </div>
                                </div>

                                <div class="am-form-group">
                                    <label for="order-status" class="am-u-sm-3 am-form-label">状态</label>
                                    <div class="am-u-sm-9">
                                        <select id="order-status" name="status" value="${requestScope.order.status }"
                                                style="background-color: #4b5357">
                                            <option value="0"
                                                    <c:if test="${0 == requestScope.order.status}">selected</c:if>>删除
                                            </option>
                                            <option value="1"
                                                    <c:if test="${1 == requestScope.order.status}">selected</c:if>>正常
                                            </option>
                                            <option value="2"
                                                    <c:if test="${2 == requestScope.order.status}">selected</c:if>>未审批
                                            </option>
                                            <option value="3"
                                                    <c:if test="${3 == requestScope.order.status}">selected</c:if>>拒绝
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <label for="order-num" class="am-u-sm-3 am-form-label">预定数量</label>
                                    <div class="am-u-sm-9">
                                        <input type="number" class="tpl-form-input" id="order-num" placeholder=""
                                               name="num" value="${requestScope.order.num}">
                                    </div>
                                </div>
                                <div class="am-form-group">
                                    <div class="am-u-sm-9 am-u-sm-push-3">
                                        <button type="submit" class="am-btn am-btn-primary tpl-btn-bg-color-success ">
                                            提交
                                        </button>
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
<script src="${pageContext.request.contextPath }/assets/js/amazeui.min.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/app.js"></script>
</body>

</html>
