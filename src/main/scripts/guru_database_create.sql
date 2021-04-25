create table category (id bigint not null auto_increment, department_name varchar(255) not null, version bigint, primary key (id)) engine=InnoDB;
create table ingredient (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), recipe_id bigint, unit_of_measure_id bigint, primary key (id)) engine=InnoDB;
create table notes (id bigint not null auto_increment, notes longtext, primary key (id)) engine=InnoDB;
create table recipe (id bigint not null auto_increment, cook_time integer not null, difficulty varchar(255), directions varchar(10480) not null, image longblob, name varchar(255) not null, prep_time integer not null, servings integer not null, source varchar(255) not null, url varchar(255) not null, notes_id bigint, primary key (id)) engine=InnoDB;
create table recipe_categories (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB;
create table unit_of_measure (id bigint not null auto_increment, units varchar(255), primary key (id)) engine=InnoDB;
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe (id);
alter table ingredient add constraint FK15ttfoaomqy1bbpo251fuidxw foreign key (unit_of_measure_id) references unit_of_measure (id);
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id);
alter table recipe_categories add constraint FK6pvvs3ogtv1r4nnnff6tdse11 foreign key (category_id) references category (id);
alter table recipe_categories add constraint FKf9uxi3701as945rybpx129buq foreign key (recipe_id) references recipe (id);