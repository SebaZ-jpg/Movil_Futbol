// EVALUABLE E11 · "Copia de seguridad validada"
// ============================================================
// LEE PRIMERO docs/E11.md Y EL EJEMPLO X11Copia.kt (ya resuelto).
//
// TU MISIÓN: implementar la función restore de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E11Test y el botón Comprobar):
//   1. Leer el TEXTO de una copia en JSON (formato de Wire.encode).
//   2. Quedarse solo con los IDs conocidos: si la copia habla de un jugador
//      que no existe en este móvil, se ignora.
//   3. Si el texto está corrupto o no es JSON: error controlado (nada se rompe).
//   4. Devolver un mapa ID -> Mark SOLO con favorito y nota (la copia v1
//      no incluye etiquetas ni valoración).
// PISTA: Wire.decode(text, known) ya valida el formato; aprovéchalo.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E11Copia: aquí vive tu solución evaluable.
object E11Copia {
 fun restore(current: Map<Int, Mark>, text: String, known: Set<Int>): Map<Int, Mark> { // lo guardado ahora (ID -> Mark), el texto de la copia y los IDs que existen
  // Wire.decode valida TODO antes de devolver nada (versión, tipos, duplicados, tamaño)
  // y descarta IDs desconocidos. Si el texto no es JSON, lanza error y no se escribe nada.
  val fromBackup = Wire.decode(text, known) // convierte el texto JSON en un mapa ID -> Mark; si algo está mal, lanza excepción y aquí se corta
  // Lo actual se conserva; la copia sobrescribe solo los IDs que trae (favorito y nota).
  return current + fromBackup // el + entre mapas junta los dos; si un ID está en ambos, gana el de la copia
 }
 fun demo(): String = restore(emptyMap(), Wire.encode(mapOf(1 to Mark(true, "Nota"))), setOf(1)).toString() // demo: crea una copia con un favorito, la restaura sobre un mapa vacío
}
