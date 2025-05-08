<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet"
        href="<%= request.getContextPath() %>/css/style.css"
        type="text/css" />
  <script>
    // Definimos la base para AJAX
    const basePath = '<%= request.getContextPath() %>';

    function validarNombre() {
      const nombre = document.getElementById("nombre").value;
      const xhr = new XMLHttpRequest();
      xhr.open("POST", basePath + "/procesarNombre", true);
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

  <p>
    <a href="<%= request.getContextPath() %>/mostrarUsuarios"
       class="volver">Volver lista completa</a>
  </p>
</body>
</html>
