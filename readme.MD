# prueba tecnica ruben barrera

## Consideraciones
- Se utiliza openjdk 11
- La aplicación se configuró con Maven. No se utilizó una arquitectura hexagonal, ya que no era un requisito en el pedido. Se optó por una estructura común.
   - controller: En esta carpeta se encuentran los controladores de la aplicación, que se encargan de manejar las solicitudes HTTP y las respuestas. Los controladores definen las rutas y los puntos de entrada de la aplicación web. Aquí se establecen las interacciones con el cliente y se traducen las solicitudes en acciones específicas en la lógica de la aplicación.
   - entity: En la carpeta "entity" se definen las clases que representan las entidades del dominio. Estas clases suelen estar relacionadas con la base de datos y pueden utilizarse para mapear objetos a tablas de la base de datos o para definir las estructuras de datos principales de la aplicación.
   - model: La carpeta "model" se utiliza para definir objetos de modelo que se utilizan para transferir datos entre diferentes capas de la aplicación. Estos objetos suelen denominarse DTOs (Data Transfer Objects) y son útiles para encapsular datos y transferirlos de manera eficiente entre el controlador y la lógica de negocio, por ejemplo.
   - repository: En la carpeta "repository" se encuentran los repositorios, que se utilizan para interactuar con la capa de persistencia de datos, como bases de datos. Aquí se definen las consultas y operaciones de lectura y escritura en la base de datos. Los repositorios permiten separar la lógica de acceso a datos de la lógica de negocio.
   - converters: La carpeta "converters" podría albergar clases o componentes encargados de realizar conversiones de datos o transformaciones entre diferentes formatos.
   - exceptions: La carpeta "exceptions" se utiliza para almacenar clases de excepción personalizadas.
- Se realizan test unitarios con junit
- se documenta con openApi libreria que tiene swagger que se puede visitar en http://localhost:8095/self-service/user-crud/swagger-ui/index.html#
- La aplicación usa el puerto 8095
- adicional agregue .github/workflows/build.yml por lo cual si lo suben a github correra el actions llamado build donde hara un build y lo test unitarios con esto pude ver que cada commit que hiciera mantuviera la integridad del microservicio
- diagramas se encuentran en la caarpeta resource 
   - formato PNG  https://github.com/ifyum/bci-user-crud-api/blob/main/src/main/resources/diagram.png
   - formato SVG  https://github.com/ifyum/bci-user-crud-api/blob/main/src/main/resources/diagrama.svg
   - formato PUML https://github.com/ifyum/bci-user-crud-api/blob/main/src/main/resources/mi-diagrama.puml
## Instrucciones
- En la carpeta resources se encuentra el archivo .sql para la creación del modelo "db_creation.sql".
- La aplicación se configuró para utilizar h2 en formato embebido file y la ubicacion seria "C:\bcitest"
- La aplicación se puedes ejecutar desde Intellij o cualquier ide compatible con spring boot, en su defecto desde consola se puede usar el comando "mvnw spring-boot:run" en la raiz del proyecto.
- Para detener la aplicación en ejecución por consola, solo basta apretar "ctrl+c".

![Mi imagen](https://raw.githubusercontent.com/ifyum/bci-user-crud-api/main/src/main/resources/diagram.png)

