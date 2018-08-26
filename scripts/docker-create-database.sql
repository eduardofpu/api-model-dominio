-- Drop table

-- DROP TABLE public.name_table

CREATE TABLE public.name_table (
	id int8 NOT NULL,
	name_table varchar(255) NULL,
	CONSTRAINT name_table_pkey PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);

-- Drop table

-- DROP TABLE public.add_column

CREATE TABLE public.add_column (
	id int8 NOT NULL,
	data_type varchar(255) NULL,
	name_column varchar(255) NULL,
	id_table_id int8 NULL,
	CONSTRAINT add_column_pkey PRIMARY KEY (id),
	CONSTRAINT fkk482qpbob0ud38ha2vae2p5a1 FOREIGN KEY (id_table_id) REFERENCES name_table(id)
)
WITH (
	OIDS=FALSE
);
