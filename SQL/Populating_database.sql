-- alunos
insert into aluno values (1, "Maria", "da Silva", "154.135.576-80", "maria@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511985445903", "analista de mídia social", "1", now(), now(),1);
insert into aluno values (2, "Ana", "Maria", "074.464.473-97", "ana@gmail.com", "teste123", "F", STR_TO_DATE( "01/01/1990", "%d/%m/%Y" ), "5511998978999", "analista de mídia social", "1", now(), now(),1);

-- professores
insert into professor values (1, "Ronald", "Lima", "526.618.451-51", "74.786.347/0001-48", "ronald@gmail.com", "123teste", "M", STR_TO_DATE( "23/07/1986", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);
insert into professor values (2, "João", "Lima", "999.888.777-66", "73.688.624/0001-17", "joao@gmail.com", "123teste", "M", STR_TO_DATE( "02/02/1980", "%d/%m/%Y" ), "5511988887777", NULL, "1", now(), now(),1);

-- modulos
insert into modulo values (1, "Hullo", 1.0, "1", now(), now());
insert into modulo values (2, "Introductions 1", 2.0 , "1", now(), now());
insert into modulo values (3, "Greetings 1", 1.1 , "0", now(), now());

-- aulas
INSERT INTO `aula` VALUES (1,'Talk about yourself',1,'A','<p><strong>Greeting/Introducing yourself</strong></p>\r\n\r\n<p>Greet the student in English. Speak in a natural tone and be careful not to be too fast.</p>\r\n\r\n<blockquote>\r\n<p><em>Hi, ___________, my name is __________. </em></p>\r\n\r\n<p><em>I am your English teacher today. </em>Or <em>I will be your English teacher today.</em></p>\r\n</blockquote>\r\n\r\n<p><strong><em>Can you hear me well?</em></strong></p>\r\n\r\n<p>If the student does not interact with you in this moment, use portuguese immediately after the greeting. With basic students, we can use either English or Portuguese. Our priority is to use English, but we have no intention to intimidate students. Be careful with that!</p>\r\n\r\n<blockquote>\r\n<p><em>Good, let&rsquo;s start?</em></p>\r\n\r\n<p><em>Let&rsquo;s talk about yourself? </em></p>\r\n</blockquote>\r\n\r\n<blockquote>\r\n<p><em>Talk about yourself. </em></p>\r\n</blockquote>\r\n\r\n<p>If during production the student still has problems to interact with you, give hints and use verbal cues:</p>\r\n\r\n<blockquote>\r\n<p><em>Your name?!</em></p>\r\n\r\n<p><em>Your age?! (idade)</em></p>\r\n\r\n<p><em>Your location?! (onde mora)</em></p>\r\n\r\n<p><em>Your marital status (casado? solteiro?)</em></p>\r\n</blockquote>\r\n','<p>In this call the student will talk about himself/herself:<br />\r\nGive personal information.</p>\r\n\r\n<p><strong>Greeting/Introducing yourself</strong></p>\r\n\r\n<p>Greet the student in English. Speak in a natural tone and be careful not to be too fast.</p>\r\n\r\n<blockquote>\r\n<p><em>Hi, ___________, my name is __________. </em></p>\r\n\r\n<p><em>I am your English teacher today. </em>Or <em>I will be your English teacher today.</em></p>\r\n</blockquote>\r\n\r\n<p><strong><em>Can you hear me well?</em></strong></p>\r\n\r\n<p>If the student does not interact with you in this moment, use portuguese immediately after the greeting. With basic students, we can use either English or Portuguese. Our priority is to use English, but we have no intention to intimidate students. Be careful with that!</p>\r\n\r\n<blockquote>\r\n<p><em>Good, let&rsquo;s start?</em></p>\r\n\r\n<p><em>Let&rsquo;s talk about yourself? </em></p>\r\n</blockquote>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Model sentences</strong></p>\r\n\r\n<p>From now on, you will model sentences: ask-listen and/or model-ask-listen.</p>\r\n\r\n<blockquote>\r\n<p><em>What&rsquo;s your full name? </em></p>\r\n</blockquote>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question.</p>\r\n\r\n<blockquote>\r\n<p><em>What&rsquo;s your complete name? </em></p>\r\n</blockquote>\r\n\r\n<p>If the student does not interact with you in this moment, model the answer and immediately ask the question.</p>\r\n\r\n<blockquote>\r\n<p><em>My name is Simone Telles Martins Ramos</em></p>\r\n\r\n<p><em>What&rsquo;s your name?</em></p>\r\n</blockquote>\r\n\r\n<p>After the student replies, continue the dialogue.</p>\r\n\r\n<blockquote>\r\n<p><em>(student&rsquo;s name), I am 40 years old. </em></p>\r\n\r\n<p><em>How old are you?</em></p>\r\n</blockquote>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question or use portuguese.</p>\r\n\r\n<p>After the student reply, correct pronunciation if necessary. Then, continue the dialogue.</p>\r\n\r\n<blockquote>\r\n<p><em>(student&rsquo;s name), I live in S&atilde;o Paulo?</em></p>\r\n\r\n<p><em>Where do you live?</em></p>\r\n</blockquote>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question or use portuguese.</p>\r\n\r\n<p>After the student reply, correct pronunciation if necessary. Then, continue the dialogue.</p>\r\n\r\n<blockquote>\r\n<p><em>______, I am married. </em></p>\r\n\r\n<p><em>And you, (student&rsquo;s name)?</em></p>\r\n\r\n<p><em>Are you married or single?</em></p>\r\n</blockquote>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question or use portuguese. Explain: married = casado(a), single = solteiro(a), widow/widower = vi&uacute;vo(a).</p>\r\n\r\n<p>After the student reply, correct pronunciation if necessary. Then, continue the dialogue.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Practice</strong></p>\r\n\r\n<blockquote>\r\n<p><em>What&rsquo;s your full name? Or What&rsquo;s your complete name? </em></p>\r\n\r\n<p><em>How old are you?</em></p>\r\n\r\n<p><em>Where do you live?</em></p>\r\n\r\n<p><em>Are you married or single?</em></p>\r\n</blockquote>\r\n\r\n<ol>\r\n	<li>during practice the student still has problems to interact with you, model the sentences again. If after modeling, the student continues to present interaction difficulties, you can use portuguese to help or ask the student to try to answer the question using the multimodal material (this is the moment to test its needs and efficiency).</li>\r\n</ol>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Production</strong></p>\r\n\r\n<p>Now encourage your student to give you his/her personal information.</p>\r\n\r\n<blockquote>\r\n<p><em>Now, let&rsquo;s practice more. </em></p>\r\n\r\n<p><em>Talk about yourself. </em></p>\r\n</blockquote>\r\n\r\n<p>If during production the student still has problems to interact with you, give hints and use verbal cues:</p>\r\n\r\n<blockquote>\r\n<p><em>Your name?!</em></p>\r\n\r\n<p><em>Your age?! (idade)</em></p>\r\n\r\n<p><em>Your location?! (onde mora)</em></p>\r\n\r\n<p><em>Your marital status (casado? solteiro?)</em></p>\r\n</blockquote>\r\n\r\n<p>If after the hints, the student continues to present interaction difficulties, it means that he needs more practice and we should encourage student to read the material again and make contact to receive another phone call.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Ending the call</strong></p>\r\n\r\n<blockquote>\r\n<p><em>(student&rsquo;s name), it is time to finish our class.</em></p>\r\n\r\n<p><em>Voc&ecirc; atingiu o objetivo de aprendizagem que era &ldquo;talk about yourself&rdquo; , ou seja, fornecer informa&ccedil;&otilde;es pessoais.</em></p>\r\n\r\n<p><em>Por favor, (re)leia o material interativo e pratique com as atividades do APP. </em></p>\r\n\r\n<p><em>Nice talking to you! Foi muito bom conversar com voc&ecirc;.</em></p>\r\n\r\n<p><em>Thank you, good-bye</em></p>\r\n</blockquote>\r\n','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-20 15:27:07',1),(2,'how are you?',2,'A','review class 2','this is the content class 2','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-15 20:07:18',1),(3,'I am',3,'A','review class 3','this is the content class 3','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-15 20:07:18',1),(4,'She is',4,'A','review class 4','this is the content class 4','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-15 20:07:18',1),(5,'They are',5,'A','review class 5','this is the content class 5','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-15 20:07:18',1),(6,'They are second class',1,'B','review class 1B','this is the content class 1B','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-15 20:07:18',1),(7,'hullo',1,'A','<p>review class 1</p>\r\n','<p><strong>Interacting with the student</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Greeting/Introducing yourself</strong></p>\r\n\r\n<p>Greet the student in English. Speak in a natural tone and be careful not to be too fast.</p>\r\n\r\n<p><em>Hi, ___________, my name is __________. </em></p>\r\n\r\n<p><em>I am your English teacher today. </em>Or <em>I will be your English teacher today.</em></p>\r\n\r\n<p><strong><em>Can you hear me well?</em></strong></p>\r\n\r\n<p>If the student does not interact with you in this moment, use portuguese immediately after the greeting. With basic students, we can use either English or Portuguese. Our priority is to use English, but we have no intention to intimidate students. Be careful with that!</p>\r\n\r\n<p><em>Good, let&rsquo;s start?</em></p>\r\n\r\n<p><em>Let&rsquo;s talk about yourself? </em></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Model sentences</strong></p>\r\n\r\n<p>From now on, you will model sentences: ask-listen and/or model-ask-listen.</p>\r\n\r\n<p><em>What&rsquo;s your full name? </em></p>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question.</p>\r\n\r\n<p><em>What&rsquo;s your complete name? </em></p>\r\n\r\n<p>If the student does not interact with you in this moment, model the answer and immediately ask the question.</p>\r\n\r\n<p><em>My name is Simone Telles Martins Ramos</em></p>\r\n\r\n<p><em>What&rsquo;s your name?</em></p>\r\n\r\n<p>After the student replies, continue the dialogue.</p>\r\n\r\n<p><em>(student&rsquo;s name), I am 40 years old. </em></p>\r\n\r\n<p><em>How old are you?</em></p>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question or use portuguese.</p>\r\n\r\n<p>After the student reply, correct pronunciation if necessary. Then, continue the dialogue.</p>\r\n\r\n<p><em>(student&rsquo;s name), I live in S&atilde;o Paulo?</em></p>\r\n\r\n<p><em>Where do you live?</em></p>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question or use portuguese.</p>\r\n\r\n<p>After the student reply, correct pronunciation if necessary. Then, continue the dialogue.</p>\r\n\r\n<p><em>______, I am married. </em></p>\r\n\r\n<p><em>And you, (student&rsquo;s name)?</em></p>\r\n\r\n<p><em>Are you married or single?</em></p>\r\n\r\n<p>If the student does not interact with you in this moment, rephrase the question or use portuguese. Explain: married = casado(a), single = solteiro(a), widow/widower = vi&uacute;vo(a).</p>\r\n\r\n<p>After the student reply, correct pronunciation if necessary. Then, continue the dialogue.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Practice</strong></p>\r\n\r\n<p><em>What&rsquo;s your full name? Or What&rsquo;s your complete name? </em></p>\r\n\r\n<p><em>How old are you?</em></p>\r\n\r\n<p><em>Where do you live?</em></p>\r\n\r\n<p><em>Are you married or single?</em></p>\r\n\r\n<ol>\r\n	<li>during practice the student still has problems to interact with you, model the sentences again. If after modeling, the student continues to present interaction difficulties, you can use portuguese to help or ask the student to try to answer the question using the multimodal material (this is the moment to test its needs and efficiency).</li>\r\n</ol>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Production</strong></p>\r\n\r\n<p>Now encourage your student to give you his/her personal information.</p>\r\n\r\n<p><em>Now, let&rsquo;s practice more. </em></p>\r\n\r\n<p><em>Talk about yourself. </em></p>\r\n\r\n<p>If during production the student still has problems to interact with you, give hints and use verbal cues:</p>\r\n\r\n<p><em>Your name?!</em></p>\r\n\r\n<p><em>Your age?! (idade)</em></p>\r\n\r\n<p><em>Your location?! (onde mora)</em></p>\r\n\r\n<p><em>Your marital status (casado? solteiro?)</em></p>\r\n\r\n<p>If after the hints, the student continues to present interaction difficulties, it means that he needs more practice and we should encourage student to read the material again and make contact to receive another phone call.</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>Ending the call</strong></p>\r\n\r\n<p><em>(student&rsquo;s name), it is time to finish our class.</em></p>\r\n\r\n<p><em>Voc&ecirc; atingiu o objetivo de aprendizagem que era &ldquo;talk about yourself&rdquo; , ou seja, fornecer informa&ccedil;&otilde;es pessoais.</em></p>\r\n\r\n<p><em>Por favor, (re)leia o material interativo e pratique com as atividades do APP. </em></p>\r\n\r\n<p><em>Nice talking to you! Foi muito bom conversar com voc&ecirc;.</em></p>\r\n\r\n<p><em>Thank you, good-bye</em></p>\r\n','nice activity','teaser?','1','2017-05-15 20:07:18','2017-05-17 15:33:12',2),(8,'Aula 2 modulo 2',2,'A','<p>revisao&nbsp;aula 2 modulo 2</p>\r\n','<p>Conteudo aula 2 modulo 2</p>\r\n','<p>atividade aula 2 modulo 2</p>\r\n','<p>TEaser&nbsp;aula 2 modulo 2</p>\r\n','1','2017-05-17 17:04:03','2017-05-17 17:04:03',2);

-- comentei as linhas debaixo porque repetem as informações da primeira linha, não deletei pq talvez queiramos usar depois
-- insert into aula values (2, "how are you?", 2, "A", "review class 2", "this is the content class 2", "nice activity", "teaser?", "1", now(), now(), 1);
-- insert into aula values (3, "I am", 3, "A", "review class 3", "this is the content class 3", "nice activity", "teaser?", "1", now(), now(), 1);
-- insert into aula values (4, "She is", 4, "A", "review class 4", "this is the content class 4", "nice activity", "teaser?", "1", now(), now(), 1);
-- insert into aula values (5, "They are", 5, "A", "review class 5", "this is the content class 5", "nice activity", "teaser?", "1", now(), now(), 1);
-- insert into aula values (6, "They are second class", 1, "B", "review class 1B", "this is the content class 1B", "nice activity", "teaser?", "1", now(), now(), 1);
-- insert into aula values (7, "hullo", 1, "A", "review class 1", "this is the content class 1", "nice activity", "teaser?", "1", now(), now(), 2);

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
                            id_professor_aula_realizada,
                            url_gravacao_aula_realizada) 
	values (1, 5, 4, 4, "aluno conseguiu entender e reproduzir bem as estruturas, necessita melhorar um pouco o sotaque", "Realizada", 
            STR_TO_DATE( "28/04/2017 10:10:10", "%d/%m/%Y %H:%i:%s" ), 
            STR_TO_DATE( "29/04/2017 13:10:10", "%d/%m/%Y %H:%i:%s" ), 
            STR_TO_DATE( "29/04/2017 13:15:10", "%d/%m/%Y %H:%i:%s" ), 1, 1, 1,
            "https://api.twilio.com/2010-04-01/Accounts/AC8963db92d979cf31fbdb8df728e70966/Recordings/RE4ac17c7f10a4bb61b5f921a234d17916");

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
                            id_professor_aula_realizada,
                            url_gravacao_aula_realizada) 
	values (2, 4, 3, 3, "aluno conseguiu reproduzir a produzir maior parte, problema com o verbo", "Realizada",
             STR_TO_DATE( "30/04/2017 08:10:10", "%d/%m/%Y %H:%i:%s" ), 
			 STR_TO_DATE( "30/04/2017 18:10:10", "%d/%m/%Y %H:%i:%s" ), 
             STR_TO_DATE( "30/04/2017 18:15:10", "%d/%m/%Y %H:%i:%s" ), 1, 2, 1, 
             "https://api.twilio.com/2010-04-01/Accounts/AC8963db92d979cf31fbdb8df728e70966/Recordings/RE4ac17c7f10a4bb61b5f921a234d17916");

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
                            id_professor_aula_realizada,
                            url_gravacao_aula_realizada) 
	values (6, 2, STR_TO_DATE( "01/05/2017 21:15:10", "%d/%m/%Y %H:%i:%s" ), "Criada",5, 2, 1, "https://api.twilio.com/2010-04-01/Accounts/AC8963db92d979cf31fbdb8df728e70966/Recordings/RE4ac17c7f10a4bb61b5f921a234d17916");
    