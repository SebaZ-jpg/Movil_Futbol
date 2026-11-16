// EVALUABLE E15 · "Intercambio con confirmación"
// ============================================================
// LEE PRIMERO docs/E15.md Y EL EJEMPLO X15Bluetooth.kt (ya resuelto).
//
// TU MISIÓN: implementar la función decode de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E15Test y el botón Comprobar):
//   1. Leer el mensaje JSON recibido por Bluetooth: {"version":1,"ids":[...]}.
//   2. Validar TODO antes de aceptar: versión correcta, tamaño máximo 8192 bytes,
//      sin IDs repetidos y todos conocidos en este dispositivo.
//   3. Si algo falla: error controlado (el receptor lo rechaza sin romperse).
//   4. Devolver la lista de IDs válidos.
// PISTA: JSONObject(text) para leer; JSONArray para los ids; TextKey no hace falta aquí.
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

import org.json.JSONObject

// Declara el objeto E15Bluetooth: aquí vive tu solución evaluable.
object E15Bluetooth {
 fun decode(text: String, known: Set<Int>): List<Int> { // texto recibido y conjunto de IDs que conocemos; devuelve los IDs válidos
  // Tamaño máximo medido en BYTES UTF-8 (una ñ ocupa 2).
  require(text.toByteArray(Charsets.UTF_8).size <= 8192) { "Mensaje demasiado grande" } // se miden bytes reales, no caracteres, para evitar mensajes enormes
  // Si no es JSON o faltan campos, org.json lanza JSONException (rechazo controlado).
  val root = JSONObject(text) // convierte el texto en objeto JSON; si el texto es basura lanza JSONException
  require(root.getInt("version") == 1) { "Versión no admitida" } // getInt lanza error si falta "version"; solo aceptamos la 1
  val array = root.getJSONArray("ids") // la lista "ids" del mensaje; lanza error si falta
  require(array.length() > 0) { "Lista de IDs vacía" } // no tiene sentido recibir cero IDs
  // Cada elemento debe ser un entero de verdad (no texto ni decimal).
  val ids = (0 until array.length()).map { i -> // recorre las posiciones 0..n-1 y convierte cada una
   val value = array.get(i) // valor crudo en la posición i (puede ser número, texto, etc.)
   require(value is Int) { "ID no entero" } // "is Int" comprueba el tipo: "7" (texto) o 7.5 (decimal) se rechazan
   value // devuelve el valor (ya sabemos que es Int)
  } // fin del map: queda una List<Int>
  require(ids.distinct().size == ids.size) { "IDs repetidos" } // sin repetidos
  require(ids.all { it in known }) { "ID desconocido" } // todos deben existir en el catálogo
  // Mismo orden en que llegaron.
  return ids // se devuelven tal cual llegaron
 }
 fun demo(): String = decode("{\"version\":1,\"ids\":[1,2]}", setOf(1, 2)).toString() // demo: mensaje correcto con los IDs 1 y 2
}

