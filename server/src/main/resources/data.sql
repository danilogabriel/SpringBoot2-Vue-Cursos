INSERT INTO USUARIO(username,legajo,cuit,nombre) VALUES ('dbiondi', 3710681, 20220254144, 'Danilo Biondi');
INSERT INTO USUARIO(username,legajo,cuit,nombre) VALUES ('cbonadeo', 2222222, 22222222222, 'Carlos Bonadeo');
INSERT INTO USUARIO(username,legajo,cuit,nombre) VALUES ('pnovoa', 3333333, 33333333333, 'Pablo Novoa');

INSERT INTO CURSO(titulo,tipo,categoria,descripcion,contenidos,instructores,perfil_alumno,cant_horas,max_alumnos,publicado,usuario_legajo) VALUES ('VueJS Inicial',     'Curso','WEB','Es un curso de iniciación para aquellos que quieran desarrolar web','Instalacion  Primer Ejemplo   Uso de Componentes   Uso de VUEX','Carlos Bonadeo [experimentado web developer de SIT]','DEV',12,10, true, 3710681);
INSERT INTO CURSO(titulo,tipo,categoria,descripcion,contenidos,instructores,perfil_alumno,cant_horas,max_alumnos,publicado,usuario_legajo) VALUES ('SpringBoot Inicial','Curso','WEB','Es un curso de iniciación en desarrollo de API REST en Spring','Instalacion  Parte 1   Parte 2  Parte 3','Raul Mendez [Desarrollador Senior]','DEV',12,10, false, 3710681);
INSERT INTO CURSO(titulo,tipo,categoria,descripcion,contenidos,instructores,perfil_alumno,cant_horas,max_alumnos,publicado,usuario_legajo) VALUES ('PL SQL Basico',     'Charla','DB','Primer acercamiento a PL SQL','Parte1   Parte2   Parte3','Carlos Ovin','DEV',8,5, false, 2222222);
INSERT INTO CURSO(titulo,tipo,categoria,descripcion,contenidos,instructores,perfil_alumno,cant_horas,max_alumnos,publicado,usuario_legajo) VALUES ('Selenium',          'Taller','TEST','Conocimientos basicos de testing End to End','Parte1   Parte2   Parte3','Seba Picardi','QA',20,8, true, 3333333);

INSERT INTO INSCRIPCION(legajo,idcurso) VALUES (3710681, 1);


