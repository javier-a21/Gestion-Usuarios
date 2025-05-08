<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Usuarios</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
    <h1>Formulario de Registro de Usuarios</h1>

    <form action="<%= request.getContextPath() %>/procesarFormulario"
          method="post">
        <label for="nombre">Nombre</label>
        <input style="width:80%" class="nombreForm" type="text" id="nombre" name="nombre" required="required">
        <br><br>

        <label for="edad">Edad</label><br>
        <input  style="width:80%" type="number" id="edad" name="edad" required="required">
        <br><br>

        <select id="sexo" name="sexo" required="required">
            <option selected disabled="disabled">
                Seleccione..
            </option>
            <option value="Hombre">Hombre</option>
            <option value="Mujer">Mujer</option>
            <option value="Otro">Otro</option>
        </select>
        <br><br>

        <button type="submit">Enviar</button>
    </form>
</body>
</html>
