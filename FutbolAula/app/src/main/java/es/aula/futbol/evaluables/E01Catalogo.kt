// EVALUABLE E01 · "Más equipos reales"
// ============================================================
// LEE PRIMERO docs/E01.md Y EL EJEMPLO X01Catalogo.kt (ya resuelto).
//
// TU MISIÓN: implementar la función addTeam de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E01Test y el botón Comprobar):
//   1. Recibir el catálogo actual y un LOTE nuevo (un solo club, 5+ jugadores).
//   2. Devolver el catálogo actualizado (lista actual + lote).
//   3. RECHAZAR con error: lote vacío, dos clubes mezclados, ID repetido
//      o el mismo jugador duplicado con otro ID.
// REGLAS: no cambies la firma ni borres el TODO hasta terminar.
// El botón "Comprobar E01" de Academia te dirá exactamente qué caso falla.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E01Catalogo: aquí vive tu solución evaluable.
object E01Catalogo { // "object" = una única instancia; se usa como E01Catalogo.addTeam(...)
 // Lista cerrada de posiciones válidas. "setOf" crea un conjunto: buscar en él es rápido y no admite repetidos.
 private val positions = setOf("Portero", "Defensa", "Centrocampista", "Delantero") // private: solo se usa dentro de este objeto

 fun addTeam(current: List<Player>, incoming: List<Player>): List<Player> { // current = catálogo actual, incoming = lote nuevo; devuelve el catálogo resultante
  // 1. Un lote vacío no aporta nada: se rechaza.
  require(incoming.isNotEmpty()) { "El lote está vacío" } // require(condición){mensaje}: si la condición es falsa lanza IllegalArgumentException con ese mensaje

  // 2. Un lote es de UN solo club.
  require(incoming.map { it.club.trim() }.distinct().size == 1) { "Un lote solo puede tener un club" } // map saca el club de cada jugador, trim quita espacios, distinct quita repetidos; debe quedar exactamente 1

  // 3. Cada ficha debe ser publicable: nombre, club, posición conocida y fuente HTTPS.
  require(incoming.all { it.name.isNotBlank() && it.club.isNotBlank() }) { "Nombre y club obligatorios" } // all = se cumple para TODOS; isNotBlank = no vacío ni solo espacios
  require(incoming.all { it.position in positions }) { "Posición no admitida" } // "in" comprueba que la posición esté en el conjunto permitido
  require(incoming.all { it.source.startsWith("https://") }) { "La fuente debe ser HTTPS" } // la fuente de la foto/dato tiene que ser segura (https, no http)

  // 4. IDs: ni repetidos dentro del lote ni ya existentes en el catálogo.
  require(incoming.map { it.id }.distinct().size == incoming.size) { "IDs repetidos en el lote" } // si al quitar repetidos quedan menos IDs, había alguno repetido
  val usedIds = current.map { it.id }.toSet() // todos los IDs que ya existen, como conjunto
  require(incoming.none { it.id in usedIds }) { "Hay un ID que ya existe" } // none = ninguno cumple la condición

  // 5. Mismo jugador con otro ID: se compara (nombre, club) sin tildes ni mayúsculas.
  fun key(p: Player) = TextKey.of(p.name) to TextKey.of(p.club) // función local: crea un Pair(nombre normalizado, club normalizado); TextKey.of quita tildes y pasa a minúsculas
  val existing = current.map { key(it) }.toSet() // claves de los jugadores que ya estaban
  val batchKeys = incoming.map { key(it) } // claves de los jugadores del lote nuevo
  require(batchKeys.distinct().size == batchKeys.size) { "Jugador repetido en el lote" } // dos fichas del mismo jugador dentro del lote
  require(batchKeys.none { it in existing }) { "Ese jugador ya está en el catálogo" } // un jugador del lote que ya existía con otro ID

  // 6. Todo correcto: lista nueva (no se modifica la original).
  return current + incoming // el operador + en listas crea una lista NUEVA con todos los elementos
 }
 fun demo(): String = addTeam(Seed.players, Seed.extra).size.toString() // demo de la pantalla Academia: añade el lote de ejemplo y muestra cuántos jugadores quedan
}
