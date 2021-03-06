<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
    <jsp:body>
        <!--Your content goes here-->
        <h2>${Model.name}</h2>
        <hr>
        <a href="/threads/${Model.threadID}/post/create">Create Post</a>
        <c:forEach var="post" items="${Model.posts.allPosts}">
            <a href="/threads/${Model.threadID}/post/${post.postID}">${post.title}</a>
        </c:forEach>

    </jsp:body>
</t:genericpage>