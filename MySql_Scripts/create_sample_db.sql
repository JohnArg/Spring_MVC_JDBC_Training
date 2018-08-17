create database if not exists EbookLibrary;
use EbookLibrary;
create table if not exists Ebooks(
	_id int auto_increment,
    title varchar(30),
    author varchar(30),
    published Date,
    primary key (_id)
)engine=InnoDB;
#start inserting sample data
insert into Ebooks(title, author, published)
values ("The Marathon", "Miranda Bridges", "1998-9-7");

insert into Ebooks(title, author, published)
values ("Violet Hill", "Jeremy Osgon", "1992-5-4");

insert into Ebooks(title, author, published)
values ("Silent Residence", "Amanda Crywaters", "2002-3-12");

insert into Ebooks(title, author, published)
values ("Snowflakes In Smallville", "Klark Kent", "2005-6-23");

insert into Ebooks(title, author, published)
values ("Gordon's Struggle", "Batman", "1997-12-24");

insert into Ebooks(title, author, published)
values ("Always Smile", "The Jocker", "1990-5-29");

insert into Ebooks(title, author, published)
values ("My Green Lantern", "Green Lantern", "2008-1-8");

insert into Ebooks(title, author, published)
values ("Save The Universe", "Thanos", "2017-8-3");

insert into Ebooks(title, author, published)
values ("Social Conduct", "Deadpool", "2018-11-2");

insert into Ebooks(title, author, published)
values ("Don't Choke On Ambitions", "Darth Vader", "1995-10-4");

insert into Ebooks(title, author, published)
values ("Be In Charge", "Bane", "1994-5-10");

insert into Ebooks(title, author, published)
values ("Do Or Do Not", "Yoda", "2002-3-1");

insert into Ebooks(title, author, published)
values ("Fly You Fool", "Gandalf", "1991-1-8");

insert into Ebooks(title, author, published)
values ("Snakes As Pets", "Voldemort", "2001-7-19");

insert into Ebooks(title, author, published)
values ("How To Hanlde Fame", "Harry Potter", "1995-5-8");

insert into Ebooks(title, author, published)
values ("Her Kiss Is Poisson", "Poisson Ivy", "1992-3-4");

insert into Ebooks(title, author, published)
values ("Moving To Siberia", "Dr. Freeze", "1997-6-4");

insert into Ebooks(title, author, published)
values ("Puzzles To Solve", "Eduard Nigma", "2005-2-23");

insert into Ebooks(title, author, published)
values ("Papaya", "A Minion", "2015-4-9");