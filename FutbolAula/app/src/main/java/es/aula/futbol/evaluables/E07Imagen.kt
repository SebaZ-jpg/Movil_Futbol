// EVALUABLE E07 · "Imagen persistente por jugador"
// ============================================================
// LEE PRIMERO docs/E07.md Y EL EJEMPLO X07Imagen.kt (ya resuelto).
//
// TU MISIÓN: implementar la función filename de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E07Test y el botón Comprobar):
//   1. Construir el nombre de archivo de la imagen de un jugador.
//   2. Formato EXACTO: "jugador_<ID>.<extension>" con ID positivo.
//   3. Solo admitir png o jpg; cualquier otra cosa (incluidas rutas con ../
//      o barras) debe RECHAZARSE: el nombre nunca puede manipular la ruta.
// PISTA: require(extension in setOf("png","jpg")) y return "jugador_$id.$extension".
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E07Imagen: aquí vive tu solución evaluable.
object E07Imagen { // objeto con la función filename
 fun filename(id: Int, extension: String): String { // id del jugador y extensión; devuelve el nombre del archivo
  require(id > 0) { "El ID debe ser positivo" } // los IDs son números positivos

  // Lista cerrada: así "../x" o "a/b" nunca llegan a formar parte de la ruta.
  require(extension in setOf("png", "jpg")) { "Solo png o jpg" } // solo dos extensiones permitidas; cualquier otra cosa (incluida una ruta rara) se rechaza
  return "jugador_$id.$extension" // plantilla de texto: $id y $extension se sustituyen por sus valores, p. ej. jugador_7.png
 }
 fun demo(): String = filename(7, "png") // demo: nombre para el jugador 7
}
