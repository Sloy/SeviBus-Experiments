SeviBus
=======

SeviBus - versión 4 (en desarrollo) - Now with Open Source! :D

[![Build Status](https://travis-ci.org/Sloy/SeviBus.svg?branch=master)](https://travis-ci.org/Sloy/SeviBus)  [![Coverage Status](https://coveralls.io/repos/Sloy/SeviBus/badge.svg)](https://coveralls.io/r/Sloy/SeviBus)

![New icon proposal](./art/web_hi_res_512.png)

## Contribuir
##### Roadmap
Hay un Kanban público de features y tareas pendientes, en desarrollo y terminadas: https://trello.com/b/uLZG297a/sevibus-4-user-stories

##### Cómo usar el repositorio
El desarrollo se hace mediante gitflow, con ramas feature para cada nueva función que se desarrolla, una rama principal develop donde unir dichas features, y una rama master que representa la actual versión publicada. 
Se puede trabajar en varias features de forma paralela.
Excepcionalmente podrán ocurrir commits en la rama develop, pero no debe considerarse permitido.

Hasta la publicación de la versión 4.0.0, las versiones publicadas en master se considerarán releases para betatesters.

Tanto las contribuciones externas como la integración de nuevas features se deben hacer mediante pull request. Para aceptar un pull request es necesario como mínimo el aprobado del responsable del repositorio (Sloy). Se anima a hacer code reviews por parte de cualquier persona. 

Se recomienda que el código añadido al módulo *domain*. La carencia de estos puede ser motivo para rechazarlo, y en un futuro la bajada de cobertura será un criterio automático de fallo.

El código debe estar escrito en inglés, aunque los commits pueden hacerse en español para facilitar la comprensión de los cambios realizados.

## Integración contínua
El repositorio utiliza Travis CI para hacer compilaciones de cada commit publicado, Coveralls para determinar el porcentaje de cobertura de tests, y Crashlytics beta para distribuir versiones de prueba de forma automatizada.

Hay dos grupos de beta: developers y betatesters.
- *Developers:* Este grupo recibe compilaciones de debug para cualquier commit hecho sobre develop y pull requests. Sólo pueden estar personas involucradas en el desarrollo del código.
- *Betatesters:* Compilaciones de release a partir de commits hechos en master, es decir, releases y hotfixes. Cualquiera puede entrar en este grupo, para ello pedirlo a sevibus@sloydev.com o contactando con el responsable.

