# Wooly: cómo subirlo a la Play Store

El juego ya está listo como app de Android (hecha con Capacitor). Solo falta compilarla en tu computadora y subirla.

## Qué hay en esta carpeta

- `www/index.html` es el juego completo. Si quieres cambiar algo del juego, se cambia aquí.
- `android/` es el proyecto de Android Studio. Ya tiene el ícono, la pantalla de inicio y la orientación vertical.
- `ANUNCIOS.md` explica cómo activar los anuncios con tu cuenta de AdMob y cobrar.
- `RECORDS.md` explica cómo activar la tabla de récords mundial con Google Play Juegos.
- `politica-privacidad.md` es una plantilla de política de privacidad (español e inglés).
- `textos-tienda.md` tiene el título y las descripciones de la tienda en 8 idiomas.
- `assets/` tiene las imágenes para la ficha de la tienda: ícono de 512x512, gráfico destacado de 1024x500 y capturas.

## 1. Instala esto (una sola vez)

1. Node.js (versión LTS): https://nodejs.org
2. Android Studio: https://developer.android.com/studio

## 2. Abre el proyecto

En una terminal, dentro de esta carpeta:

```
npm install
npx cap sync android
npx cap open android
```

Se abre Android Studio. Espera a que termine "Gradle sync" (la primera vez tarda varios minutos).

## 3. Pruébalo en tu celular

1. En tu celular: Ajustes > Acerca del teléfono > toca 7 veces "Número de compilación". Luego activa "Depuración USB" en Opciones de desarrollador.
2. Conecta el celular por USB y presiona el botón verde ▶ (Run) en Android Studio.

Si cambias `www/index.html`, vuelve a correr `npx cap sync android` antes de darle Run.

## 4. Cambia el nombre del paquete (antes de subirlo)

El ID de la app es `com.borregoalcielo.juego`. Cuando la subas por primera vez, ese ID ya no se puede cambiar. Si quieres otro (por ejemplo `com.tunombre.borrego`), cámbialo en:
- `capacitor.config.json` → `appId`
- `android/app/build.gradle` → `applicationId` y `namespace`

## 5. Genera el archivo para la tienda (.aab)

1. En Android Studio: Build > Generate Signed App Bundle or APK > Android App Bundle.
2. Crea una llave nueva ("Create new…"). Guarda el archivo `.jks` y las contraseñas en un lugar seguro: las vas a necesitar para cada actualización.
3. Elige "release". El archivo queda en `android/app/release/app-release.aab`.

## 6. Súbelo a Google Play

1. Crea tu cuenta en https://play.google.com/console (pago único de 25 USD).
2. "Crear app" → nombre "Wooly", tipo Juego, gratis.
3. Llena la ficha de la tienda con las imágenes de `assets/`.
4. Política de privacidad: la tienda pide un enlace. Usa la plantilla de `politica-privacidad.md`.
5. Anuncios y seguridad de los datos: el juego tiene anuncios de AdMob. Sigue el paso 5 de `ANUNCIOS.md` para saber qué marcar.
6. Clasificación de contenido y público objetivo: si eliges niños menores de 13, Google aplica reglas extra de la política de Familias.
7. Sube el `.aab` en una prueba y luego pásalo a Producción.

Importante: las cuentas personales nuevas deben hacer primero una prueba cerrada con al menos 12 personas durante 14 días antes de poder publicar en Producción. Revisa en Play Console los requisitos actuales, porque Google los cambia a veces.

## Actualizaciones

Cada vez que subas una versión nueva, sube el número `versionCode` en `android/app/build.gradle` (1 → 2 → 3…) y el `versionName` ("1.0" → "1.1").
