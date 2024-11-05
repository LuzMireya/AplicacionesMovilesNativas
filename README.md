# AplicacionesMovilesNativas
Desarrollo de aplicaciones moviles nativas
Equipo #4: Los developers

USE loginapp;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100)UNIQUE NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    rol ENUM ('admin', 'usuario') NOT NULL,
    fotoPerfil VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultima_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP);
