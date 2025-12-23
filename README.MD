# PM Slider - Carrusel de Imágenes con Jetpack Compose
Rama main: versión obsoleta de código que usa las librerias de google accompanist, las cuales estan obsoletas.
Rama updated: versión más reciente de código, que usa las librerias propias de Android.

Este proyecto es una aplicación de Android sencilla desarrollada con **Jetpack Compose** para demostrar cómo implementar un carrusel de imágenes interactivo (`HorizontalPager`). La aplicación muestra una serie de imágenes en un deslizador horizontal con indicadores de página que se actualizan en tiempo real.


## Funcionalidades

- **Carrusel de Imágenes**: Utiliza el componente `HorizontalPager` de `androidx.compose.foundation` para mostrar imágenes de forma paginada.
- **Indicadores de Página**: Muestra una fila de puntos debajo del carrusel, donde el punto correspondiente a la página actual se resalta para dar feedback visual al usuario.
- **Carga de Datos Local**: Las imágenes se cargan desde los recursos locales (`drawable`) a través de una clase `Datasource`.
- **Diseño Moderno**: La interfaz de usuario está construida siguiendo las guías de diseño de Material 3.

## Arquitectura y Componentes Clave

La aplicación tiene una arquitectura simple centrada en componentes de Jetpack Compose.

1.  **`MainActivity.kt`**: Es el punto de entrada de la aplicación. Configura la UI principal usando `setContent` y llama al componente `ImageCarousel`.

2.  **`ImageCarousel` (`@Composable`)**: Este es el componente principal que contiene toda la lógica del carrusel.
    -   **`rememberPagerState`**: Crea y recuerda el estado del `Pager` (`PagerState`). Este estado es crucial, ya que almacena información como la página actual, el número total de páginas y el desplazamiento.
    -   **`HorizontalPager`**: Es el contenedor que permite deslizar horizontalmente entre sus páginas (en este caso, las imágenes). Está vinculado al `pagerState` para funcionar correctamente.
    -   **Indicador de Página Personalizado**: Se implementa usando un `Row` que se repite (`repeat`) tantas veces como páginas hay. Dentro del bucle, un `Box` con forma de círculo (`CircleShape`) cambia de color (`MaterialTheme.colorScheme.primary`) si su índice coincide con la `pagerState.currentPage`.

### Flujo de la Interacción

1.  **Inicialización**: `ImageCarousel` se inicializa. `rememberPagerState` establece el número de páginas basándose en la lista de imágenes recibida.
2.  **Renderizado**: `HorizontalPager` muestra la primera imagen. El `Row` de indicadores dibuja un punto resaltado para la primera posición y puntos tenues para las demás.
3.  **Interacción del Usuario**: El usuario desliza el dedo hacia la izquierda o la derecha.
4.  **Actualización de Estado**: El `HorizontalPager` actualiza automáticamente el `pagerState.currentPage`.
5.  **Recomposición**: Como el indicador de página depende de `pagerState.currentPage`, Jetpack Compose lo "recompone" (vuelve a dibujar) de forma inteligente, cambiando qué punto está resaltado para reflejar la nueva página actual.

## Dependencias Relevantes

-   `androidx.compose.foundation:foundation`: Proporciona los componentes `HorizontalPager` y `rememberPagerState`.
-   `androidx.compose.material3:material3`: Para los componentes de UI de Material Design como `Card`, `Scaffold`, etc.
-   `androidx.activity:activity-compose`: Integración de Jetpack Compose con `ComponentActivity`.

Este proyecto sirve como un excelente ejemplo base para implementar carruseles, *onboardings* o galerías de imágenes en cualquier aplicación de Jetpack Compose, utilizando las APIs más modernas y estables proporcionadas por Google.

