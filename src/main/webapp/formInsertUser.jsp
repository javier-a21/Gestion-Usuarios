<html>
<body>
<link rel="stylesheet" type="text/css" href="css/style.css">
<h1>Formulario de Registro de Usuarios</h1>

 <form action="procesarFormulario" method="post">
<label for="nombre">Nombre</label>
<input class="nombreForm" type="text" id="nombre" name="nombre" required="required">
<br><br>

<label for="edad">Edad</label>
<input type="number" id="edad" name="edad" required="required">
<br><br>

<select  id="sexo" name="sexo" required="required">
 <option selected disabled="disabled">Seleccione..</option>
  <option value="Hombre">Hombre</option>
  <option value="Mujer" >Mujer</option>
  <option value="Otro">Otro</option>
</select>

<br><br>
<button type="submit">Enviar</button>
</form>
</body>
</html>
