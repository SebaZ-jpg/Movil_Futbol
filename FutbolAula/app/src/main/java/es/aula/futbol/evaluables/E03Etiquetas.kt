// EVALUABLE E03 · "Etiquetas y valoración personal"
// ============================================================
// LEE PRIMERO docs/E03.md Y EL EJEMPLO X03Etiquetas.kt (ya resuelto).
//
// TU MISIÓN: implementar la función mark de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E03Test y el botón Comprobar):
//   1. Limpiar la lista de etiquetas: quitar espacios, vacías y repetidas
//      (aunque cambien acentos o mayúsculas: "Técnica" = "tecnica").
//   2. Admitir COMO MÁXIMO 5 etiquetas de hasta 24 caracteres cada una.
//   3. La valoración solo puede ir de 0 a 5; fuera de rango: error.
//   4. Conservar la nota y el favorito que ya tenía la ficha (usa copy).
// PISTA: original.copy(tags = clean, rating = rating) crea la Mark nueva.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E03Etiquetas: aquí vive tu solución evaluable.
object E03Etiquetas {
 fun mark(original: Mark, tags: List<String>, rating: Int): Mark { // original = lo que ya había guardado; devuelve la Mark nueva

  // 1. Se recortan espacios y se descartan las etiquetas vacías.
  val trimmed = tags.map { it.trim() }.filter { it.isNotEmpty() } // trim quita espacios de los lados; filter tira las que quedan vacías

  // 2. Cada etiqueta, como máximo 24 caracteres.
  require(trimmed.all { it.length <= 24 }) { "Etiqueta de más de 24 caracteres" } // si alguna es demasiado larga, error

  // 3. "Técnica" y " tecnica " son la misma: se quita la repetida (queda la primera).
  val clean = trimmed.distinctBy { TextKey.of(it) } // distinctBy elimina duplicados comparando la versión sin tildes ni mayúsculas

  // 4. Máximo cinco etiquetas únicas.
  require(clean.size <= 5) { "Máximo 5 etiquetas" } // se cuenta DESPUÉS de limpiar, para no castigar repetidas

  // 5. Valoración personal de 0 a 5.
  require(rating in 0..5) { "La valoración va de 0 a 5" } // "0..5" es un rango que incluye ambos extremos

  // 6. copy conserva favorito y nota que ya tenía la ficha.
  return original.copy(tags = clean, rating = rating) // copy crea otra Mark igual cambiando solo etiquetas y valoración
 }
 fun demo(): String = mark(Mark(), listOf("Técnica", " técnica "), 4).toString() // demo: dos etiquetas iguales se quedan en una
}
