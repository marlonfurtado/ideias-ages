<%@ tag description="Template" pageEncoding="UTF-8"%>
<%@ attribute name="pageTitle" required="true"%>
<%@attribute name="scripts" fragment="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Ideias AGES - ${pageTitle}</title>

        <!-- Bootstrap -->
        <link href="./assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="./assets/css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-offset-4 col-md-4 ">
                            <div id="errorMessageDiv" class="alert alert-danger" style="display: none; margin-bottom: 0 !important;">
                                <div id="errorMessageContent">
                                    <ul class="errors" role="alert">
                                        <li id="errorMessageText"></li>
                                    </ul>
                                </div>
                            </div>
                            <div id="includedContent">
                                <jsp:doBody />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="./assets/js/jquery.js" type="text/javascript"></script>
        <script src="./assets/js/bootstrap.min.js" type="text/javascript"></script>
        <jsp:invoke fragment="scripts"/>
    </body>
</html>