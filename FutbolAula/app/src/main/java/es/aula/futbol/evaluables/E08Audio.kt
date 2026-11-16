// EVALUABLE E08 · "Playlist y comentarios de voz"
// ============================================================
// LEE PRIMERO docs/E08.md Y EL EJEMPLO X08Audio.kt (ya resuelto).
//
// TU MISIÓN: implementar la función next de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E08Test y el botón Comprobar):
//   1. Calcular el índice anterior o siguiente en una lista de audios.
//   2. Dirección solo -1 (anterior) o 1 (siguiente); otra cosa: error.
//   3. El índice actual debe existir en la lista; si no: error.
//   4. El movimiento es CIRCULAR: desde el primero hacia atrás se va al
//      último, y desde el último hacia adelante vuelve al primero.
//   5. Lista vacía: devolver null.
// PISTA: el módulo con cuidado con negativos, igual que en E05.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E08Audio: aquí vive tu solución evaluable.
object E08Audio {
 fun next(current: Int, size: Int, direction: Int): Int? { // posición actual, nº de audios y sentido (-1 anterior, 1 siguiente); null si no hay audios
  require(direction == -1 || direction == 1) { "Dirección: -1 o 1" } // solo se admite retroceder o avanzar
  require(size >= 0) { "Tamaño no válido" } // un tamaño negativo no tiene sentido

  // Sin audios no hay índice al que ir.
  if (size == 0) return null // lista vacía: devolvemos null

  // El índice actual debe existir en la lista.
  require(current in 0 until size) { "Índice fuera de la lista" } // "0 until size" va de 0 hasta size-1 (el último no se incluye)

  // Recorrido circular (mod nunca devuelve negativos).
  return (current + direction).mod(size) // suma el sentido y da la vuelta: en el último, +1 vuelve a 0; en el 0, -1 va al último
 }
 fun demo(): String = next(0, 3, -1).toString() // demo: desde el primero de 3 audios, retroceder lleva al último (2)
}
