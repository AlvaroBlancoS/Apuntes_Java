-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-01-2022 a las 12:35:24
-- Versión del servidor: 10.1.36-MariaDB
-- Versión de PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empresa_proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamentos`
--

CREATE TABLE `departamentos` (
  `id_dep` int(11) NOT NULL,
  `nombre_dep` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `departamentos`
--

INSERT INTO `departamentos` (`id_dep`, `nombre_dep`) VALUES
(1, 'Informatica'),
(2, 'Administracion'),
(3, 'Contabilidad'),
(4, 'Almacenes'),
(5, 'Marketing');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id_empleado` int(11) NOT NULL,
  `id_depEm` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `dni` varchar(9) NOT NULL,
  `salario` double(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id_empleado`, `id_depEm`, `nombre`, `apellidos`, `dni`, `salario`) VALUES
(1, 1, 'Alvaro', 'Blanco Sangines', '12345678A', 2000.68),
(2, 1, 'Alberto', 'Hincapie', '87654321A', 2000.00),
(3, 2, 'Marta', 'Castillo', '86421357H', 1000.32),
(4, 5, 'Americo', 'Vespucio', '91827364T', 2000.12),
(5, 3, 'Ada', 'Lovelace', '12346598K', 2000.00),
(6, 3, 'Marie', 'Curie', '94613764L', 3000.00),
(7, 2, 'Marie', 'Curie', '28394032S', 3100.02),
(8, 5, 'Benito', 'Mussolini', '32654798W', 3000.00),
(9, 5, 'Antonio', 'Vivaldi', '23456789Q', 50000.23),
(10, 1, 'Juana', 'Perez', '12345698D', 4000.02);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departamentos`
--
ALTER TABLE `departamentos`
  ADD PRIMARY KEY (`id_dep`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `FK_Clave_empleado_departamento` (`id_depEm`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `FK_Clave_empleado_departamento` FOREIGN KEY (`id_depEm`) REFERENCES `departamentos` (`id_dep`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
