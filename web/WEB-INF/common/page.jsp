<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="page">
    <ul class="pagination">
        <li class="page-item"><a class="page-link"
                                 href="${url}&p=1">首页</a></li>
        <c:if test="${cp>1}">
            <li class="page-item"><a class="page-link"
                                     href="${url}&p=${cp-1}">上一页</a></li>
        </c:if>
        <c:forEach begin="${cp-2>1 ? (cp-2) :1}"
                   end="${cp+2>tp?tp:(cp+2)}" var="e">
            <%--            判断是否是当前页--%>
            <c:if test="${cp==e}">
                <li class="page-item"><a class="page-link" style="background-color: #007bff;color:white"
                                         href="${url}&p=${e}">${e}</a></li>
            </c:if>
            <c:if test="${cp!=e}">
                <li class="page-item"><a class="page-link"
                                         href="${url}&p=${e}">${e}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${cp<tp}">
            <li class="page-item"><a class="page-link"
                                     href="${url}&p=${cp+1}">下一页</a></li>
        </c:if>
        <li class="page-item"><a class="page-link"
                                 href="${url}&p=${tp}">尾页</a></li>
    </ul>

</div>
					