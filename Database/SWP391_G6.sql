USE [master]
GO
/****** Object:  Database [SWP391_G6]    Script Date: 5/20/2024 11:31:57 AM ******/
CREATE DATABASE [SWP391_G6]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SWP391_G6', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\SWP391_G6.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SWP391_G6_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\SWP391_G6_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [SWP391_G6] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SWP391_G6].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SWP391_G6] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SWP391_G6] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SWP391_G6] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SWP391_G6] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SWP391_G6] SET ARITHABORT OFF 
GO
ALTER DATABASE [SWP391_G6] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [SWP391_G6] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SWP391_G6] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SWP391_G6] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SWP391_G6] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SWP391_G6] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SWP391_G6] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SWP391_G6] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SWP391_G6] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SWP391_G6] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SWP391_G6] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SWP391_G6] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SWP391_G6] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SWP391_G6] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SWP391_G6] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SWP391_G6] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SWP391_G6] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SWP391_G6] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SWP391_G6] SET  MULTI_USER 
GO
ALTER DATABASE [SWP391_G6] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SWP391_G6] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SWP391_G6] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SWP391_G6] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SWP391_G6] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SWP391_G6] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SWP391_G6] SET QUERY_STORE = ON
GO
ALTER DATABASE [SWP391_G6] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [SWP391_G6]
GO
/****** Object:  Table [dbo].[answers]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[answers](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[answer_detail] [varchar](max) NULL,
	[created_at] [date] NULL,
	[update_at] [date] NULL,
	[creator_id] [int] NULL,
	[is_correct] [int] NULL,
 CONSTRAINT [PK_answers] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[blogs]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[blogs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](max) NULL,
	[author] [varchar](max) NULL,
	[description] [varchar](max) NULL,
	[creator_id] [int] NULL,
	[comment_id] [int] NULL,
 CONSTRAINT [PK_blogs] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[comments]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comments](
	[id] [int] NOT NULL,
	[detail] [varchar](max) NULL,
	[rate] [int] NULL,
 CONSTRAINT [PK_comments] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lesson_has_quiz]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lesson_has_quiz](
	[lesson_id] [int] NULL,
	[quiz_id] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lessons]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lessons](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](max) NULL,
	[creator_id] [int] NULL,
	[update_at] [date] NULL,
	[created_at] [date] NULL,
	[status] [int] NULL,
 CONSTRAINT [PK_lessons] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[payments]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[payments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[user_id] [int] NULL,
	[method] [varchar](max) NULL,
	[payment_date] [date] NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_payments] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[question_has_answer]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question_has_answer](
	[question_id] [int] NULL,
	[answer_id] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[questions]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[questions](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[detail] [varchar](max) NULL,
 CONSTRAINT [PK_questions] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[quiz_has_question]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[quiz_has_question](
	[quiz_id] [int] NULL,
	[question_id] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[quizs]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[quizs](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](max) NULL,
	[created_at] [date] NULL,
	[update_ad] [date] NULL,
	[creator_id] [varchar](max) NULL,
	[score] [float] NULL,
	[time] [int] NULL,
 CONSTRAINT [PK_quizs] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](max) NULL,
 CONSTRAINT [PK_roles] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[status]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[status](
	[id] [int] NOT NULL,
	[name] [varchar](max) NULL,
 CONSTRAINT [PK_status] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[subject_has_lesson]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subject_has_lesson](
	[subject_id] [int] NULL,
	[lesson_id] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[subjects]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[subjects](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](max) NULL,
	[creator_id] [int] NULL,
	[creater_at] [date] NULL,
	[update_at] [date] NULL,
	[status] [int] NULL,
	[img] [varchar](max) NULL,
 CONSTRAINT [PK_subjects] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_has_subject]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_has_subject](
	[user_id] [int] NULL,
	[subject_id] [int] NULL,
	[start_date] [nchar](10) NULL,
	[end_date] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 5/20/2024 11:31:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[first_name] [varchar](max) NULL,
	[last_name] [varchar](max) NULL,
	[email] [varchar](max) NULL,
	[phone_number] [varchar](max) NULL,
	[gender] [int] NULL,
	[dob] [date] NULL,
	[profile_img] [varchar](max) NULL,
	[username] [varchar](max) NULL,
	[password] [varchar](max) NULL,
	[created_at] [date] NULL,
	[updated_at] [date] NULL,
	[role_id] [int] NULL,
	[status_id] [int] NULL,
	[token] [varchar](30) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[blogs]  WITH CHECK ADD  CONSTRAINT [FK_blogs_comments] FOREIGN KEY([comment_id])
REFERENCES [dbo].[comments] ([id])
GO
ALTER TABLE [dbo].[blogs] CHECK CONSTRAINT [FK_blogs_comments]
GO
ALTER TABLE [dbo].[blogs]  WITH CHECK ADD  CONSTRAINT [FK_blogs_users] FOREIGN KEY([creator_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[blogs] CHECK CONSTRAINT [FK_blogs_users]
GO
ALTER TABLE [dbo].[lesson_has_quiz]  WITH CHECK ADD  CONSTRAINT [FK_lesson_has_quiz_lessons] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[lesson_has_quiz] CHECK CONSTRAINT [FK_lesson_has_quiz_lessons]
GO
ALTER TABLE [dbo].[lesson_has_quiz]  WITH CHECK ADD  CONSTRAINT [FK_lesson_has_quiz_quizs] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[quizs] ([id])
GO
ALTER TABLE [dbo].[lesson_has_quiz] CHECK CONSTRAINT [FK_lesson_has_quiz_quizs]
GO
ALTER TABLE [dbo].[payments]  WITH CHECK ADD  CONSTRAINT [FK_payments_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[payments] CHECK CONSTRAINT [FK_payments_users]
GO
ALTER TABLE [dbo].[question_has_answer]  WITH CHECK ADD  CONSTRAINT [FK_question_has_answer_answers] FOREIGN KEY([answer_id])
REFERENCES [dbo].[answers] ([id])
GO
ALTER TABLE [dbo].[question_has_answer] CHECK CONSTRAINT [FK_question_has_answer_answers]
GO
ALTER TABLE [dbo].[question_has_answer]  WITH CHECK ADD  CONSTRAINT [FK_question_has_answer_questions] FOREIGN KEY([question_id])
REFERENCES [dbo].[questions] ([id])
GO
ALTER TABLE [dbo].[question_has_answer] CHECK CONSTRAINT [FK_question_has_answer_questions]
GO
ALTER TABLE [dbo].[quiz_has_question]  WITH CHECK ADD  CONSTRAINT [FK_quiz_has_question_questions] FOREIGN KEY([question_id])
REFERENCES [dbo].[questions] ([id])
GO
ALTER TABLE [dbo].[quiz_has_question] CHECK CONSTRAINT [FK_quiz_has_question_questions]
GO
ALTER TABLE [dbo].[quiz_has_question]  WITH CHECK ADD  CONSTRAINT [FK_quiz_has_question_quizs] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[quizs] ([id])
GO
ALTER TABLE [dbo].[quiz_has_question] CHECK CONSTRAINT [FK_quiz_has_question_quizs]
GO
ALTER TABLE [dbo].[subject_has_lesson]  WITH CHECK ADD  CONSTRAINT [FK_subject_has_lesson_lessons] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[subject_has_lesson] CHECK CONSTRAINT [FK_subject_has_lesson_lessons]
GO
ALTER TABLE [dbo].[subject_has_lesson]  WITH CHECK ADD  CONSTRAINT [FK_subject_has_lesson_subjects] FOREIGN KEY([subject_id])
REFERENCES [dbo].[subjects] ([id])
GO
ALTER TABLE [dbo].[subject_has_lesson] CHECK CONSTRAINT [FK_subject_has_lesson_subjects]
GO
ALTER TABLE [dbo].[user_has_subject]  WITH CHECK ADD  CONSTRAINT [FK_user_has_subject_subjects] FOREIGN KEY([subject_id])
REFERENCES [dbo].[subjects] ([id])
GO
ALTER TABLE [dbo].[user_has_subject] CHECK CONSTRAINT [FK_user_has_subject_subjects]
GO
ALTER TABLE [dbo].[user_has_subject]  WITH CHECK ADD  CONSTRAINT [FK_user_has_subject_users] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[user_has_subject] CHECK CONSTRAINT [FK_user_has_subject_users]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_users_roles] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK_users_roles]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FK_users_status] FOREIGN KEY([status_id])
REFERENCES [dbo].[status] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FK_users_status]
GO
USE [master]
GO
ALTER DATABASE [SWP391_G6] SET  READ_WRITE 
GO
