USE [master]
GO
/****** Object:  Database [QuizPracticeDB]    Script Date: 5/12/2024 5:25:41 PM ******/
CREATE DATABASE [QuizPracticeDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QuizPracticeDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\QuizPracticeDB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QuizPracticeDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\QuizPracticeDB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [QuizPracticeDB] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QuizPracticeDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QuizPracticeDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QuizPracticeDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QuizPracticeDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QuizPracticeDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QuizPracticeDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QuizPracticeDB] SET  MULTI_USER 
GO
ALTER DATABASE [QuizPracticeDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QuizPracticeDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QuizPracticeDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QuizPracticeDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QuizPracticeDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QuizPracticeDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QuizPracticeDB] SET QUERY_STORE = ON
GO
ALTER DATABASE [QuizPracticeDB] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [QuizPracticeDB]
GO
/****** Object:  Table [dbo].[Answer]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer](
	[Answer_ID] [int] IDENTITY(1,1) NOT NULL,
	[Question_ID] [int] NULL,
	[Content] [varchar](255) NULL,
	[Explanation] [text] NULL,
 CONSTRAINT [PK_Answer_1] PRIMARY KEY CLUSTERED 
(
	[Answer_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Answer_Detail]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Answer_Detail](
	[Answer_ID] [int] NULL,
	[Explanation] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Blog]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Blog](
	[Blog_ID] [int] IDENTITY(1,1) NOT NULL,
	[Blog_Title] [varchar](255) NULL,
	[Author_ID] [int] NULL,
	[Created_Date] [datetime] NULL,
	[Updated_Date] [datetime] NULL,
	[Content] [text] NULL,
	[Status] [bit] NULL,
	[Thumbnail] [varchar](255) NULL,
 CONSTRAINT [PK_Blog] PRIMARY KEY CLUSTERED 
(
	[Blog_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[Category_ID] [int] IDENTITY(1,1) NOT NULL,
	[Category_Name] [varchar](255) NOT NULL,
	[Created_At] [datetime] NOT NULL,
	[Updated_At] [datetime] NULL,
	[Created_By] [int] NOT NULL,
	[Updated_By] [int] NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Category_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Correct_Answer]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Correct_Answer](
	[Answer_ID] [int] NOT NULL,
	[Question_ID] [int] NOT NULL,
 CONSTRAINT [PK_Correct_Answer] PRIMARY KEY CLUSTERED 
(
	[Answer_ID] ASC,
	[Question_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[Course_ID] [int] IDENTITY(1,1) NOT NULL,
	[Course_Name] [varchar](255) NOT NULL,
	[Category_ID] [int] NOT NULL,
	[Status_ID] [int] NOT NULL,
	[Thumbnail] [varchar](255) NULL,
	[Description] [text] NULL,
	[Created_At] [datetime] NOT NULL,
	[Updated_At] [datetime] NULL,
	[Created_By] [int] NOT NULL,
	[Updated_By] [int] NULL,
 CONSTRAINT [PK_Course] PRIMARY KEY CLUSTERED 
(
	[Course_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course_Dimension]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course_Dimension](
	[Course_ID] [int] NOT NULL,
	[Dimension_ID] [int] NOT NULL,
 CONSTRAINT [PK_Course_Dimension] PRIMARY KEY CLUSTERED 
(
	[Course_ID] ASC,
	[Dimension_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course_Price]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course_Price](
	[Course_ID] [int] NOT NULL,
	[Price_Package_ID] [int] NOT NULL,
 CONSTRAINT [PK_Course_Price] PRIMARY KEY CLUSTERED 
(
	[Course_ID] ASC,
	[Price_Package_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course_Status]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course_Status](
	[Course_Status_ID] [int] IDENTITY(1,1) NOT NULL,
	[Course_Status_Name] [varchar](255) NULL,
 CONSTRAINT [PK_Course_Status] PRIMARY KEY CLUSTERED 
(
	[Course_Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Course_Tag]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course_Tag](
	[Course_ID] [int] NOT NULL,
	[Tag_ID] [int] NOT NULL,
 CONSTRAINT [PK_Course_Tag] PRIMARY KEY CLUSTERED 
(
	[Course_ID] ASC,
	[Tag_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dimension]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dimension](
	[Dimension_ID] [int] IDENTITY(1,1) NOT NULL,
	[Dimension_Name] [varchar](255) NULL,
	[Description] [text] NULL,
	[Dimension_Type_ID] [int] NULL,
 CONSTRAINT [PK_Dimension] PRIMARY KEY CLUSTERED 
(
	[Dimension_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Dimension_Type]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Dimension_Type](
	[Dimesion_Type_ID] [int] IDENTITY(1,1) NOT NULL,
	[Dimension_Type_Name] [varchar](255) NULL,
 CONSTRAINT [PK_Dimension_Type] PRIMARY KEY CLUSTERED 
(
	[Dimesion_Type_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lesson]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lesson](
	[Lesson_ID] [int] IDENTITY(1,1) NOT NULL,
	[Lesson_Name] [varchar](255) NULL,
	[Video_Url] [varchar](255) NULL,
	[Content] [text] NULL,
	[Status] [bit] NULL,
	[Course_ID] [int] NULL,
	[Description] [nchar](10) NULL,
 CONSTRAINT [PK_Lesson] PRIMARY KEY CLUSTERED 
(
	[Lesson_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Price_Package]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Price_Package](
	[Price_Package_ID] [int] IDENTITY(1,1) NOT NULL,
	[Price_Package_Name] [varchar](255) NULL,
	[Access_Duration] [int] NULL,
	[Price] [decimal](18, 2) NULL,
	[Sale_Price] [decimal](18, 2) NULL,
	[Price_Package_Status_ID] [int] NULL,
	[Description] [text] NULL,
 CONSTRAINT [PK_Price_Package] PRIMARY KEY CLUSTERED 
(
	[Price_Package_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Price_Package_Status]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Price_Package_Status](
	[Price_Package_Status_ID] [int] IDENTITY(1,1) NOT NULL,
	[Price_Package_Status_Name] [varchar](255) NULL,
 CONSTRAINT [PK_Price_Package_Status] PRIMARY KEY CLUSTERED 
(
	[Price_Package_Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question](
	[Question_ID] [int] IDENTITY(1,1) NOT NULL,
	[Course_ID] [int] NULL,
	[Dimention_ID] [int] NULL,
	[Lesson_ID] [int] NULL,
	[Question_Status_ID] [int] NULL,
	[Level_ID] [int] NULL,
	[Content] [text] NULL,
	[Media_Url] [varchar](255) NULL,
	[Question_Type_ID] [int] NULL,
 CONSTRAINT [PK_Question] PRIMARY KEY CLUSTERED 
(
	[Question_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question_Dimension]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question_Dimension](
	[Question_ID] [int] NOT NULL,
	[Dimension_ID] [int] NOT NULL,
 CONSTRAINT [PK_Question_Dimension] PRIMARY KEY CLUSTERED 
(
	[Question_ID] ASC,
	[Dimension_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question_Status]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question_Status](
	[Question_Status_ID] [int] IDENTITY(1,1) NOT NULL,
	[Question_Status_Name] [varchar](255) NULL,
 CONSTRAINT [PK_Question_Status] PRIMARY KEY CLUSTERED 
(
	[Question_Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Question_Type]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Question_Type](
	[Question_Type_ID] [int] IDENTITY(1,1) NOT NULL,
	[Question_Type_Name] [varchar](255) NOT NULL,
 CONSTRAINT [PK_Question_Type] PRIMARY KEY CLUSTERED 
(
	[Question_Type_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz](
	[Quiz_ID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [varchar](255) NULL,
	[Lesson_ID] [int] NULL,
	[Course_ID] [int] NULL,
	[Quiz_Level_ID] [int] NULL,
	[Dimension_ID] [int] NULL,
	[Quiz_Status_ID] [int] NULL,
	[Duration] [time](7) NULL,
	[Pass_Rate] [float] NULL,
	[Description] [text] NULL,
	[Total_Question] [int] NOT NULL,
 CONSTRAINT [PK_Quiz] PRIMARY KEY CLUSTERED 
(
	[Quiz_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz_Level]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz_Level](
	[Quiz_Level_ID] [int] NOT NULL,
	[Quiz_Level_Name] [varchar](100) NULL,
 CONSTRAINT [PK_Quiz_Level] PRIMARY KEY CLUSTERED 
(
	[Quiz_Level_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Quiz_Question]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Quiz_Question](
	[Quiz_ID] [int] NOT NULL,
	[Question_ID] [int] NOT NULL,
 CONSTRAINT [PK_Quiz_Question] PRIMARY KEY CLUSTERED 
(
	[Quiz_ID] ASC,
	[Question_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration_Course]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration_Course](
	[Registration_ID] [int] IDENTITY(1,1) NOT NULL,
	[Registration_Date] [datetime] NULL,
	[Registration_Status_ID] [int] NULL,
	[Course_ID] [int] NULL,
	[Price_Package_ID] [int] NULL,
	[User_ID] [int] NULL,
 CONSTRAINT [PK_Registration_Course] PRIMARY KEY CLUSTERED 
(
	[Registration_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration_Status]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration_Status](
	[Registration_Status_ID] [int] IDENTITY(1,1) NOT NULL,
	[Registration_Status_Name] [varchar](255) NOT NULL,
 CONSTRAINT [PK_Registration_Status] PRIMARY KEY CLUSTERED 
(
	[Registration_Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[Role_ID] [int] IDENTITY(1,1) NOT NULL,
	[Role_Name] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Role] PRIMARY KEY CLUSTERED 
(
	[Role_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Slider]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Slider](
	[Slider_ID] [int] IDENTITY(1,1) NOT NULL,
	[Slider_URL] [varchar](255) NOT NULL,
	[Title] [varchar](255) NOT NULL,
	[Back_up_Link] [varchar](255) NOT NULL,
	[Note] [text] NULL,
	[Course_ID] [int] NOT NULL,
	[Created_At] [datetime] NOT NULL,
	[Updated_At] [datetime] NULL,
	[Created_By] [int] NOT NULL,
	[Updated_By] [int] NULL,
 CONSTRAINT [PK_Slider] PRIMARY KEY CLUSTERED 
(
	[Slider_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tag]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tag](
	[Tag_ID] [int] IDENTITY(1,1) NOT NULL,
	[Tag_Name] [varchar](255) NULL,
 CONSTRAINT [PK_Tag] PRIMARY KEY CLUSTERED 
(
	[Tag_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[User_ID] [int] IDENTITY(1,1) NOT NULL,
	[First_Name] [varchar](255) NOT NULL,
	[Last_Name] [varchar](255) NOT NULL,
	[Email] [varchar](255) NOT NULL,
	[Phone_Number] [varchar](30) NOT NULL,
	[Gender] [bit] NULL,
	[DOB] [date] NULL,
	[Profile_Image] [varchar](255) NULL,
	[Username] [varchar](255) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[Created_At] [datetime] NOT NULL,
	[Updated_At] [datetime] NULL,
	[Role_ID] [int] NULL,
	[Status_ID] [int] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[User_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User_Quiz]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_Quiz](
	[User_ID] [int] NOT NULL,
	[Quiz_ID] [int] NOT NULL,
 CONSTRAINT [PK_User_Quiz] PRIMARY KEY CLUSTERED 
(
	[User_ID] ASC,
	[Quiz_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User_Staus]    Script Date: 5/12/2024 5:25:41 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_Staus](
	[Status_ID] [int] IDENTITY(1,1) NOT NULL,
	[Status_Name] [varchar](255) NULL,
 CONSTRAINT [PK_User_Staus] PRIMARY KEY CLUSTERED 
(
	[Status_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Answer]  WITH CHECK ADD  CONSTRAINT [FK_Answer_Question1] FOREIGN KEY([Question_ID])
REFERENCES [dbo].[Question] ([Question_ID])
GO
ALTER TABLE [dbo].[Answer] CHECK CONSTRAINT [FK_Answer_Question1]
GO
ALTER TABLE [dbo].[Blog]  WITH CHECK ADD  CONSTRAINT [FK_Blog_User] FOREIGN KEY([Author_ID])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Blog] CHECK CONSTRAINT [FK_Blog_User]
GO
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_User] FOREIGN KEY([Updated_By])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_User]
GO
ALTER TABLE [dbo].[Category]  WITH CHECK ADD  CONSTRAINT [FK_Category_User1] FOREIGN KEY([Created_By])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Category] CHECK CONSTRAINT [FK_Category_User1]
GO
ALTER TABLE [dbo].[Correct_Answer]  WITH CHECK ADD  CONSTRAINT [FK_Correct_Answer_Answer] FOREIGN KEY([Answer_ID])
REFERENCES [dbo].[Answer] ([Answer_ID])
GO
ALTER TABLE [dbo].[Correct_Answer] CHECK CONSTRAINT [FK_Correct_Answer_Answer]
GO
ALTER TABLE [dbo].[Correct_Answer]  WITH CHECK ADD  CONSTRAINT [FK_Correct_Answer_Question] FOREIGN KEY([Question_ID])
REFERENCES [dbo].[Question] ([Question_ID])
GO
ALTER TABLE [dbo].[Correct_Answer] CHECK CONSTRAINT [FK_Correct_Answer_Question]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [FK_Course_Category] FOREIGN KEY([Category_ID])
REFERENCES [dbo].[Category] ([Category_ID])
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [FK_Course_Category]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [FK_Course_Course_Status] FOREIGN KEY([Status_ID])
REFERENCES [dbo].[Course_Status] ([Course_Status_ID])
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [FK_Course_Course_Status]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [FK_Course_User] FOREIGN KEY([Created_By])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [FK_Course_User]
GO
ALTER TABLE [dbo].[Course]  WITH CHECK ADD  CONSTRAINT [FK_Course_User1] FOREIGN KEY([Updated_By])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Course] CHECK CONSTRAINT [FK_Course_User1]
GO
ALTER TABLE [dbo].[Course_Dimension]  WITH CHECK ADD  CONSTRAINT [FK_Course_Dimension_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Course_Dimension] CHECK CONSTRAINT [FK_Course_Dimension_Course]
GO
ALTER TABLE [dbo].[Course_Dimension]  WITH CHECK ADD  CONSTRAINT [FK_Course_Dimension_Dimension] FOREIGN KEY([Dimension_ID])
REFERENCES [dbo].[Dimension] ([Dimension_ID])
GO
ALTER TABLE [dbo].[Course_Dimension] CHECK CONSTRAINT [FK_Course_Dimension_Dimension]
GO
ALTER TABLE [dbo].[Course_Price]  WITH CHECK ADD  CONSTRAINT [FK_Course_Price_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Course_Price] CHECK CONSTRAINT [FK_Course_Price_Course]
GO
ALTER TABLE [dbo].[Course_Price]  WITH CHECK ADD  CONSTRAINT [FK_Course_Price_Price_Package] FOREIGN KEY([Price_Package_ID])
REFERENCES [dbo].[Price_Package] ([Price_Package_ID])
GO
ALTER TABLE [dbo].[Course_Price] CHECK CONSTRAINT [FK_Course_Price_Price_Package]
GO
ALTER TABLE [dbo].[Course_Tag]  WITH CHECK ADD  CONSTRAINT [FK_Course_Tag_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Course_Tag] CHECK CONSTRAINT [FK_Course_Tag_Course]
GO
ALTER TABLE [dbo].[Course_Tag]  WITH CHECK ADD  CONSTRAINT [FK_Course_Tag_Tag] FOREIGN KEY([Tag_ID])
REFERENCES [dbo].[Tag] ([Tag_ID])
GO
ALTER TABLE [dbo].[Course_Tag] CHECK CONSTRAINT [FK_Course_Tag_Tag]
GO
ALTER TABLE [dbo].[Dimension]  WITH CHECK ADD  CONSTRAINT [FK_Dimension_Dimension_Type] FOREIGN KEY([Dimension_Type_ID])
REFERENCES [dbo].[Dimension_Type] ([Dimesion_Type_ID])
GO
ALTER TABLE [dbo].[Dimension] CHECK CONSTRAINT [FK_Dimension_Dimension_Type]
GO
ALTER TABLE [dbo].[Lesson]  WITH CHECK ADD  CONSTRAINT [FK_Lesson_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Lesson] CHECK CONSTRAINT [FK_Lesson_Course]
GO
ALTER TABLE [dbo].[Price_Package]  WITH CHECK ADD  CONSTRAINT [FK_Price_Package_Price_Package_Status] FOREIGN KEY([Price_Package_Status_ID])
REFERENCES [dbo].[Price_Package_Status] ([Price_Package_Status_ID])
GO
ALTER TABLE [dbo].[Price_Package] CHECK CONSTRAINT [FK_Price_Package_Price_Package_Status]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Course]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Question_Status] FOREIGN KEY([Question_Status_ID])
REFERENCES [dbo].[Question_Status] ([Question_Status_ID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Question_Status]
GO
ALTER TABLE [dbo].[Question]  WITH CHECK ADD  CONSTRAINT [FK_Question_Question_Type] FOREIGN KEY([Question_Type_ID])
REFERENCES [dbo].[Question_Type] ([Question_Type_ID])
GO
ALTER TABLE [dbo].[Question] CHECK CONSTRAINT [FK_Question_Question_Type]
GO
ALTER TABLE [dbo].[Question_Dimension]  WITH CHECK ADD  CONSTRAINT [FK_Question_Dimension_Dimension] FOREIGN KEY([Dimension_ID])
REFERENCES [dbo].[Dimension] ([Dimension_ID])
GO
ALTER TABLE [dbo].[Question_Dimension] CHECK CONSTRAINT [FK_Question_Dimension_Dimension]
GO
ALTER TABLE [dbo].[Question_Dimension]  WITH CHECK ADD  CONSTRAINT [FK_Question_Dimension_Question] FOREIGN KEY([Question_ID])
REFERENCES [dbo].[Question] ([Question_ID])
GO
ALTER TABLE [dbo].[Question_Dimension] CHECK CONSTRAINT [FK_Question_Dimension_Question]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Course]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Lesson] FOREIGN KEY([Lesson_ID])
REFERENCES [dbo].[Lesson] ([Lesson_ID])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Lesson]
GO
ALTER TABLE [dbo].[Quiz]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Quiz_Level] FOREIGN KEY([Quiz_Level_ID])
REFERENCES [dbo].[Quiz_Level] ([Quiz_Level_ID])
GO
ALTER TABLE [dbo].[Quiz] CHECK CONSTRAINT [FK_Quiz_Quiz_Level]
GO
ALTER TABLE [dbo].[Quiz_Question]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Question_Question] FOREIGN KEY([Question_ID])
REFERENCES [dbo].[Question] ([Question_ID])
GO
ALTER TABLE [dbo].[Quiz_Question] CHECK CONSTRAINT [FK_Quiz_Question_Question]
GO
ALTER TABLE [dbo].[Quiz_Question]  WITH CHECK ADD  CONSTRAINT [FK_Quiz_Question_Quiz] FOREIGN KEY([Quiz_ID])
REFERENCES [dbo].[Quiz] ([Quiz_ID])
GO
ALTER TABLE [dbo].[Quiz_Question] CHECK CONSTRAINT [FK_Quiz_Question_Quiz]
GO
ALTER TABLE [dbo].[Registration_Course]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Course_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Registration_Course] CHECK CONSTRAINT [FK_Registration_Course_Course]
GO
ALTER TABLE [dbo].[Registration_Course]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Course_Price_Package] FOREIGN KEY([Price_Package_ID])
REFERENCES [dbo].[Price_Package] ([Price_Package_ID])
GO
ALTER TABLE [dbo].[Registration_Course] CHECK CONSTRAINT [FK_Registration_Course_Price_Package]
GO
ALTER TABLE [dbo].[Registration_Course]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Course_Registration_Status] FOREIGN KEY([Registration_Status_ID])
REFERENCES [dbo].[Registration_Status] ([Registration_Status_ID])
GO
ALTER TABLE [dbo].[Registration_Course] CHECK CONSTRAINT [FK_Registration_Course_Registration_Status]
GO
ALTER TABLE [dbo].[Registration_Course]  WITH CHECK ADD  CONSTRAINT [FK_Registration_Course_User] FOREIGN KEY([User_ID])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Registration_Course] CHECK CONSTRAINT [FK_Registration_Course_User]
GO
ALTER TABLE [dbo].[Slider]  WITH CHECK ADD  CONSTRAINT [FK_Slider_Course] FOREIGN KEY([Course_ID])
REFERENCES [dbo].[Course] ([Course_ID])
GO
ALTER TABLE [dbo].[Slider] CHECK CONSTRAINT [FK_Slider_Course]
GO
ALTER TABLE [dbo].[Slider]  WITH CHECK ADD  CONSTRAINT [FK_Slider_User] FOREIGN KEY([Created_By])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Slider] CHECK CONSTRAINT [FK_Slider_User]
GO
ALTER TABLE [dbo].[Slider]  WITH CHECK ADD  CONSTRAINT [FK_Slider_User1] FOREIGN KEY([Created_By])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[Slider] CHECK CONSTRAINT [FK_Slider_User1]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_Role] FOREIGN KEY([Role_ID])
REFERENCES [dbo].[Role] ([Role_ID])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_Role]
GO
ALTER TABLE [dbo].[User]  WITH CHECK ADD  CONSTRAINT [FK_User_User_Staus] FOREIGN KEY([Status_ID])
REFERENCES [dbo].[User_Staus] ([Status_ID])
GO
ALTER TABLE [dbo].[User] CHECK CONSTRAINT [FK_User_User_Staus]
GO
ALTER TABLE [dbo].[User_Quiz]  WITH CHECK ADD  CONSTRAINT [FK_User_Quiz_Quiz] FOREIGN KEY([Quiz_ID])
REFERENCES [dbo].[Quiz] ([Quiz_ID])
GO
ALTER TABLE [dbo].[User_Quiz] CHECK CONSTRAINT [FK_User_Quiz_Quiz]
GO
ALTER TABLE [dbo].[User_Quiz]  WITH CHECK ADD  CONSTRAINT [FK_User_Quiz_User] FOREIGN KEY([User_ID])
REFERENCES [dbo].[User] ([User_ID])
GO
ALTER TABLE [dbo].[User_Quiz] CHECK CONSTRAINT [FK_User_Quiz_User]
GO
USE [master]
GO
ALTER DATABASE [QuizPracticeDB] SET  READ_WRITE 
GO
