Challenge de la preaceleración de Alkemy.

Para Ejecutar se debe implementar los siguientes cambios en "application.properties"

spring.datasource.url=jdbc:mysql:<URL BASE DE DATOS>?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=USERNAME BASE DE DATOS
spring.datasource.password=PASSWORD BASE DE DATOS

spring.sendgrid.api-key=API KEY DE SENDGRID
spring.sendgrid.fromEmail=EMAIL DESDE EL CUAL VA A MANDAR EL MENSAJE DE BIENVENIDA.

