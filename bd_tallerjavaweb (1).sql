-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-07-2020 a las 03:12:48
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_tallerjavaweb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_producto`
--

CREATE TABLE `tb_producto` (
  `id_producto` int(11) NOT NULL,
  `nombre_producto` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `descripcion_producto` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  `unidades` int(11) NOT NULL,
  `valor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tb_producto`
--

INSERT INTO `tb_producto` (`id_producto`, `nombre_producto`, `descripcion_producto`, `unidades`, `valor`) VALUES
(1, 'Cafe', 'Cafe suave, granulado en tarro', 15, 11000),
(2, 'azucar', 'azucar refinada con vitamina d', 20, 1000),
(3, 'pan integral', 'pan integral sin conservantes', 30, 500),
(4, 'leche entera', 'leche entera bolsa', 10, 1500),
(5, 'vasos', 'vasos de plastico color verde', 15, 70),
(6, 'vino', 'vino de manzana', 5, 12500),
(9, 'arroz', 'Libra de arroz', 50, 3000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tb_usuario`
--

CREATE TABLE `tb_usuario` (
  `id` int(11) NOT NULL,
  `nombre1` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `nombre2` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellido1` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apellido2` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `n_identificacion` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL,
  `correo` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `clave` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `sexo` varchar(1) COLLATE utf8mb4_spanish_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `tb_usuario`
--

INSERT INTO `tb_usuario` (`id`, `nombre1`, `nombre2`, `apellido1`, `apellido2`, `n_identificacion`, `correo`, `clave`, `sexo`, `fecha_nac`, `estado`) VALUES
(1, 'daniel', 'camilo', 'cristancho', 'rubio', 'cedula ', 'uncorreo@hotmail.com', '1234567890', 'M', '2000-11-19', 1),
(3, 'Carla', '', 'Marin', 'Espitia', 'cedula', 'eltalcorreo@gmail.com', '9876543210', 'F', '1997-12-23', 0),
(7, 'Daniela', 'Andrea', 'Aristizabal', 'Bonilla', 'Cedula', 'alguien@hotmail.com', '213567', 'F', '1999-07-30', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tb_producto`
--
ALTER TABLE `tb_producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tb_producto`
--
ALTER TABLE `tb_producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tb_usuario`
--
ALTER TABLE `tb_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
