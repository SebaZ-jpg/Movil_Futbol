// EVALUABLE E16 · "Panel de mi colección"
// ============================================================
// LEE PRIMERO docs/E16.md Y EL EJEMPLO X16Panel.kt (ya resuelto).
//
// TU MISIÓN: implementar la función counts de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E16Test y el botón Comprobar):
//   1. Contar cuántos jugadores hay de cada club.
//   2. Sin duplicar: si un jugador aparece dos veces, cuenta UNA (usa IDs).
//   3. Lista vacía: mapa vacío, sin error.
//   4. Devolver el mapa club -> recuento, ordenado alfabéticamente por club.
// PISTA: rows.distinctBy { it.id }.groupingBy { it.club }.eachCount().toSortedMap().
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E16Panel: aquí vive tu solución evaluable.
object E16Panel {
 fun counts(rows: List<Player>): Map<String, Int> = // devuelve un mapa club -> cantidad
  // Sin duplicados por ID, agrupado por club y ordenado alfabéticamente.
  rows.distinctBy { it.id } // quita jugadores repetidos (mismo ID) para no contarlos dos veces
   .groupingBy { it.club } // agrupa por club
   .eachCount() // cuenta cuántos hay en cada grupo
   .toSortedMap() // ordena el mapa por nombre de club (A-Z)
 fun demo(): String = counts(Seed.players).toString() // demo: recuento del catálogo completo
}
