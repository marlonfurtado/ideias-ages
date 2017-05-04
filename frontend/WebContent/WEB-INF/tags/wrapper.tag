<%@ tag description="Template" pageEncoding="UTF-8"%>
<%@ attribute name="pageTitle" required="true"%>
<%@attribute name="scripts" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta name="robots" content="noindex,nofollow" />

        <title>Ideias AGES - ${pageTitle}</title>

        <!-- Bootstrap -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all' rel='stylesheet' type='text/css'>
        <link href="../assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div id="loadingWrapper"></div>

        <jsp:invoke fragment="header"/>
        <jsp:doBody />
        <jsp:invoke fragment="footer"/>

        <script src="../assets/js/store.legacy.min.js" type="text/javascript"></script>
        <script src="../assets/js/jquery.js" type="text/javascript"></script>
        <script src="../assets/js/jquery.mask.min.js" type="text/javascript"></script>
        <script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>
        <jsp:invoke fragment="scripts"/>
    </body>
</html>