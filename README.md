# pruebaUsuarios
Aplicación backend que expone servicios API-REST para creación de usuarios con generación de token para acceder a los metodos privados del sistema.
La apicación se construyó con una arquitectura hexagonal, respetando el principio de separación entre las capas de Dominio, de Aplicación y de Infraestructura. Del mismo modo se aplicaron los principios de Segregación de Interfaces, Inversión de Dependencias, de Sustitución de Liskov y de Abierto-Cerrado.
Del mismo modo, cuenta con el archivo swager de la documentación.
## Métodos Publicos
La aplicación tiene dos métodos que se pueden consumir sin autorización de token.
### /v1/api/generateToken
Este servicio permite la generación de un nuevo Token para un usuario especifico. Se accede atravez de una petición Post y en el curpo de la petición se debe agregar los datos de de email y password del usuario.
Ejemplo { "email": "oli@correro.com", "password": "ragnar" }.
### /v1/api/createUser
Este servicio permite la creación de un usuario y de respuesta recibe entre otros datos el Token que se generó para acceso a los metodos seguros.

## Métodos Privados
La aplicación tiene un método seguro que se consume agregando el Token de autorización Bearer.
### /v1/api/{id}
Este servicio permite la consulta de un usuarion a partir de su ID.

## Datos
La aplicación usa base de datos H2 y se insertan datos de prueba al momento de desplegar la aplicación.

## Uso
Para consumir los servicios REST se debe usar la colección Postman "smartJobCollection.postman_collection.json". 
En la cual se encuentran los servicios públicos y privados mencionados anteriormente.

## Documentación
En la ruta /generated/swagger-ui se encuantra el archivo swagger.yaml con la documentación de las APIs

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.
