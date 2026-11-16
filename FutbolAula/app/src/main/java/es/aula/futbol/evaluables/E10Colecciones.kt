// EVALUABLE E10 · "Colecciones múltiples"
// ============================================================
// LEE PRIMERO docs/E10.md Y EL EJEMPLO X10Colecciones.kt (ya resuelto).
//
// TU MISIÓN: implementar la función add de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E10Test y el botón Comprobar):
//   1. Añadir un nombre de colección NUEVO a la lista existente.
//   2. Limpiar el nombre: quitar espacios de los extremos (trim).
//   3. RECHAZAR: nombres en blanco, o repetidos aunque cambien acentos o
//      mayúsculas ("Técnica" y "tecnica" son el mismo nombre: usa TextKey.of).
//   4. Devolver la lista completa de nombres, incluido el nuevo al final.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E10Colecciones: aquí vive tu solución evaluable.
object E10Colecciones {
 fun add(names: List<String>, name: String): List<String> { // nombres existentes + nombre nuevo; devuelve la lista con el nuevo al final
  val clean = name.trim() // quita espacios de los lados

  // De 1 a 40 caracteres (un nombre en blanco queda con 0 y se rechaza).
  require(clean.length in 1..40) { "El nombre debe tener de 1 a 40 caracteres" } // rango de longitud permitido

  // Repetido aunque cambien tildes o mayúsculas.
  require(names.none { TextKey.of(it) == TextKey.of(clean) }) { "Ya existe una colección con ese nombre" } // compara versiones normalizadas: "Promesas" = "promesás"

  // Lista previa + nombre nuevo al final (la pantalla usa .last()).
  return names + clean // lista nueva con el nombre añadido al final
 }
 fun demo(): String = add(listOf("Promesas"), "Mi once").joinToString() // demo: añade "Mi once" a la lista y la muestra
}
