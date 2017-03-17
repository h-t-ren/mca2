
CREATE TABLE altrntv
(
	id_altrntv            INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	dscr                  VARCHAR(2048) NULL,
	id_prblm              INTEGER NULL,
	seq                   INTEGER NULL
)
;



ALTER TABLE altrntv
	ADD  PRIMARY KEY (id_altrntv)
;



CREATE TABLE altrntv_vl
(
	id_altrntv            INTEGER NOT NULL,
	id_atrbt              INTEGER NOT NULL,
	org_value             FLOAT NULL,
	norm_value            FLOAT NULL
)
;



ALTER TABLE altrntv_vl
	ADD  PRIMARY KEY (id_altrntv,id_atrbt)
;



CREATE TABLE anlz
(
	id_anlz               INTEGER NULL,
	seq                   INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	dscr                  VARCHAR(2048) NULL,
	note                  VARCHAR(2048) NULL,
	created               TIMESTAMP NULL,
	pblsh                 INTEGER NULL,
	id_usr                INTEGER NULL,
	id_instnc             INTEGER NULL
)
;



ALTER TABLE anlz
	ADD  PRIMARY KEY (id_anlz)
;



CREATE TABLE atrbt
(
	id_atrbt              INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	dscr                  VARCHAR(2048) NULL,
	unit                  VARCHAR(64) NULL,
	id_prblm              INTEGER NULL
)
;



ALTER TABLE atrbt
	ADD  PRIMARY KEY (id_atrbt)
;



CREATE TABLE crtrn
(
	id_crtrn              INTEGER NULL,
	id_atrbt              INTEGER NULL,
	id_instnc             INTEGER NULL,
	parent                INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	dscr                  VARCHAR(2048) NULL,
	type                  VARCHAR(6) NULL,
	org_target            FLOAT NULL,
	org_value_min         FLOAT NULL,
	org_value_max         FLOAT NULL,
	seq                   INTEGER NULL,
	lvl                   INTEGER NULL,
	top_set               INTEGER NULL,
	id_outvar             INTEGER NULL,
	id_var                INTEGER NULL,
	button_display        INTEGER NULL
)
;



ALTER TABLE crtrn
	ADD  PRIMARY KEY (id_crtrn)
;



CREATE TABLE ent_hnt
(
	id_ent                INTEGER NULL,
	seq                   INTEGER NULL,
	hnt                   VARCHAR(128) NULL
)
;



ALTER TABLE ent_hnt
	ADD  PRIMARY KEY (id_ent,seq)
;



CREATE TABLE instnc
(
	id_instnc             INTEGER NULL,
	snm                   VARCHAR(17) NULL,
	nm                    VARCHAR(128) NULL,
	dscr                  VARCHAR(2048) NULL,
	id_prblm              INTEGER NULL,
	id_usr                INTEGER NULL,
	created               TIMESTAMP NULL,
	pblsh                 INTEGER NULL
)
;



ALTER TABLE instnc
	ADD  PRIMARY KEY (id_instnc)
;



CREATE TABLE itr
(
	id_itr                INTEGER NULL,
	id_method             INTEGER NULL,
	parent                INTEGER NULL,
	created               TIMESTAMP NULL,
	lastmdfy              TIMESTAMP NULL,
	status                INTEGER NULL,
	note                  VARCHAR(2048) NULL,
	id_anlz               INTEGER NULL,
	id_usr                INTEGER NULL
)
;



ALTER TABLE itr
	ADD  PRIMARY KEY (id_itr)
;



CREATE TABLE itr_alter
(
	id_itr                INTEGER NOT NULL,
	id_atrbt              INTEGER NOT NULL,
	id_altrntv            INTEGER NOT NULL,
	pos                   INTEGER NULL,
	caf                   FLOAT NULL
)
;



ALTER TABLE itr_alter
	ADD  PRIMARY KEY (id_itr,id_atrbt,id_altrntv)
