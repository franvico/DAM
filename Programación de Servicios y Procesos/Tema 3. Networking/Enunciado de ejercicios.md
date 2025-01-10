# Ejercicios de Networking

## Ejercicio 1

- Crear un sistema cliente servidor.
- El cliente se conecta al servidor y puede hacer dos acciones:
    - Crear un fichero: con este comando el servidor crea un fichero con el nombre indicado por el cliente que contiene un timestamp del momento en que se ha creado.
    - Bajar un fichero: el cliente puede indicar el nombre de un fichero y el servidor, si exite ese fichero, se lo envía por el canal de comunicación.
- En cada conexión el servidor al conectarse enviará un mensaje donde se indica el número de conexión secuencial y las operaciones que se pueden hacer.

## Ejercicio 2

- Crea un servidor que espere a que 3 clientes se hayan conectado. En cuanto esa condición esté cumplida, empieza a mandarles cada 5 segundos un mensaje "sigues conectado-##". ## es un número que va creciendo
- Los clientes imprimen por pantalla este mensaje.

- Nota: se pueden hacer 4 versiones de este ejercicios:
    - v1. usando stream socket, considerando solo 3 o más clientes
    - v2. usando stream socket, considerando grupos independientes de 3 clientes
    - v3. como v1 usando datagramsocket
    - v4. como v2 usando datagramsocket

## Ejercicio 3

- Crear un cliente que puede enviar un mensaje a un servidor. El mensaje lo escribe el usuario por teclado.
- El servidor empezarará a reenviar el mensaje recibido cada 5 segundos al cliente.
- El cliente, podrá recibir un nuevo texto al servidor (reemplazando el otro mensaje) pero sin dejar de recibir los mensajes que envía el servidor.

## Ejercicio 4

- Crear un programa que permita mandar un mensaje en un anillo de programas.
- Define una forma para poder añadir nuevos programas al anillo.