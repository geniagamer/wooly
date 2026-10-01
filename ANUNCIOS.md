# Wooly: cómo activar los anuncios y cobrar

El juego ya trae los anuncios de AdMob (de Google) programados. Ahora mismo usa **anuncios de prueba**, así que puedes compilarlo y ver cómo funcionan sin riesgo. Para ganar dinero de verdad tienes que cambiar 3 IDs por los tuyos.

## Qué anuncios tiene el juego

| Anuncio | Cuándo sale | Por qué así |
|---|---|---|
| **Con recompensa: Revivir** | En la pantalla final, si el jugador quiere seguir desde donde se cayó (una vez por partida) | El jugador lo elige. Son los que mejor pagan y los que menos molestan. |
| **Con recompensa: ×2 estrellas** | En la pantalla final, para duplicar las estrellas ganadas | Hace que valga la pena ver anuncios para comprar disfraces. |
| **Intersticial (pantalla completa)** | Al tocar "Otra vez", cada 3 partidas y nunca antes de 2 minutos | Gana dinero aunque nadie toque los anuncios con recompensa, sin cansar al jugador. |

No puse banners durante el juego porque tapan la pantalla, pagan muy poco y hacen que la gente desinstale.

## Paso 1. Crea tu cuenta de AdMob

1. Entra a https://admob.google.com con la misma cuenta de Google de tu Play Console.
2. Llena tus datos (país, zona horaria, moneda).

## Paso 2. Registra la app y crea los bloques de anuncios

1. En AdMob: **Apps > Agregar app > Android**. Si todavía no está en la Play Store, di que no está publicada. Ponle de nombre "Wooly".
2. Copia el **ID de la app**. Se ve así: `ca-app-pub-1234567890123456~1234567890` (lleva una **~**).
3. Dentro de la app, ve a **Bloques de anuncios > Agregar bloque** y crea dos:
   - **Bonificado** (Rewarded). Nombre: "Wooly recompensa".
   - **Intersticial**. Nombre: "Wooly entre partidas".
4. Copia el ID de cada bloque. Se ven así: `ca-app-pub-1234567890123456/1234567890` (llevan una **/**).

## Paso 3. Pon tus IDs en el juego

**A)** En `android/app/src/main/res/values/strings.xml` cambia el ID de la app:

```xml
<string name="admob_app_id">TU-ID-DE-APP-CON-~</string>
```

**B)** En `www/index.html` busca `const ADS={` (está cerca de la mitad del archivo) y cambia esto:

```js
testing:false,
rewarded:'TU-ID-DEL-BLOQUE-BONIFICADO',
interstitial:'TU-ID-DEL-BLOQUE-INTERSTICIAL',
```

**C)** Corre `npx cap sync android` y vuelve a compilar.

⚠️ **Nunca toques ni veas a propósito tus propios anuncios reales.** Google lo detecta como fraude y puede cerrar tu cuenta. Para probar en tu celular, deja `testing:true` o agrega tu celular como dispositivo de prueba en AdMob (**Configuración > Dispositivos de prueba**).

## Paso 4. Mensaje de privacidad para Europa (obligatorio)

En AdMob ve a **Privacidad y mensajería > Reglamentos europeos > Crear mensaje** y publícalo. El juego ya está programado para mostrarlo solo a quien lo necesite. Si Google lo pide, en el menú aparece un botón de "Privacidad".

## Paso 5. Lo que cambia en la Play Store

- **¿Contiene anuncios?** Marca **Sí**.
- **Seguridad de los datos:** con anuncios, la app ya **sí recopila datos** (el ID de publicidad del celular, entre otros). Google tiene una guía exacta de qué marcar para AdMob: busca "AdMob Data safety disclosure".
- **Política de privacidad:** debe mencionar AdMob. Usa el texto de `politica-privacidad.md` y publícalo gratis en Google Sites o GitHub Pages. Pon ese enlace en la ficha de la tienda.
- **Público objetivo:** si eliges que el juego es para menores de 13 años, Google aplica reglas especiales a los anuncios. Si quieres incluir niños, avísame y configuro el juego en modo infantil.
- **app-ads.txt (recomendado):** AdMob te pide poner un archivo `app-ads.txt` en el sitio web de desarrollador que registres en Play Console. Puede ser la misma página de tu política de privacidad, si te deja subir archivos (GitHub Pages sí deja). Sin ese archivo pagan menos.

## Paso 6. Cobrar

- En AdMob ve a **Pagos** y llena tus datos fiscales y bancarios.
- Cuando juntes unos 10 USD, Google te manda por correo postal un PIN para verificar tu dirección.
- Google paga una vez al mes, cuando tu saldo pasa el mínimo de pago de tu país (en muchos países son 100 USD).

## Lo que puedes esperar

Lo que ganes depende sobre todo de **cuántas personas juegan** y **de qué país son**. Los países como EE. UU. pagan mucho más por anuncio que Latinoamérica. Con pocos jugadores serán centavos al día. Para ganar algo significativo necesitas miles de jugadores diarios, así que promocionar el juego (videos en TikTok, Reels, Shorts) es tan importante como los anuncios.
