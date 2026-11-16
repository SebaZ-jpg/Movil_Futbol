// EVALUABLE E02 · "Buscar, filtrar y ordenar"
// ============================================================
// LEE PRIMERO docs/E02.md Y EL EJEMPLO X02Filtros.kt (ya resuelto).
//
// TU MISIÓN: implementar la función filter de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E02Test y el botón Comprobar):
//   1. Filtrar a la vez por: texto buscado, club, posición y favoritos.
//   2. El texto busca SIN importar acentos ni mayúsculas (usa TextKey.of).
//   3. Ordenar por nombre: ascendente o descendente según el parámetro.
// PISTA: encadena filter { ... } y luego sortedBy { ... }; si toca descendente,
// da la vuelta al resultado con .reversed().
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.

package es.aula.futbol

// Declara el objeto E02Filtros: aquí vive tu solución evaluable.
object E02Filtros {
    fun filter(rows: List<Player>, query: String, club: String, position: String, favorites: Set<Int>, onlyFavorites: Boolean, descending: Boolean): List<Player> { // parámetros = lista completa + cada filtro; devuelve la lista filtrada

        // Texto buscado normalizado una sola vez (sin tildes ni mayúsculas).
        val q = TextKey.of(query) // así "mbappe" encuentra "Mbappé"

        // Todos los criterios se cumplen A LA VEZ; vacío significa "todos".
        val found = rows.filter { // filter se queda solo con los jugadores para los que el bloque da true
            (q.isEmpty() || TextKey.of(it.name).contains(q)) && // sin texto = pasa todo; con texto, el nombre normalizado debe contenerlo
                    (club.isEmpty() || it.club == club) && // sin club elegido = pasa todo; si no, el club debe coincidir exacto
                    (position.isEmpty() || it.position == position) && // igual con la posición
                    (!onlyFavorites || it.id in favorites) // si "solo favoritos" está activo, el ID debe estar entre los favoritos
        } // fin del filter; "&&" exige que se cumplan las cuatro condiciones

        // Orden por nombre sin tildes (así "Éder" queda entre D y F); si toca, se invierte.
        val ordered = found.sortedBy { TextKey.of(it.name) } // sortedBy ordena por el criterio dado (nombre normalizado), de A a Z
        return if (descending) ordered.reversed() else ordered // si piden descendente se da la vuelta a la lista
    }
    fun demo(): String = filter(Seed.players, "", "FC Barcelona", "Delantero", emptySet(), false, false).joinToString { it.name } // demo: delanteros del Barça, mostrando solo los nombres separados por coma
}
