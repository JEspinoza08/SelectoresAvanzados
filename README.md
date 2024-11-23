<h1>Testing - WEB</h1>

Primer paso, asegurese de colocar el chromedriver segun la version de su navegador chrome en la carpeta:
driver

Comando para la ejecucion por terminal y ver los reportes cucumber:

```bash
Reemplazar @TAG por el tag de ejecucion del .feature

mvn clean verify -Dcucumber.filter.tags="@HAPPY_PATH_CARRITO"
```

Ejecutar los comandos:

    * mvn clean intall -DskipTests  -> Volver a reimportar las dependencias
    * mvn compile -> para compilar el proyecto

Para ver los reportes de cucUmber ir a la carpeta: target/site/cucumber-html-reports y abrir en el explorador el archivo overview-features



  
