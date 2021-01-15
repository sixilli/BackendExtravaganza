--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

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
-- Name: trigger_set_timestamp(); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION public.trigger_set_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: blog_posts; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.blog_posts (
    id bigint NOT NULL,
    author character varying(255),
    category integer NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    link character varying(255),
    text character varying(255),
    title character varying(255),
    updated_at timestamp without time zone DEFAULT now() NOT NULL
);


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: schema_migrations; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.schema_migrations (
    version bigint NOT NULL,
    inserted_at timestamp(0) without time zone
);


--
-- Name: tournaments; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tournaments (
    id bigint NOT NULL,
    attendees integer NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    event_link character varying(255),
    location character varying(255),
    region character varying(255),
    stream_link character varying(255),
    title character varying(255),
    updated_at timestamp without time zone DEFAULT now() NOT NULL,
    start_time timestamp without time zone
);


--
-- Name: blog_posts blog_posts_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.blog_posts
    ADD CONSTRAINT blog_posts_pkey PRIMARY KEY (id);


--
-- Name: schema_migrations schema_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.schema_migrations
    ADD CONSTRAINT schema_migrations_pkey PRIMARY KEY (version);


--
-- Name: tournaments tournaments_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tournaments
    ADD CONSTRAINT tournaments_pkey PRIMARY KEY (id);


--
-- Name: blog_posts set_timestamp; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER set_timestamp BEFORE UPDATE ON public.blog_posts FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();


--
-- Name: tournaments set_timestamp; Type: TRIGGER; Schema: public; Owner: -
--

CREATE TRIGGER set_timestamp BEFORE UPDATE ON public.tournaments FOR EACH ROW EXECUTE FUNCTION public.trigger_set_timestamp();


--
-- PostgreSQL database dump complete
--

