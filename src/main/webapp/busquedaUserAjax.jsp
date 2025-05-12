<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
    <script>
    function validarNombre() {
        var nombre = document.getElementById("nombre").value;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "procesarNombre", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById("resultado").innerHTML = xhr.responseText;
            }
        };

        xhr.send("nombre=" + encodeURIComponent(nombre));
    }

    </script>
</head>
<body>
    <h1>Buscar Usuario por Nombre</h1>
    <label class="labelAjax" for="nombre">Nombre:</label>
    <input class="nombreInput" type="text" id="nombre" name="nombre" onkeyup="validarNombre()" placeholder="Escribe el nombre..." />

    <div id="resultado"></div>
     <a class="volver" href="<%= request.getContextPath() %>/mostrarUsuarios">Volver a la lista completa</a>
</body>
</html>
