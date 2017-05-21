package jUnit;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hullo.dao.AlunoDAOImpl;
import com.hullo.entity.AlunoImpl;

public class UC02CRUDAluno {
	
	@Autowired
	AlunoDAOImpl alunoDAO = new AlunoDAOImpl();
	
	static AlunoImpl aluno = new AlunoImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Date now = new Date();
		Calendar data = new GregorianCalendar();
		data.set(Calendar.YEAR, -19);
		Date dataNasc = data.getTime();	
		
		aluno.setAtivo_usuario("1");
		//aluno.setCd_cidade_usuario(1);
		aluno.setCpf_usuario("33225074837");
		aluno.setData_nascimento_usuario(dataNasc);
		aluno.setDt_insert_usuario(now);
		aluno.setDt_last_update_usuario(now);
		aluno.setEmail_usuario("melc13@gmail.com");
		aluno.setNome_usuario("Nana");
		aluno.setProfissao_usuario("Empreendedora");
		aluno.setSenha_usuario("123");
		aluno.setSexo_usuario("F");
		aluno.setSobrenome_usuario("Esposito");
		aluno.setTelefone_usuario("5511951985777");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		alunoDAO.saveUsuario(aluno);;
	}

}
