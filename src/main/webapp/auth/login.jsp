<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    </head>
    <style>
        .box-principal {
            display: flex;
            flex-direction: column;
            flex-wrap: nowrap;
            align-content: center;
            justify-content: center;
            align-items: center;
            margin: 100px 10px 10px 10px;
        }
        .invalid-user{
            color: red;
        }
    </style>

    <body>

        <form action="/prj_encomendas/auth" method="POST">
            <div class="alert alert-warning alert-dismissible fade show" role="alert" style="display: ${show == 'true' ? 'block' : 'none'}">
                <strong>Erro ao efetuar login: </strong>   ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>

            <div class="box-principal ">
                <h1>Login</h1>
                <div class="col-md-6 col-12">
                    <label for="id-user">Usuário</label>
                    <input type="text" class="form-control" id="id-user" name="usuario" aria-describedby="emailHelp" autocomplete="off">
                </div>
                <div class="col-md-6 col-12">
                    <label for="id-pass">Senha</label>
                    <input type="password" class="form-control" name="senha" id="id-pass">
                </div>
                <div class="col-md-6 col-12" style="margin-top: 20px">
                    <button id="btn-entrar" type="submit" class="btn btn-primary form-control" disabled="disabled" name="btn-login" value="login">Entrar</button>
                </div>
            </div>
        </form>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
        <script type="text/javascript">

            $(document).ready(function () {
                window.history.forward();
                $("#id-pass").keyup(function () {
                    if ($("#id-user").val() !== '' && $("#id-pass").val() !== '') {
                        $("#btn-entrar").removeAttr("disabled");
                    } else if ($("#id-pass").val() === '') {
                        $("#btn-entrar").attr("disabled", "disabled");
                    }
                });

                $("#id-user").focus(function () {
                    $("#id-user").next().remove();
                    $("#id-pass").removeAttr("disabled");
                });

                $("#id-user").blur(function () {
                    if ($("#id-user").val() !== '') {

                        $.ajax({
                            url: "/prj_encomendas/auth",
                            method: "POST",
                            data: {
                                ajax: true,
                                usuario: $("#id-user").val(),
                                senha: $("#id-pass").val()
                            },
                            success: function (result) {
                                var json = JSON.parse(result);
                                if (json && json.show) {
                                    $("#id-user").after("<div class='invalid-user'>" + json.message + "</div>");
                                    $("#id-pass").attr("disabled", "disabled");
                                    $("#btn-entrar").attr("disabled", "disabled");
                                }
                            }

                        });
                    }
                });
            });
        </script>

    </body>


</html>
