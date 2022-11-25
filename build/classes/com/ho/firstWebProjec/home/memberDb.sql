create table member2(
	m_photo varchar2(150 char) not null,
	m_id varchar2(15 char) primary key,
	m_pw varchar2(15 char) not null,
	m_name varchar2(10 char) not null,
	m_birthday date not null,
	m_email varchar2(20 char) not null,
	m_addr varchar2(100 char) not null
);

drop table commentNft2 cascade constraint purge;

create table nftGallery7(
	n_no number(5) primary key,
	n_owner varchar2(15 char) not null,
	n_photo varchar2(150 char) not null,
	n_title varchar2(30 char) not null,
	n_txt varchar2(250 char) not null,
	n_date date not null,
	constraint n_write foreign key(n_owner) references member2(m_id) on delete cascade
);

create table commentNft3(
	c_no number(6) primary key,
	c_id varchar2(15 char) not null,
	c_txt varchar2(100 char) not null,
	c_date date not null,
	c_n_no number(5) not null,
	constraint c_comment foreign key(c_n_no) references nftGallery7(n_no) on delete cascade
);

select * from COMMENTNFT3;

create sequence commentNft2_seq;

create sequence nftGallery2_seq;


delete from nftGallery7 where n_no = ?

insert into nftGallery4 values(nftGallery_seq.nextval, ?, ?, ?, sysdate);

select * from member2;

select * from(select rownum as rn, m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date from (select m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date from member2, nftGallery7 where where m_id = n_owner order by s_date desc)) where rn >= ? and rn <= ?
select m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date from member2, nftGallery7 where m_id = n_owner where rn >= ? and rn <= ?

select * 
	from(
		select rownum as rn, m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date 
			from (
				select m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date 
					from member2, nftGallery7 where m_id = n_owner order by n_date desc)) where rn >= 1 and rn <= 2;

select count(*) from nftGallery7 where n_txt, like '%'||?||'%' or n_owner like '%'||?||'%'
	
select * 
	from(
		select rownum as rn, m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date 
			from (
				select m_photo, m_name, n_no, n_owner, n_photo, n_title, n_txt, n_date 
					from member2, nftGallery7 where m_id = n_owner order by n_date desc)) where rn >= ? and rn <= ?;
		
					
update nftGallery7 set n_photo = ?, n_title = ?, n_txt = ?	

create table anything3(
	a_name varchar2(10 char) primary key,
	a_Characteristic varchar2(100 char) not null
);

select * from anything3;

insert into anything3 values('À±°æÈ£', 'Èûµë');
insert into anything3 values('ÀåÁØÈñ', '¹è·Á½É ±íÀ½');

update anything3 set a_Characteristic = '¹è·Á½É ±íÀ½' where a_name = 'ÀåÁØÈñ';