;



CREATE TABLE itr_boxplot
(
	id_itr                INTEGER NOT NULL,
	id_crtrn              INTEGER NOT NULL,
	lft                   INTEGER NULL,
	mdl                   INTEGER NULL,
	rght                  INTEGER NULL
)
;



ALTER TABLE itr_boxplot
	ADD  PRIMARY KEY (id_itr,id_crtrn)
;



CREATE TABLE itr_dots
(
	id_itr                INTEGER NOT NULL,
	id_crtrn              INTEGER NOT NULL,
	pos                   INTEGER NULL,
	label                 VARCHAR(4000) NULL,
	ndots                 INTEGER NULL
)
;



ALTER TABLE itr_dots
	ADD  PRIMARY KEY (id_itr,id_crtrn,pos)
;



CREATE TABLE itr_ent_vl
(
	id_itr_ent_vl         INTEGER NULL,
	id_itr                INTEGER NULL,
	id_ent                INTEGER NULL,
	value                 FLOAT NULL,
	object_id             INTEGER NULL,
	object_id2            INTEGER NULL
)
;



ALTER TABLE itr_ent_vl
	ADD  PRIMARY KEY (id_itr_ent_vl)
;



CREATE TABLE itr_msg
(
	id_itr                INTEGER NOT NULL,
	seq                   INTEGER NULL,
	msg_type              INTEGER NULL,
	txt                   VARCHAR(4000) NULL
)
;



ALTER TABLE itr_msg
	ADD  PRIMARY KEY (id_itr,seq)
;



CREATE TABLE prblm
(
	id_prblm              INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	dscr                  VARCHAR(2048) NULL,
	id_usr                INTEGER NULL,
	created               TIMESTAMP NULL,
	parent                INTEGER NULL,
	n_cols                INTEGER NULL,
	n_rows                INTEGER NULL,
	n_nzeros              INTEGER NULL,
	pblsh                 INTEGER NULL
)
;



ALTER TABLE prblm
	ADD  PRIMARY KEY (id_prblm)
;



CREATE TABLE spc_ent
(
	id_ent                INTEGER NULL,
	ent_type              INTEGER NULL,
	value_id              INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	seq                   INTEGER NULL
)
;



ALTER TABLE spc_ent
	ADD  PRIMARY KEY (id_ent)
;



CREATE TABLE spc_mthd
(
	id_spc_mthd           INTEGER NULL,
	id_mthd               INTEGER NULL,
	id_ent                INTEGER NULL,
	def_by                INTEGER NULL,
	is_req                INTEGER NULL
)
;



ALTER TABLE spc_mthd
	ADD  PRIMARY KEY (id_spc_mthd)
;



CREATE TABLE spc_mthd_dict
(
	id_mthd               INTEGER NULL,
	snm                   VARCHAR(16) NULL,
	nm                    VARCHAR(128) NULL,
	pblsh                 INTEGER NULL,
	seq                   INTEGER NULL
)
;



ALTER TABLE spc_mthd_dict
	ADD  PRIMARY KEY (id_mthd)
;



CREATE TABLE t_time
(
	id_itr                INTEGER NULL,
	ins_name              VARCHAR(1024) NULL,
	p_time                INTEGER NULL,
	c_date                VARCHAR(254) NULL,
	r_time                INTEGER NULL,
	s_time                INTEGER NULL,
	d_time                INTEGER NULL,
	solver_t              INTEGER NULL,
	solver_cpu            INTEGER NULL,
	ip                    VARCHAR(1024) NULL
)
;



ALTER TABLE t_time
	ADD  PRIMARY KEY (id_itr)
;



CREATE TABLE usr
(
	id_usr                INTEGER NULL,
	login                 VARCHAR(64) NULL,
	is_disabled           INTEGER NULL,
	password              VARCHAR(256) NULL
)
;



