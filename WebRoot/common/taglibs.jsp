<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="<c:url value='/rc/sc/jq/jquery-1.8.3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/rc/sc/jq/plus/jquery.form-3.25.min.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/rc/st/common/public.css"/>" />
<SCRIPT type="text/javascript">
$.ajaxSetup({cache : false});var contextPath = "/";
</SCRIPT>