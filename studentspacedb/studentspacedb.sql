-- Crear tablas
CREATE TABLE public.avatar (
	id serial4 NOT NULL,
	url varchar NOT NULL,
	title varchar(100) NOT NULL,
	CONSTRAINT avatar_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user (
	id serial4 NOT NULL,
	username varchar(50) NOT NULL,
	name varchar(100) DEFAULT NULL::character varying NOT NULL,
	surname varchar(100) DEFAULT NULL::character varying NULL,
	email varchar(100) DEFAULT NULL::character varying NOT NULL,
	password varchar(100) NOT NULL,
	id_avatar int4 NULL,
	birthday date NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id),
	CONSTRAINT fk_user_avatar FOREIGN KEY (id_avatar) REFERENCES public.avatar(id)
);

CREATE TABLE public.course (
	id serial4 NOT NULL,
	name varchar(255) NOT NULL,
	user_id int4 NOT NULL,
	color varchar(10) NULL,
	CONSTRAINT course_pkey PRIMARY KEY (id),
	CONSTRAINT course_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.user(id)
);

CREATE TABLE public.subject (
	id serial4 NOT NULL,
	name varchar(255) NOT NULL,
	course_id int4 NOT NULL,
	color varchar(10) NULL,
	user_id int4 NOT NULL,
	CONSTRAINT subject_pkey PRIMARY KEY (id),
	CONSTRAINT subject_academic_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id) ON DELETE SET NULL,
	CONSTRAINT subject_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.user(id)
);

CREATE TABLE public.task (
	id serial4 NOT NULL,
	user_id int4 NOT NULL,
	title varchar NOT NULL,
	description varchar NULL,
	checked bool DEFAULT false NULL,
	course_id int4 NULL,
	date timestamptz NULL,
	subject_id int4 NULL,
	CONSTRAINT task_pkey PRIMARY KEY (id),
	CONSTRAINT task_academic_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id) ON DELETE SET NULL,
	CONSTRAINT task_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES public.subject(id) ON DELETE SET NULL,
	CONSTRAINT task_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.user(id)
);

CREATE TABLE public.document (
	id serial4 NOT NULL,
	user_id int4 NOT NULL,
	course_id int4 NULL,
	title varchar NOT NULL,
	body varchar NULL,
	subject_id int4 NULL,
	CONSTRAINT document_pkey PRIMARY KEY (id),
	CONSTRAINT document_academic_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id) ON DELETE SET NULL,
	CONSTRAINT document_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES public.subject(id) ON DELETE SET NULL,
	CONSTRAINT document_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.user(id)
);

CREATE TABLE public.event (
	id serial4 NOT NULL,
	user_id int4 NOT NULL,
	title varchar NOT NULL,
	description varchar NULL,
	all_day bool DEFAULT true NULL,
	course_id int4 NULL,
	start_date timestamptz NULL,
	end_date timestamptz NULL,
	subject_id int4 NULL,
	color varchar DEFAULT '#3788d8'::character varying NULL,
	CONSTRAINT event_pkey PRIMARY KEY (id),
	CONSTRAINT event_academic_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id) ON DELETE SET NULL,
	CONSTRAINT event_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES public.subject(id) ON DELETE SET NULL,
	CONSTRAINT event_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.user(id)
);

CREATE TABLE public.score (
	id serial4 NOT NULL,
	user_id int4 NOT NULL,
	name varchar NOT NULL,
	subject_id int4 NULL,
	score float4 NULL,
	date timestamptz NULL,
	status int4 NULL,
	course_id int4 NULL,
	color varchar NULL,
	CONSTRAINT score_pkey PRIMARY KEY (id),
	CONSTRAINT score_course_id_fkey FOREIGN KEY (course_id) REFERENCES public.course(id) ON DELETE SET NULL,
	CONSTRAINT score_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES public.subject(id) ON DELETE SET NULL,
	CONSTRAINT score_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.user(id)
);

-- Insertar datos iniciales en la tabla avatar
INSERT INTO public.avatar (id, url, title) VALUES (2, 'https://i.imgur.com/ojRTBSc.jpg', 'Axia');
INSERT INTO public.avatar (id, url, title) VALUES (6, 'https://i.imgur.com/NviZCL7.jpg', 'Leo');
INSERT INTO public.avatar (id, url, title) VALUES (3, 'https://i.imgur.com/hucpDF2.jpg', 'Camila');
INSERT INTO public.avatar (id, url, title) VALUES (5, 'https://i.imgur.com/A4TI4vD.jpg', 'Tom');
INSERT INTO public.avatar (id, url, title) VALUES (8, 'https://i.imgur.com/pKgQpDi.jpg', 'Axel');
INSERT INTO public.avatar (id, url, title) VALUES (7, 'https://i.imgur.com/WbuxCEL.jpg', 'Marcos');
INSERT INTO public.avatar (id, url, title) VALUES (1, 'https://i.imgur.com/BWuG51z.jpg', 'Aran');
INSERT INTO public.avatar (id, url, title) VALUES (4, 'https://i.imgur.com/v18m7Nx.jpg', 'Noah');
