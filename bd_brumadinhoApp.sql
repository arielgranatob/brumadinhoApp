-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 29-Jan-2019 às 22:25
-- Versão do servidor: 10.1.37-MariaDB
-- versão do PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arielgranato_brumadinhoApp`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tblanimal`
--

CREATE TABLE `tblanimal` (
  `codAnimal` int(11) NOT NULL,
  `fotoAnimal` varchar(500) NOT NULL,
  `tipoAnimal` varchar(500) NOT NULL,
  `nomeAnimal` varchar(500) NOT NULL,
  `obsAnimal` varchar(500) NOT NULL,
  `telefoneContatoAnimal` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tblanimal`
--

INSERT INTO `tblanimal` (`codAnimal`, `fotoAnimal`, `tipoAnimal`, `nomeAnimal`, `obsAnimal`, `telefoneContatoAnimal`) VALUES
(1, '24bae043e0d1d8ecc69748c714c912ef.jpg', 'Cachorro ', 'Diefo', 'Ahuahsus', 'Hauahau'),
(2, '7559a928b5d4932ae6bc906de9c2d721.jpg', 'Hauaha', 'Shsu', 'Shua', 'Bsus'),
(4, '2373788b411fb1cb40b57bf7a0d23ab4.jpg', 'Ariel', 'Ahua', 'Jaua8', 'Hshs'),
(5, 'a4d08fd3a8fa1186d6fd799a4ed5962d.jpg', 'Ariel', 'Ahua', 'Jaua8', 'Hshs'),
(6, 'c09e1808e0814a5d87abc926aa9b04df.jpg', 'Ariel', 'Mlk', 'Sereia', 'Miigs'),
(7, '06028e7a214c355980bd32e7c8bb351f.jpg', 'Ariel', 'Mlk', 'Sereia', 'Miigs'),
(8, 'afcf3af525ed60d8462a1faa6ba39215.jpg', 'Ariel', 'Mlk', 'Sereia', 'Miigs'),
(9, '4f047f44009a53b5888c0d45909723f4.jpg', 'Ariel', 'Mlk', 'Sereia', 'Miigs'),
(10, '3c68b4faae92ada7e5f1f2350540c7e7.jpg', 'Ariel', 'Mlk', 'Migs', 'Sereia ');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tblpessoa`
--

CREATE TABLE `tblpessoa` (
  `codPessoa` int(11) NOT NULL,
  `nomePessoa` varchar(500) NOT NULL,
  `fotoPessoa` varchar(500) NOT NULL,
  `celularPessoa` varchar(500) NOT NULL,
  `caracteristicasPessoa` varchar(500) NOT NULL,
  `tatuagemPessoa` varchar(500) NOT NULL,
  `cicatrizPessoa` varchar(500) NOT NULL,
  `fototatuagemPessoa` varchar(500) NOT NULL,
  `obscicatrizPessoa` varchar(500) NOT NULL,
  `caractmarcantesPessoa` varchar(500) NOT NULL,
  `nomedodeclarantePessoa` varchar(500) NOT NULL,
  `contatodeclarantePessoa` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tblpessoa`
--

INSERT INTO `tblpessoa` (`codPessoa`, `nomePessoa`, `fotoPessoa`, `celularPessoa`, `caracteristicasPessoa`, `tatuagemPessoa`, `cicatrizPessoa`, `fototatuagemPessoa`, `obscicatrizPessoa`, `caractmarcantesPessoa`, `nomedodeclarantePessoa`, `contatodeclarantePessoa`) VALUES
(1, 'Sereia', 'b12be0f8a8ec0b1201d2ec524b4789c3.jpg', '32322', 'Ahauha', 'Sim', 'Sim', 'ec3fe762e2568faf2794e49b7c332ffe.jpg', 'Testa', 'Nariz', 'Ariel', '123'),
(2, 'Saa', '0ac1ff3c4f6fce0cc3cdd567dca75e4d.jpg', 'Zsz', 'Gayaga', 'Sim', 'Sim', '42b8f54d0d08c0c2f0467c62e141326f.jpg', 'Hahah', 'Gayagay', 'Gauaya', 'Hauaha'),
(3, 'Ariel', '35d11d4c35dbf6a2f0714da6693174d0.jpg', '', '', 'NÃ£o', 'NÃ£o', '4baa18777acca54a948867f61924ca4d.jpg', '', '', 'Ariel Granato', '991287613');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblanimal`
--
ALTER TABLE `tblanimal`
  ADD PRIMARY KEY (`codAnimal`);

--
-- Indexes for table `tblpessoa`
--
ALTER TABLE `tblpessoa`
  ADD PRIMARY KEY (`codPessoa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblanimal`
--
ALTER TABLE `tblanimal`
  MODIFY `codAnimal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tblpessoa`
--
ALTER TABLE `tblpessoa`
  MODIFY `codPessoa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