ALTER TABLE usr
	ADD  PRIMARY KEY (id_usr)
;



ALTER TABLE altrntv_vl
	ADD FOREIGN KEY R_7 (id_altrntv) REFERENCES altrntv(id_altrntv)
;


ALTER TABLE altrntv_vl
	ADD FOREIGN KEY R_9 (id_atrbt) REFERENCES atrbt(id_atrbt)
;



ALTER TABLE anlz
	ADD FOREIGN KEY R_13 (id_usr) REFERENCES usr(id_usr)
;


ALTER TABLE anlz
	ADD FOREIGN KEY R_14 (id_instnc) REFERENCES instnc(id_instnc)
;



ALTER TABLE atrbt
	ADD FOREIGN KEY R_6 (id_prblm) REFERENCES prblm(id_prblm)
;



ALTER TABLE crtrn
	ADD FOREIGN KEY R_10 (parent) REFERENCES crtrn(id_crtrn)
;


ALTER TABLE crtrn
	ADD FOREIGN KEY R_11 (id_atrbt) REFERENCES atrbt(id_atrbt)
;


ALTER TABLE crtrn
	ADD FOREIGN KEY R_12 (id_instnc) REFERENCES instnc(id_instnc)
;



ALTER TABLE instnc
	ADD FOREIGN KEY R_3 (id_usr) REFERENCES usr(id_usr)
;


ALTER TABLE instnc
	ADD FOREIGN KEY R_4 (id_prblm) REFERENCES prblm(id_prblm)
;



ALTER TABLE itr
	ADD FOREIGN KEY R_15 (parent) REFERENCES itr(id_itr)
;


ALTER TABLE itr
	ADD FOREIGN KEY R_18 (id_anlz) REFERENCES anlz(id_anlz)
;


ALTER TABLE itr
	ADD FOREIGN KEY R_19 (id_usr) REFERENCES usr(id_usr)
;



ALTER TABLE itr_alter
	ADD FOREIGN KEY R_20 (id_itr) REFERENCES itr(id_itr)
;


ALTER TABLE itr_alter
	ADD FOREIGN KEY R_21 (id_atrbt) REFERENCES atrbt(id_atrbt)
;


ALTER TABLE itr_alter
	ADD FOREIGN KEY R_22 (id_altrntv) REFERENCES altrntv(id_altrntv)
;



ALTER TABLE itr_boxplot
	ADD FOREIGN KEY R_23 (id_itr) REFERENCES itr(id_itr)
;


ALTER TABLE itr_boxplot
	ADD FOREIGN KEY R_24 (id_crtrn) REFERENCES crtrn(id_crtrn)
;



ALTER TABLE itr_dots
	ADD FOREIGN KEY R_25 (id_itr) REFERENCES itr(id_itr)
;


ALTER TABLE itr_dots
	ADD FOREIGN KEY R_26 (id_crtrn) REFERENCES crtrn(id_crtrn)
;



ALTER TABLE itr_ent_vl
	ADD FOREIGN KEY R_27 (id_itr) REFERENCES itr(id_itr)
;


ALTER TABLE itr_ent_vl
	ADD FOREIGN KEY R_28 (id_ent) REFERENCES spc_ent(id_ent)
;



ALTER TABLE itr_msg
	ADD FOREIGN KEY R_29 (id_itr) REFERENCES itr(id_itr)
;



ALTER TABLE prblm
	ADD FOREIGN KEY R_1 (id_usr) REFERENCES usr(id_usr)
;


ALTER TABLE prblm
	ADD FOREIGN KEY R_2 (parent) REFERENCES prblm(id_prblm)
;



ALTER TABLE spc_mthd
	ADD FOREIGN KEY R_16 (id_mthd) REFERENCES spc_mthd_dict(id_mthd)
;


ALTER TABLE spc_mthd
	ADD FOREIGN KEY R_17 (id_ent) REFERENCES spc_ent(id_ent)
;


