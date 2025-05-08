<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1>Eliminar Usuarios por ID</h1>
    <form class="form-container" action="<%= request.getContextPath() %>/eliminarUserId" method="post">
        <label class="form-label" for="id">ID del Usuario a Eliminar:</label>
        <input class="form-input" type="text" id="id" name="id" placeholder="Escribe el ID" />
        <input class="form-button" type="submit" value="Eliminar" />
    </form>
    <a class="volver" href="<%= request.getContextPath() %>/mostrarUsuarios">Volver a la lista</a>
</body>
</html>
