<html>
    <head>
        <title>Welcome to Grails</title>
        <meta name="layout" content="main" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:280px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <div id="nav">
            <div class="homePagePanel">
                <div class="panelTop"></div>
                <div class="panelBody">
                    <h1>IMP-CDA</h1>
                    <ul>
                        <li>Numero de Organizaciones: N </li>
                        <li>CDA´s Registrados: N </li>

                    </ul>
                    
                </div>
                <div class="panelBtm"></div>
            </div>
        </div>
        <div id="pageBody">
            <h1>SOS Telemedicina, Índice Maestro de Pacientes</h1>
            <p></p>

            <div id="controllerList" class="dialog">
                <h2>Panel de Control:</h2>
                <ul>
                  
               <!--   <li><g:link controller="paciente">Administrar Pacientes</g:link></li>
                  <br />
                  <li><g:link controller="cda">Administrar Cdas</g:link></li>
                  <br />
                  <li><g:link controller="personal">Administrar Personal</g:link></li>
               -->
               
                  <li><g:link controller="role">Administrar Roles</g:link></li>
                  <br />
                  <li><g:link controller="user">Administrar Usuarios</g:link></li>
                  <br />
                  <li><g:link controller="organizacion">Administrar Organizaciones</g:link></li>
                  <br />


                </ul>
            </div>
            <br />
            <g:link controller="logout" action="index"><g:message code="default.session.sing.in"/></g:link>
        </div>
    </body>
</html>
