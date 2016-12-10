<%@ tag pageEncoding="UTF-8" description="分页" %>
<%@ attribute name="page" type="com.atguigu.p2p.util.Page" required="true" description="分页对象" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table width="100%" align="center">
    <tr>
    	<td>
    		<span class="page-info">[共有记录${page.totalCount}条  / 共分${page.totalPage}页  ， 当前是第 ${page.nowPage} 页]</span >
    	</td>
    	<td>	
	        <c:choose>
	            <c:when test="${page.nowPage==1}">
	                	第一页 &nbsp;&nbsp;
	                	上一页&nbsp;&nbsp;
	            </c:when>
	            <c:otherwise>
	                <a href="#" onclick="turnPage(1);" title="首页">第一页</a>&nbsp;&nbsp;
	                <a href="#" onclick="turnPage(${page.nowPage - 1});" title="上一页">上一页</a>&nbsp;&nbsp;
	            </c:otherwise>
	        </c:choose>
	        <c:choose>
	            <c:when test="${page.nowPage==page.totalPage}">
	                	下一页&nbsp;&nbsp;
	                	最后页&nbsp;&nbsp;
	            </c:when>
	            <c:otherwise>
	                <a href="#" onclick="turnPage(${page.nowPage + 1});" title="下一页">下一页</a>&nbsp;&nbsp;
	                <a href="#" onclick="turnPage(${page.totalPage});" title="尾页">最后页</a>&nbsp;&nbsp;
	            </c:otherwise>
	        </c:choose>
		</td>
	</tr>
</table>