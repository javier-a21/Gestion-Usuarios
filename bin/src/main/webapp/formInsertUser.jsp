<!DOCTYPE html>
<html>
 <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style.css">
 <body>
<h1>Formulario de Registro de Usuarios</h1>

 <form action="procesarFormulario" method="post">
<label for="nombre">Nombre</label>
<input style="width:90%;" class="nombreForm" type="text" id="nombre" name="nombre" required="required">
<br><br>

<label for="edad">Edad</label>
<input style="width:90%;" type="number" id="edad" name="edad" required="required">
<br><br>

<select  id="sexo" name="sexo" required="required">
 <option selected disabled="disabled">Seleccione..</option>
  <option value="Hombre">Hombre</option>
  <option value="Mujer" >Mujer</option>
  <option value="Otro">Otro</option>
</select>

<br><br>
<button type="submit">Enviar</button>
 <a class="volver" href="<%= request.getContextPath() %>/index.jsp">Volver</a>
</form>
</body>
</html>
