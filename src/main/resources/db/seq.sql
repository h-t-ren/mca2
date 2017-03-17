set foreign_key_checks =0;

alter table `altrntv` 
modify `id_altrntv` int(11) not null auto_increment;


alter table `anlz` 
modify `id_anlz` int(11) not null auto_increment;


alter table `atrbt` 
modify `id_atrbt` int(11) not null auto_increment;


alter table `crtrn` 
modify `id_crtrn` int(11) not null auto_increment;


alter table `altrntv` 
modify `id_altrntv` int(11) not null auto_increment;


alter table `instnc` 
modify `id_instnc` int(11) not null auto_increment;


alter table `itr` 
modify `id_itr` int(11) not null auto_increment;



alter table `itr_ent_vl` 
modify `id_itr_ent_vl` int(11) not null auto_increment;

alter table `prblm` 
modify `id_prblm` int(11) not null auto_increment;


alter table `usr` 
modify `id_usr` int(11) not null auto_increment;


set foreign_key_checks =0;