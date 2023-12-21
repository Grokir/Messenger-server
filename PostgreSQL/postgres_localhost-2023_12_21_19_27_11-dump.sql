--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: chat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.chat (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    title character varying,
    is_delete boolean DEFAULT false NOT NULL
);


ALTER TABLE public.chat OWNER TO postgres;

--
-- Name: history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.history (
    id integer NOT NULL,
    chat_id uuid NOT NULL,
    msg_id uuid NOT NULL
);


ALTER TABLE public.history OWNER TO postgres;

--
-- Name: history_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.history_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.history_id_seq OWNER TO postgres;

--
-- Name: history_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.history_id_seq OWNED BY public.history.id;


--
-- Name: msg; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.msg (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    user_id uuid,
    message character varying NOT NULL,
    time_message timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.msg OWNER TO postgres;

--
-- Name: users_in_chat; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users_in_chat (
    id integer NOT NULL,
    chat_id uuid,
    user_id uuid,
    was_deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE public.users_in_chat OWNER TO postgres;

--
-- Name: uic_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.uic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.uic_id_seq OWNER TO postgres;

--
-- Name: uic_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.uic_id_seq OWNED BY public.users_in_chat.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id uuid DEFAULT gen_random_uuid() NOT NULL,
    name character varying,
    surname character varying,
    foto bytea,
    password character varying,
    login character varying NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: history id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history ALTER COLUMN id SET DEFAULT nextval('public.history_id_seq'::regclass);


--
-- Name: users_in_chat id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_in_chat ALTER COLUMN id SET DEFAULT nextval('public.uic_id_seq'::regclass);


--
-- Data for Name: chat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.chat (id, title, is_delete) FROM stdin;
c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	chat1	f
83f3dfef-4f58-4d69-8f50-0029f891194c	chat2	f
4093711b-6299-4550-8450-6679f33bd9fe	NewChat	f
\.


--
-- Data for Name: history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.history (id, chat_id, msg_id) FROM stdin;
1	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	f8816260-afb0-46b7-a72f-eb36132f7db3
2	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	d90ace82-4710-4934-af73-2e4ee3257cc6
3	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	e3e21550-995b-4960-8773-9563165750b4
4	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	6f25a5ee-d899-4a28-9a4d-6380632114a8
5	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	f7ae828b-8993-4a43-a86f-93d20598ff95
6	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	f2bf2a25-ecca-41ee-91ae-cdfeac3f56c7
7	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	7123f848-77c3-42db-9826-8977f6e9a1ca
8	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	66c9c965-0627-4d4e-909c-2e9d5770a0e3
9	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	5f0a5186-0f78-411e-a9a8-73d17977402f
10	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	fc0a8030-2693-4e94-9d2a-881337a61ff2
11	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	fc0a8030-2693-4e94-9d2a-881337a61ff2
12	4093711b-6299-4550-8450-6679f33bd9fe	0a016052-5bc7-41af-93c3-f509177f8f37
\.


--
-- Data for Name: msg; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.msg (id, user_id, message, time_message) FROM stdin;
f8816260-afb0-46b7-a72f-eb36132f7db3	e40356db-86b1-4e84-8421-6b418271e8f1	msg1	2023-10-21 06:53:27.047576
d90ace82-4710-4934-af73-2e4ee3257cc6	8aabb47e-90ac-4404-93da-788df76901a9	msg2	2023-10-21 06:53:27.047576
e3e21550-995b-4960-8773-9563165750b4	e40356db-86b1-4e84-8421-6b418271e8f1	msg3	2023-10-21 06:53:27.047576
6f25a5ee-d899-4a28-9a4d-6380632114a8	8aabb47e-90ac-4404-93da-788df76901a9	msg4	2023-10-21 06:53:27.047576
f7ae828b-8993-4a43-a86f-93d20598ff95	e40356db-86b1-4e84-8421-6b418271e8f1	msg5	2023-10-21 06:53:27.047576
f2bf2a25-ecca-41ee-91ae-cdfeac3f56c7	8aabb47e-90ac-4404-93da-788df76901a9	msg6	2023-10-21 06:53:27.047576
7123f848-77c3-42db-9826-8977f6e9a1ca	e40356db-86b1-4e84-8421-6b418271e8f1	msg7	2023-10-21 06:53:27.047576
66c9c965-0627-4d4e-909c-2e9d5770a0e3	8aabb47e-90ac-4404-93da-788df76901a9	msg8	2023-10-21 06:53:27.047576
5f0a5186-0f78-411e-a9a8-73d17977402f	e40356db-86b1-4e84-8421-6b418271e8f1	msg9	2023-10-21 06:53:27.047576
fc0a8030-2693-4e94-9d2a-881337a61ff2	8aabb47e-90ac-4404-93da-788df76901a9	msg10	2023-10-21 06:53:27.047576
0a016052-5bc7-41af-93c3-f509177f8f37	1273d700-736c-4f98-a7ac-bbe9312d20b4	Hello world!	2023-12-16 20:49:48.967085
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, name, surname, foto, password, login) FROM stdin;
054efe67-9dc8-426e-beb7-c264258d92b0	Petr	Petrov	\N	Petr_pass	Petr_login
1273d700-736c-4f98-a7ac-bbe9312d20b4	Petr	Ivanov	\N	674259d469ced56235384aaf931ad1e62276276bcfe75cf53c750ffb3bab8e2b	Petr_I
dfbdc128-be1c-468d-8918-5ef2f3d3f7b6	Pasha	Pavlov	\N	41c4b7ff426f41dae062f949951a67ccf285dc89aada0915d6704d02c2223667	Login
4cc646b7-2775-e839-8067-38a3a4efb56d	Ivan	Ivanov	\N	\N	IVA
3cce3619-6bdb-4d1b-a84a-7eaa47737de1	User3	User3	\N	\N	usr_3
e40356db-86b1-4e84-8421-6b418271e8f1	User1	User1	\N	\N	usr_1
8aabb47e-90ac-4404-93da-788df76901a9	User2	User2	\N	\N	usr_2
370a8941-5c70-4b4f-a318-7d4ebbcdac18	Petr	Petrovich	\N	pass	PPP 3
d150ca4a-7c79-4b76-ad50-b9e35fde2b7d	DELETED	DELETED	\\xefbbbf	some_pass	some_login
d12e9961-e974-4976-82de-c10a3f084c49	Test user		\N	ee677c8850634d9dfb40d330d2ca61b933e6349c35d233c5bcd8e0cfbe082cd6	TEST
\.


--
-- Data for Name: users_in_chat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_in_chat (id, chat_id, user_id, was_deleted) FROM stdin;
1	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	e40356db-86b1-4e84-8421-6b418271e8f1	f
2	4093711b-6299-4550-8450-6679f33bd9fe	370a8941-5c70-4b4f-a318-7d4ebbcdac18	f
4	4093711b-6299-4550-8450-6679f33bd9fe	8aabb47e-90ac-4404-93da-788df76901a9	f
3	4093711b-6299-4550-8450-6679f33bd9fe	1273d700-736c-4f98-a7ac-bbe9312d20b4	f
6	4093711b-6299-4550-8450-6679f33bd9fe	4cc646b7-2775-e839-8067-38a3a4efb56d	f
5	4093711b-6299-4550-8450-6679f33bd9fe	e40356db-86b1-4e84-8421-6b418271e8f1	f
\.


--
-- Name: history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.history_id_seq', 15, true);


--
-- Name: uic_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uic_id_seq', 19, true);


--
-- Name: chat chat_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.chat
    ADD CONSTRAINT chat_pk PRIMARY KEY (id);


--
-- Name: history history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_pk PRIMARY KEY (id);


--
-- Name: msg msg_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.msg
    ADD CONSTRAINT msg_pk PRIMARY KEY (id);


--
-- Name: users_in_chat uic_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_in_chat
    ADD CONSTRAINT uic_pk PRIMARY KEY (id);


--
-- Name: user user_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_login_key UNIQUE (login);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: history history_chat_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_chat_id_fk FOREIGN KEY (chat_id) REFERENCES public.chat(id);


--
-- Name: history history_msg_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.history
    ADD CONSTRAINT history_msg_id_fk FOREIGN KEY (msg_id) REFERENCES public.msg(id);


--
-- Name: msg msg_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.msg
    ADD CONSTRAINT msg_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: users_in_chat uic_chat_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_in_chat
    ADD CONSTRAINT uic_chat_id_fk FOREIGN KEY (chat_id) REFERENCES public.chat(id);


--
-- Name: users_in_chat uic_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users_in_chat
    ADD CONSTRAINT uic_user_id_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- PostgreSQL database dump complete
--

