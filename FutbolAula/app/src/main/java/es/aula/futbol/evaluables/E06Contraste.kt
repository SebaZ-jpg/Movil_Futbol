// EVALUABLE E06 · "Filtro de contraste reversible"
// ============================================================
// LEE PRIMERO docs/E06.md Y EL EJEMPLO X06Contraste.kt (ya resuelto).
//
// TU MISIÓN: implementar la función parameters de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E06Test y el botón Comprobar):
//   1. Recibir un valor de contraste de 0 a 3 (fuera de rango o NaN: error).
//   2. Devolver el PAR (escala, desplazamiento) que usa la matriz de color.
//   3. Las reglas fijas: escala = valor; desplazamiento = 128*(1-valor).
//      Así contraste 1 no cambia nada (offset 0) y contraste 0 empuja a 128.
// PISTA: con valor=1f el offset debe ser 0f; con valor=0f debe ser 128f.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

// Declara el objeto E06Contraste: aquí vive tu solución evaluable.
object E06Contraste {
 fun parameters(value: Float): Pair<Float, Float> { // value = contraste elegido; devuelve (escala, desplazamiento)

  // NaN no pertenece a ningún rango; el resto debe estar entre 0 y 3.
  require(!value.isNaN() && value in 0f..3f) { "El contraste va de 0 a 3" } // isNaN = "no es un número"; el rango 0f..3f son decimales de 0 a 3

  // Escala = valor; desplazamiento = 128·(1 - valor): con 1 no cambia nada.
  return value to 128f * (1f - value) // "a to b" crea un Pair; con value=1 el desplazamiento es 0 y la imagen queda igual (por eso es reversible)
 }
 fun demo(): String = parameters(1.4f).toString() // demo: contraste 1.4
}
