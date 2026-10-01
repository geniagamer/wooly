# Wooly: cómo activar los récords mundiales

El juego ya trae programada la tabla de récords mundial con **Google Play Juegos**. Cuando la actives:

- Al terminar cada partida, la altura del jugador se manda sola a la tabla.
- En el menú y en la pantalla final aparece el botón **🏆 Récords mundiales**, que abre la tabla de Google con los mejores del mundo y de los amigos.
- No hace falta que nadie cree cuentas: Google Play Juegos usa la cuenta de Google del celular.

Mientras no la configures, el botón queda escondido y el juego funciona normal.

> **Importante:** esto solo se puede configurar cuando la app ya está creada en Play Console. Puede ser en prueba interna o cerrada; no tiene que estar publicada para todos.

## Paso 1. Activa Play Juegos en tu app

1. Entra a Play Console y abre **Wooly**.
2. En el menú de la izquierda: **Crecer > Servicios de juegos de Play > Configuración y administración > Configuración**.
3. Elige **No, mi juego no usa las API de Google** (crear un proyecto nuevo) y ponle "Wooly".
4. Copia el **ID del proyecto**. Es un número largo, por ejemplo `123456789012`.

## Paso 2. Crea la credencial de Android

1. En la misma página: **Credenciales > Agregar credencial > Android**.
2. Sigue el botón para crear el **ID de cliente de OAuth** en Google Cloud. Te pedirá la **huella SHA-1** de la llave con la que se firma la app.
3. Esa huella está en Play Console: **Prueba y lanzamiento > Configuración > Integridad de la app > Firma de apps**. Copia el **certificado SHA-1 de la clave de firma de apps**.
4. Guarda la credencial.

## Paso 3. Crea la tabla de récords

1. **Servicios de juegos de Play > Configuración y administración > Tablas de clasificación > Crear tabla**.
2. Nombre: **Altura**. Formato: **Numérico**, sin decimales. Orden: **Las puntuaciones más altas son mejores**. Si te deja poner unidad, pon `m`.
3. Guarda y copia el **ID de la tabla**. Empieza con `CgkI...`.
4. Arriba, toca **Publicar** para publicar los cambios de Play Juegos. Mientras no los publiques, solo funcionan para las cuentas de prueba que agregues en **Verificadores**.

## Paso 4. Pon los IDs en el juego

En `android/app/src/main/res/values/strings.xml` cambia estas dos líneas:

```xml
<string name="game_services_project_id" translatable="false">TU-ID-DE-PROYECTO</string>
<string name="leaderboard_height" translatable="false">TU-ID-DE-TABLA-CgkI...</string>
```

Sube el cambio a GitHub (o dime los IDs y yo los pongo). Se genera una versión nueva de la app.

## Ojo con el APK de prueba

El APK que arma GitHub para probar se firma con una llave temporal distinta a la de la Play Store, así que **la tabla de récords no va a funcionar en ese APK**. Funciona en la versión que la gente baja desde la Play Store, incluida la prueba cerrada.
