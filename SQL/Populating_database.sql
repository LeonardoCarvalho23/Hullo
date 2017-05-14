-- alunos
insert into aluno values (1, "Maria", "da Silva", "154.135.576-80", "maria@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511985445903", "analista de mídia social", "1", now(), now(),1);
insert into aluno values (2, "Ana", "Maria", "074.464.473-97", "ana@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511955556666", "analista de mídia social", "1", now(), now(),1);

-- professores
insert into professor values (1, "Ronald", "Lima", "526.618.451-51", "74.786.347/0001-48", "ronald@gmail.com", "123teste", "M", STR_TO_DATE( "23/07/1986", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);
insert into professor values (2, "João", "Lima", "999.888.777-66", "73.688.624/0001-17", "joao@gmail.com", "123teste", "M", STR_TO_DATE( "02/02/1980", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);

-- modulos
insert into modulo values (1, "Hullo", 1.0, "1", now(), now());
insert into modulo values (2, "Introductions 1", 2.0 , "1", now(), now());
insert into modulo values (3, "Greetings 1", 1.1 , "0", now(), now());

-- aulas
insert into aula values (1, "hullo", 1, "A", "review class 1", "this is the content class 1", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (2, "how are you?", 2, "A", "review class 2", "this is the content class 2", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (3, "I am", 3, "A", "review class 3", "this is the content class 3", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (4, "She is", 4, "A", "review class 4", "this is the content class 4", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (5, "They are", 5, "A", "review class 5", "this is the content class 5", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (6, "They are second class", 1, "B", "review class 1B", "this is the content class 1B", "nice activity", "teaser?", "1", now(), now(), 1);
insert into aula values (7, "hullo", 1, "A", "review class 1", "this is the content class 1", "nice activity", "teaser?", "1", now(), now(), 2);

-- aulas realizadas
insert into aula_realizada (id_aula_realizada, 
							nota_model_aula_realizada, 
							nota_practice_aula_realizada, 
                            nota_production_aula_realizada,
                            comentario_aula_realizada,
                            status_aula_realizada,
                            dt_criacao_aula_realizada,
                            dt_inicio_chamada_aula_realizada,
                            dt_fim_chamada_aula_realizada,
                            id_aula_aula_realizada,
                            id_aluno_aula_realizada,
                            id_professor_aula_realizada) 
	values (1, 5, 4, 4, "aluno conseguiu entender e reproduzir bem as estruturas, necessita melhorar um pouco o sotaque", "Realizada", STR_TO_DATE( "28/04/2017 10:10:10", "%d/%m/%Y %H:%i:%s" ), STR_TO_DATE( "29/04/2017 13:10:10", "%d/%m/%Y %H:%i:%s" ), STR_TO_DATE( "29/04/2017 13:15:10", "%d/%m/%Y %H:%i:%s" ), 1, 1, 1);

insert into aula_realizada (id_aula_realizada, 
							nota_model_aula_realizada, 
							nota_practice_aula_realizada, 
                            nota_production_aula_realizada,
                            comentario_aula_realizada,
                            status_aula_realizada,
                            dt_criacao_aula_realizada,
                            dt_inicio_chamada_aula_realizada,
                            dt_fim_chamada_aula_realizada,
                            id_aula_aula_realizada,
                            id_aluno_aula_realizada,
                            id_professor_aula_realizada) 
	values (2, 4, 3, 3, "aluno conseguiu reproduzir a produzir maior parte, problema com o verbo", "Realizada", STR_TO_DATE( "30/04/2017 08:10:10", "%d/%m/%Y %H:%i:%s" ), STR_TO_DATE( "30/04/2017 18:10:10", "%d/%m/%Y %H:%i:%s" ), STR_TO_DATE( "30/04/2017 18:15:10", "%d/%m/%Y %H:%i:%s" ), 1, 2, 1);

insert into aula_realizada (id_aula_realizada,
							id_anterior_aula_realizada,
                            dt_criacao_aula_realizada,
                            dt_inicio_chamada_aula_realizada,
                            status_aula_realizada,
                            id_aula_aula_realizada,
                            id_aluno_aula_realizada,
                            id_professor_aula_realizada) 
	values (3, 1, STR_TO_DATE( "30/04/2017 11:15:10", "%d/%m/%Y %H:%i:%s" ), STR_TO_DATE( "30/04/2017 18:10:10", "%d/%m/%Y %H:%i:%s" ), "Não atendida", 2, 1, 1);

	insert into aula_realizada (id_aula_realizada,
							id_anterior_aula_realizada,
                            dt_criacao_aula_realizada,
                            dt_inicio_chamada_aula_realizada,
                            status_aula_realizada,
                            id_aula_aula_realizada,
                            id_aluno_aula_realizada,
                            id_professor_aula_realizada) 
	values (4, 2, STR_TO_DATE( "30/04/2017 21:15:10", "%d/%m/%Y %H:%i:%s" ), STR_TO_DATE( "01/05/2017 18:10:10", "%d/%m/%Y %H:%i:%s" ), "Não atendida",5, 2, 1);
    
insert into aula_realizada (id_aula_realizada,
							id_anterior_aula_realizada,
                            dt_criacao_aula_realizada,
                            status_aula_realizada,
                            id_aula_aula_realizada,
                            id_aluno_aula_realizada,
                            id_professor_aula_realizada) 
	values (5, 1, STR_TO_DATE( "30/04/2017 11:15:10", "%d/%m/%Y %H:%i:%s" ),"Criada", 2, 1, 1);

	insert into aula_realizada (id_aula_realizada,
							id_anterior_aula_realizada,
                            dt_criacao_aula_realizada,
                            status_aula_realizada,
                            id_aula_aula_realizada,
                            id_aluno_aula_realizada,
                            id_professor_aula_realizada) 
	values (6, 2, STR_TO_DATE( "01/05/2017 21:15:10", "%d/%m/%Y %H:%i:%s" ), "Criada",5, 2, 1);
    