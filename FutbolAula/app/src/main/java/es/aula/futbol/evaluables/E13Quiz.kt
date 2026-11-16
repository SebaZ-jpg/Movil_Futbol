// EVALUABLE E13 · "Reto de cinco preguntas"
// ============================================================
// LEE PRIMERO docs/E13.md Y EL EJEMPLO X13Quiz.kt (ya resuelto).
//
// TU MISIÓN: implementar la función build de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E13Test y el botón Comprobar):
//   1. Crear `count` preguntas a partir de los jugadores disponibles.
//   2. Cada pregunta: texto, DOS opciones distintas y la respuesta correcta
//      entre ellas (puedes mezclar el orden de las opciones).
//   3. Sin repetir jugador: cada pregunta va de un jugador distinto.
//   4. RECHAZAR: pedir más preguntas que jugadores, o catálogos con IDs repetidos.
// PISTA: la respuesta es el club del jugador; el distractor, el club de otro.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E13Quiz: aquí vive tu solución evaluable.
object E13Quiz {
 fun build(rows: List<Player>, count: Int): List<Question> { // jugadores disponibles y nº de preguntas; devuelve las preguntas
  require(count > 0) { "Pide al menos una pregunta" } // no tiene sentido pedir 0 o negativas
  require(rows.map { it.id }.distinct().size == rows.size) { "Hay IDs repetidos" } // un mismo jugador no puede aparecer dos veces
  require(rows.size >= count) { "No hay jugadores suficientes" } // necesitamos al menos tantos jugadores como preguntas
  val clubs = rows.map { it.club }.distinct() // lista de clubes distintos disponibles
  require(clubs.size >= 2) { "Hacen falta al menos dos clubes" } // sin dos clubes no hay opción incorrecta posible
  // Un jugador distinto por pregunta (rows ya viene mezclado por quien llama).
  return rows.take(count).mapIndexed { i, p -> // take(count) coge los primeros; mapIndexed da cada jugador p con su posición i
   // Distractor: otro club distinto del suyo, rotando para no usar siempre el mismo.
   val others = clubs.filter { it != p.club } // clubes que NO son el del jugador
   val other = others[i % others.size] // elige uno rotando con el resto de la división (i % n)
   // Se alterna el orden de las opciones; la respuesta siempre está entre ellas.
   val options = if (i % 2 == 0) listOf(p.club, other) else listOf(other, p.club) // en preguntas pares la correcta va primero, en impares segunda
   Question(p.id, "¿Dónde juega ${p.name}?", options, p.club) // crea la pregunta: id, texto, opciones y respuesta correcta
  } // fin de mapIndexed: devuelve la lista de preguntas
 }
 fun demo(): String = build(Seed.players, 5).joinToString("\n") { it.text } // demo: 5 preguntas, mostrando solo el texto de cada una
}
