<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Eliminar Usuarios por ID</h1>
    <form class="form-container" action="eliminarUserId" method="post">
        <label class="form-label" for="id">ID del Usuario a Eliminar:</label>
        <input class="form-input" type="text" id="id" name="id" placeholder="Escribe el ID" />
        <input class="form-button" type="submit" id="submit" name="submit" value="Eliminar"/>
    </form>
    <a class='volver' href='/project-usuarios/mostrarUsuarios'>Volver a la lista</a>
</body>
</html>
