-- usuario
insert into aluno values (1, "Maria", "da Silva", "111.222.333-44", "maria@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511955556666", "analista de m�dia social", "1", now(), now(),1);

insert into professor values (1, "Ronald", "Lima", "526.618.451-51", NULL, "ronald@gmail.com", "123teste", "M", STR_TO_DATE( "23/07/1986", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);
insert into professor values (2, "Jo�o", "Lima", "999.888.777-66", NULL, "joao@gmail.com", "123teste", "M", STR_TO_DATE( "02/02/1980", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);

insert into modulo values (1, "Hullo", 1.0, "0", now(), now());
insert into modulo values (2, "Introductions 1", 2.0 , "0", now(), now());
insert into modulo values (3, "Greetings 1", 1.1 , "0", now(), now());

insert into aula values (1, "hullo", 1, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (2, "how are you?", 2, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (3, "I am", 1, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 2);
insert into aula values (4, "He is", 2, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 2);