// EVALUABLE E05 · "Sorpresa respetando filtros"
// ============================================================
// LEE PRIMERO docs/E05.md Y EL EJEMPLO X05Sensor.kt (ya resuelto).
//
// TU MISIÓN: implementar la función choose de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E05Test y el botón Comprobar):
//   1. Elegir UN jugador de la lista visible usando el índice dado.
//   2. El índice da la VUELTA (módulo): 16 con 16 jugadores vuelve al 1,
//      y -1 devuelve al último. PISTA: el operador % de Kotlin da negativos,
//      usa (((index % n) + n) % n).
//   3. Lista vacía: devolver null (nadie a quien elegir).
//   4. La elección debe salir SOLO de la lista recibida (respeta los filtros).
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E05Sensor: aquí vive tu solución evaluable.
object E05Sensor { // objeto con la función choose
 fun choose(candidates: List<Player>, index: Int): Player? { // "Player?" significa que puede devolver null (nadie)

  // Sin candidatos no hay a quién elegir.
  if (candidates.isEmpty()) return null // lista vacía: devolvemos null y salimos

  // Int.mod devuelve siempre 0..n-1 (también con negativos), equivale a ((i % n) + n) % n.
  return candidates[index.mod(candidates.size)] // mod da el resto sin negativos, así CUALQUIER número (incluso -5) apunta a una posición válida
 }
 fun demo(): String = choose(Seed.players.filter { it.club == "FC Barcelona" }, 0)?.name ?: "Sin candidatos" // demo: primer jugador del Barça; "?." evita fallo si es null y "?:" pone un texto alternativo
}
