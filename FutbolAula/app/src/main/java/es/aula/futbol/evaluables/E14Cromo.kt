// EVALUABLE E14 · "Diseñar y compartir cromo"
// ============================================================
// LEE PRIMERO docs/E14.md Y EL EJEMPLO X14Cromo.kt (ya resuelto).
//
// TU MISIÓN: implementar la función title de abajo.
// QUÉ DEBE HACER (el contrato, lo comprueba E14Test y el botón Comprobar):
//   1. Limpiar el título: quitar espacios de los extremos (trim).
//   2. RECHAZAR: título vacío, de más de 28 caracteres o color fuera de 0..2.
//   3. Devolver el título limpio, listo para dibujarlo en el cromo.
// PISTA: require(raw.trim().length in 1..28) y require(color in 0..2).
// Sitúa el archivo en el paquete común; la carpeta organiza el material didáctico.
package es.aula.futbol

import org.json.JSONObject

// Declara el objeto E14Cromo: aquí vive tu solución evaluable.
object E14Cromo {
    fun title(raw: String, color: Int): String { // texto escrito por el usuario y color elegido; devuelve el título limpio
        val clean = raw.trim() // quita espacios de los lados
        require(clean.length in 1..28) { "El título debe tener de 1 a 28 caracteres" } // ni vacío ni demasiado largo para que quepa en el cromo
        require(color in 0..2) { "Color: 0 azul, 1 violeta, 2 verde" } // solo hay tres colores
        return clean // si todo es válido, se devuelve el título ya recortado
    }
    fun demo(): String = title("Mi jugador favorito", 1) // demo: título válido con color violeta
}