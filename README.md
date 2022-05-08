# YelpApp

Descripción general: 
El objetivo de esta aplicación es mostrar los negocios cercanos de acuerdo a la búsqueda que se esté realizando. Por ejemplo, si en el buscador se escribe “Pizza” se mostrará una lista de negocios que vendan pizzas. 
En esta lista se mostrará los ítems con la foto, nombre y categoría. Al hacer click en algún ítem se pasará a otra vista con información a detalle, de igual manera la ubicación del negocio y la de usted en el mapa.

Para abordar este proyecto primero se hizo el registro en la página de yelp con la finalidad de obtener un api key que servirían como método de autenticación para acceder a la información,
seguidamente se utilizó la herramienta Postman para visualizar de mejor manera la respuesta de dicha petición.  

Se utilizó Google Cloud Platform para registrar el proyecto, habilitar el sdk de google maps, obtener las credenciales del api key y después integrarlo a la aplicación.
cabe mencionar que para el desarrollo completo de la aplicación se utilizó lo siguiente:

-Kotlin
-Patron de arquitectura MVVM 
-Coorrutinas
-Retrofit (header interceptors)
-Data binding y View Bindig
-Navigation component (safe args, parcelable)
-Gson
-Dagger hilt
-Easy permissions
-Google maps

Consideraciones:
 Es importante activar el gps del dispositivo antes de entrar a la aplicación, al realizar la primera búsqueda acepte el permiso para poder tomar las coordenadas, si no se encuentra algún negocio relacionado realice otra búsqueda.
Para ver su ubicación en el mapa, presione la ruedita del gps que se encuentra en la parte superior derecha, este lo redirigirá a su ubicación actual.

Versión mínima de Android 21(Lollipop) 
