-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 26-Abr-2017 às 18:06
-- Versão do servidor: 10.1.19-MariaDB
-- PHP Version: 7.0.13
CREATE database ideias;
use ideias;


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ideias`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `idea`
--

CREATE TABLE `idea` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` mediumtext NOT NULL,
  `status_name` varchar(100) NOT NULL,
  `tags` varchar(100) DEFAULT NULL,
  `user_cpf` varchar(11) NOT NULL,
  `goal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `idea_comments`
--

CREATE TABLE `idea_comments` (
  `id` int(11) NOT NULL,
  `comment` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `idea_has_idea_comments`
--

CREATE TABLE `idea_has_idea_comments` (
  `idea_id` int(11) NOT NULL,
  `idea_comments_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `idea_has_questions`
--

CREATE TABLE `idea_has_questions` (
  `idea_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `question` mediumtext NOT NULL,
  `user_cpf` varchar(11) NOT NULL,
  `answer` mediumtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `role`
--

CREATE TABLE `role` (
  `name` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`name`) VALUES
('administrator'), ('analyst'), ('idealizer');

-- --------------------------------------------------------

--
-- Estrutura da tabela `status`
--

CREATE TABLE `status` (
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

CREATE TABLE `user` (
  `cpf` varchar(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `role_name` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`cpf`, `email`, `name`, `phone`, `password`, `active`, `role_name`) VALUES
('68864065156', 'test@acad.pucrs.br', 'Admin', NULL, '123456', 1, 'administrator');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `idea`
--
ALTER TABLE `idea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_USER_idx` (`user_cpf`),
  ADD KEY `FK_STATUS_idx` (`status_name`);

--
-- Indexes for table `idea_comments`
--
ALTER TABLE `idea_comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `idea_has_idea_comments`
--
ALTER TABLE `idea_has_idea_comments`
  ADD PRIMARY KEY (`idea_id`,`idea_comments_id`),
  ADD KEY `fk_idea_has_idea_comments_idea_comments1_idx` (`idea_comments_id`),
  ADD KEY `fk_idea_has_idea_comments_idea1_idx` (`idea_id`);

--
-- Indexes for table `idea_has_questions`
--
ALTER TABLE `idea_has_questions`
  ADD PRIMARY KEY (`idea_id`,`questions_id`),
  ADD KEY `fk_idea_has_questions_questions1_idx` (`questions_id`),
  ADD KEY `fk_idea_has_questions_idea1_idx` (`idea_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_user_idx2` (`user_cpf`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`cpf`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `FK_ROLE_idx` (`role_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `idea`
--
ALTER TABLE `idea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `idea_comments`
--
ALTER TABLE `idea_comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `idea`
--
ALTER TABLE `idea`
  ADD CONSTRAINT `FK_STATUS` FOREIGN KEY (`status_name`) REFERENCES `status` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_USER` FOREIGN KEY (`user_cpf`) REFERENCES `user` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `idea_has_idea_comments`
--
ALTER TABLE `idea_has_idea_comments`
  ADD CONSTRAINT `fk_idea_has_idea_comments_idea1` FOREIGN KEY (`idea_id`) REFERENCES `idea` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idea_has_idea_comments_idea_comments1` FOREIGN KEY (`idea_comments_id`) REFERENCES `idea_comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `idea_has_questions`
--
ALTER TABLE `idea_has_questions`
  ADD CONSTRAINT `fk_idea_has_questions_idea1` FOREIGN KEY (`idea_id`) REFERENCES `idea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idea_has_questions_questions1` FOREIGN KEY (`questions_id`) REFERENCES `questions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `FK_user2` FOREIGN KEY (`user_cpf`) REFERENCES `user` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_name`) REFERENCES `role` (`name`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

INSERT INTO status (name) VALUES ('approved');
INSERT INTO status (name) VALUES ('draft');
INSERT INTO status (name) VALUES ('open');
INSERT INTO status (name) VALUES ('rejected');
INSERT INTO status (name) VALUES ('under_analysis');
