-- alunos
insert into aluno values (1, "Maria", "da Silva", "154.135.576-80", "maria@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511955556666", "analista de mídia social", "1", now(), now(),1);
insert into aluno values (2, "Ana", "Maria", "074.464.473-97", "ana@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511955556666", "analista de mídia social", "1", now(), now(),1);

-- professores
insert into professor values (1, "Ronald", "Lima", "526.618.451-51", "74.786.347/0001-48", "ronald@gmail.com", "123teste", "M", STR_TO_DATE( "23/07/1986", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);
insert into professor values (2, "João", "Lima", "999.888.777-66", "73.688.624/0001-17", "joao@gmail.com", "123teste", "M", STR_TO_DATE( "02/02/1980", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);

-- modulos
insert into modulo values (1, "Hullo", 1.0, "1", now(), now());
insert into modulo values (2, "Introductions 1", 2.0 , "0", now(), now());
insert into modulo values (3, "Greetings 1", 1.1 , "0", now(), now());

-- aulas
insert into aula values (1, "hullo", 1, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (2, "how are you?", 2, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (3, "I am", 3, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (4, "She is", 4, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (5, "They are", 5, "A", "review this class", "this is the content", "nice activity", "teaser?", "1", now(), now(), 1);