# Global Flavors Hub Android App

Este proyecto es una aplicación Android que utiliza la arquitectura MVVM junto con Dagger Hilt para la inyección de dependencias. La aplicación se conecta a la interfaz GlobalFlavorsHubApiClient a través del repositorio GlobalFlavorsHubRecipesRepository para obtener datos relacionados con recetas globales.
Además el proyecto solo tiene una sola actividad y las demás vistas son fragmentos, esto es para que se tenga una mejor estabilidad del proyecto y que podamos manejar la navegación con NavGraph que es lo que se utilizó para poder navegar entre las vistas.

## Librerías Utilizadas

- [com.localebro:okhttpprofiler:1.0.8](https://github.com/itkacher/OkHttpProfiler)
- [com.airbnb.android:lottie:5.2.0](https://github.com/airbnb/lottie-android)
- [androidx.fragment:fragment-ktx:1.6.2](https://developer.android.com/jetpack/androidx/releases/fragment)
- [androidx.activity:activity-ktx:1.8.2](https://developer.android.com/jetpack/androidx/releases/activity)
- [androidx.lifecycle:lifecycle-livedata-ktx:2.7.0](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [com.github.bumptech.glide:glide:4.14.2](https://github.com/bumptech/glide)
- [com.squareup.retrofit2:retrofit:2.9.0](https://square.github.io/retrofit/)
- [com.squareup.retrofit2:converter-gson:2.9.0](https://github.com/square/retrofit)
- [com.google.dagger:hilt-android:2.48](https://dagger.dev/hilt/)
- [androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [androidx.navigation:navigation-compose:2.7.6](https://developer.android.com/jetpack/androidx/releases/navigation)
- [androidx.navigation:navigation-fragment-ktx:2.7.6](https://developer.android.com/jetpack/androidx/releases/navigation)
- [androidx.navigation:navigation-ui-ktx:2.7.6](https://developer.android.com/jetpack/androidx/releases/navigation)
- [com.google.android.gms:play-services-maps:18.2.0](https://developers.google.com/maps/documentation/android-sdk/overview)
- [com.google.android.gms:play-services-location:21.0.1](https://developers.google.com/android/guides/setup)
- [com.google.android.gms:play-services-places:17.0.0](https://developers.google.com/places/android-sdk/)

## API KEY
Utilizamos una API_KEY de mapas lo puse en crudo en el manifest pero yo recomiendo que se mueva a local.properties y para su compilación si se usa gitHub Actions utilizar los secrets de GitHub

## Configuración del Proyecto

1. Clona este repositorio: `git clone https://github.com/JoseLuisGregorio/GlobalFlavorsHub.git`
2. Abre el proyecto en Android Studio.
3. Sincroniza las dependencias con Gradle.
4. Compila y ejecuta la aplicación en un emulador o dispositivo Android.

## Estructura del Proyecto
Tenemos los principales directorios
- data
  -Dentro de data tenemos los modelos locales y remotos, los dataSources y los repositorios
- di
  - Dentro de di tenemos lo relacionado con la inyección de las dependencias
- domain
  - Dentro de domain tenemos los UseCase que en este caso solo se creó uno para poder filtrar los datos y solo obtener el que coincida con el id solicitado
- ui
  - Dentro de ui tenemos los fragments y adapters
- utils
  - Dentro de utils tenemos funciones de extensión


## Contribuir

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b feature/nueva-caracteristica`.
3. Realiza tus cambios y haz commit: `git commit -m 'Añadir nueva característica'`.
4. Haz push a la rama: `git push origin feature/nueva-caracteristica`.
5. Crea un pull request en GitHub.

## Contacto

Si tienes preguntas o comentarios, no dudes en ponerte en contacto con nosotros en [joseluisga10@outlook.es].