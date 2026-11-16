// EVALUABLE E04 · "Comparar fichas sin inventar datos"
// ============================================================
// LEE PRIMERO docs/E04.md Y EL EJEMPLO X04Comparador.kt (ya resuelto).
//
// TU MISIÓN: implementar la función compare de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E04Test y el botón Comprobar):
//   1. Aceptar solo de 2 a 4 jugadores DISTINTOS (fuera de eso: error).
//   2. Devolver un texto que compare identidad (nombre, club) y posición.
//   3. NUNCA inventar puntuaciones ni un ganador: solo datos reales.
// PISTA: usa require(rows.size in 2..4) y require que los IDs no se repitan.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E04Comparador: aquí vive tu solución evaluable.
object E04Comparador { // objeto con la función compare
 fun compare(rows: List<Player>): String { // recibe los jugadores elegidos, devuelve el texto a mostrar
  // De 2 a 4 jugadores y todos con ID distinto.
  require(rows.size in 2..4) { "Elige de 2 a 4 jugadores" } // el tamaño debe estar entre 2 y 4
  require(rows.map { it.id }.distinct().size == rows.size) { "Hay jugadores repetidos" } // si al quitar IDs repetidos quedan menos, había un jugador repetido

  // Una línea por jugador con datos reales; sin puntuaciones ni ganador.
  return rows.joinToString("\n") { "${it.name} | ${it.club} | ${it.position}" } // joinToString une los textos; "\n" = salto de línea; cada jugador: nombre | club | posición
 }
 fun demo(): String = compare(Seed.players.take(2)) // demo: compara los dos primeros jugadores del catálogo
}
