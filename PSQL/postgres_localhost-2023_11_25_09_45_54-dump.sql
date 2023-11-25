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
    id uuid NOT NULL,
    title character varying
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
    id uuid NOT NULL,
    user_id uuid NOT NULL,
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
    users_id uuid
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
    id uuid NOT NULL,
    first_name character varying,
    name character varying,
    patronymic character varying,
    bithday timestamp without time zone,
    foto bytea
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

COPY public.chat (id, title) FROM stdin;
c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	chat1
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
14	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	fc0a8030-2693-4e94-9d2a-881337a61ff2
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
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, first_name, name, patronymic, bithday, foto) FROM stdin;
e40356db-86b1-4e84-8421-6b418271e8f1	User1	User1	User1	2023-10-21 09:51:29	\N
8aabb47e-90ac-4404-93da-788df76901a9	User2	User2	User2	2023-10-21 09:51:29	\N
3cce3619-6bdb-4d1b-a84a-7eaa47737de1	User3	User3	User3	2023-10-21 06:59:52.614105	\N
4cc646b7-2775-e839-8067-38a3a4efb56d	Ivan	Ivanov	Ivanovich	1990-10-05 00:00:00	\N
\.


--
-- Data for Name: users_in_chat; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users_in_chat (id, chat_id, users_id) FROM stdin;
1	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	e40356db-86b1-4e84-8421-6b418271e8f1
2	c663b9b1-7b42-40b9-9cbf-dfcad8ff2a25	e40356db-86b1-4e84-8421-6b418271e8f1
\.


--
-- Name: history_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.history_id_seq', 14, true);


--
-- Name: uic_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.uic_id_seq', 2, true);


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
    ADD CONSTRAINT uic_user_id_fk FOREIGN KEY (users_id) REFERENCES public."user"(id);


--
-- PostgreSQL database dump complete
--

