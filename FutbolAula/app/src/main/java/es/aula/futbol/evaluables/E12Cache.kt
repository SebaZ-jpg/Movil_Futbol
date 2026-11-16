// EVALUABLE E12 · "Datos guardados sin conexión"
// ============================================================
// LEE PRIMERO docs/E12.md Y EL EJEMPLO X12Cache.kt (ya resuelto).
//
// TU MISIÓN: implementar la función resolve de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E12Test y el botón Comprobar):
//   1. Decidir qué catálogo mostrar y con qué mensaje.
//   2. Si la descarga funcionó (remote != null y sin error): mostrar el remoto,
//      stale=false, mensaje "Actualizado".
//   3. Si falló: mostrar los datos GUARDADOS, stale=true (están caducados),
//      con un mensaje claro. Nunca dejar la app sin datos si había guardados.
//   4. Sin red y sin nada guardado: lista vacía con stale=true.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E12Cache: aquí vive tu solución evaluable.
object E12Cache {
    fun resolve(remote: List<Player>?, saved: List<Player>, error: String?): CacheResult { // remote = descarga (null si no hubo), saved = copia local, error = motivo del fallo (null si no hubo)
        // Descarga correcta: se usa, aunque venga vacía (vacío válido ≠ error).
        if (remote != null && error == null) return CacheResult(remote, false, "Actualizado") // hay datos y no hay error: no están caducados (false)
        // Falló: se explica el motivo y se enseña lo guardado, marcado como caducado.
        val reason = error ?: "descarga no disponible" // "?:" usa el texto de la derecha si error es null
        return if (saved.isNotEmpty()) // si hay copia guardada...
            CacheResult(saved, true, "Sin conexión ($reason): datos guardados, pueden estar caducados") // ...se muestra, marcada como caducada (true)
        else // si no hay nada guardado...
            CacheResult(emptyList(), true, "Sin conexión ($reason) y sin datos guardados todavía") // ...lista vacía y mensaje explicándolo
    }
    fun demo(): String = resolve(null, Seed.players, "sin red").message // demo: simula fallo de red y muestra el mensaje
}
