// EVALUABLE E09 · "Once inicial 4-3-3"
// ============================================================
// LEE PRIMERO docs/E09.md Y EL EJEMPLO X09Alineacion.kt (ya resuelto).
//
// TU MISIÓN: implementar la función validate de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E09Test y el botón Comprobar):
//   1. Decidir si una lista de jugadores ES un once 4-3-3 válido.
//   2. Reglas: exactamente 11 jugadores, todos distintos, con 1 portero,
//      4 defensas, 3 centrocampistas y 3 delanteros.
//   3. Cualquier otra cosa (menos jugadores, repetidos, otra distribución)
//      devuelve false. NUNCA lanza error: solo dice sí o no.
// PISTA: agrupa por posición con groupBy { it.position } y cuenta cada grupo.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E09Alineacion: aquí vive tu solución evaluable.
object E09Alineacion {
 // Reparto exigido: 1 portero, 4 defensas, 3 centrocampistas, 3 delanteros.
 private val formation = mapOf("Portero" to 1, "Defensa" to 4, "Centrocampista" to 3, "Delantero" to 3) // mapa posición -> cuántos hacen falta

 fun validate(rows: List<Player>): Boolean { // devuelve true/false, nunca lanza error

  // Once exactos y todos distintos (por ID). Nunca lanza error: solo sí o no.
  if (rows.size != 11) return false // si no son 11, no vale
  if (rows.map { it.id }.distinct().size != 11) return false // si hay IDs repetidos (mismo jugador dos veces), no vale

  // Cuenta por posición y compara con el 4-3-3 (una posición rara también falla).
  return rows.groupingBy { it.position }.eachCount() == formation // agrupa por posición y cuenta cuántos hay en cada una; "==" compara los dos mapas completos
 }
 fun demo(): String = validate(Seed.players.take(11)).toString() // demo: prueba con los 11 primeros del catálogo
}
