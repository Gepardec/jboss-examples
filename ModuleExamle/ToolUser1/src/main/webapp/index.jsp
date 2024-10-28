<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="examples.tools.MyLogger,java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Benutze SimpleTools_1.0</title>
</head>
<body>

	Start <% out.println(new Date().toString() );%> <br />
	Call: SimpleTool.log("ToolUser1") <br />
	<% 	
		MyLogger logger = new MyLogger();
		logger.log("Meldung von ToolUser1");
	%> 
	Ende <br />
	
	
</body>
</html>
