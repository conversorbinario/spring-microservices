# USO

1. Crear un archivo de nombre `.env` basado en `.env.copy`. Definir el valor de las variables del mismo. 
2. Desde la raíz del proyecto:
    1. Ejecutar `docker-compose up`
    2. Reiniciar container en cada cambio de la app con `docker-compose restart java_app`
5. El proyecto estará listo accediendo por el puerto declarado en el .env -> FREE_LOCAL_PORT2