package com.hullo.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hullo.entity.AlunoImpl;
import com.hullo.entity.CidadeImpl;
import com.hullo.entity.EstadoImpl;
import com.hullo.entity.ProfessorImpl;
import com.hullo.entity.ProfessorModel;
import com.hullo.service.CidadeServiceImpl;
import com.hullo.service.EstadoServiceImpl;
import com.hullo.service.UsuarioService;

/**
* classe para ProfessorControler
* @author Hullo Team 
* @version 1.0
 */


@Controller
@RequestMapping("/professor")
@SessionAttributes("usuario")
public class ProfessorController {

	@Autowired
	@Qualifier("professorServiceImpl")
	private UsuarioService<ProfessorImpl> professorService;
	
	@Autowired
	@Qualifier("alunoServiceImpl")
	private UsuarioService<AlunoImpl> alunoService;

	@Autowired
	private EstadoServiceImpl estadoService;

	@Autowired
	private CidadeServiceImpl cidadeService;

	// Abaixo, dados para disparo de email	
	@Autowired
	private MailSender mailSender;

	/**
	 * disparo de email
	 * @param mailSender
	 */
	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/**
	 * metodo para abrir formulario
	 * @param theModel
	 * @return professor-form
	 */
	@GetMapping("/formProfessor")
	public String showFormNovoUsuario(Model theModel) {

		/* **
		 *  create model attribute to bind form data
		 */
		ProfessorImpl theProfessor = new ProfessorImpl();

		List<EstadoImpl> estados = estadoService.getEstados();

		ProfessorModel professorModel = new ProfessorModel();

		professorModel.setEstado(estados);
		professorModel.setUsuario(theProfessor);

		theModel.addAttribute("professorModel", professorModel);

		return "professor-form";
	}

	/**
	 * metodo para salvar novo usuario
	 * @param professorModel
	 * @param modelMap
	 * @return professor-form
	 * @return redirect:/usuario/usuarioLogin
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@PostMapping("/formProfessor")
	public String saveUsuario(@ModelAttribute("professorModel") ProfessorModel professorModel, ModelMap modelMap)
			throws JsonParseException, JsonMappingException, IOException {

		Date current_date = new Date();

		ProfessorImpl theProfessor = professorModel.getUsuario();

		ObjectMapper mapper = new ObjectMapper();
		CidadeImpl cidade = mapper.readValue(professorModel.getCidade(), CidadeImpl.class);

		//seta o id da cidade no usuario
		
		theProfessor.setCidade(cidade.getId_Cidade());
		//validar se ja existe usuario com esse email ou senha
		 
		ProfessorImpl validaProfessor = professorService.validaUsuario(theProfessor.getEmail_usuario(), theProfessor.getCpf_usuario());
		AlunoImpl validaAluno = alunoService.getUsuario(theProfessor.getEmail_usuario());
	
		//pega os estados
				List<EstadoImpl> estados = estadoService.getEstados();
				//coloca cidades na model caso ocorra erro
				professorModel.setEstado(estados);
		
		/* **
		 * valida idade	
		 * valida cpf
		 * valida cnpj
		 * valida duplicidade
		 */
		if(calculaIdade(theProfessor.getData_nascimento_usuario())){
		
				if (isCPF(theProfessor.getCpf_usuario())){
					
					if(isCNPJ(theProfessor.getCnpj_usuario())){
				
						if (validaProfessor != null) {
				
							 //exibe mensagem de erro
							 
							final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Já existe usuario com o mesmo e-mail ou cpf. </div>";
							modelMap.addAttribute("errorMessage", errorMessage);
				
							return "professor-form";
							
						
						  //se houver aluno, checa se a senha é igual. Se não for, devolve erro	
						 
						} else if ((validaAluno!=null) && !(validaAluno.getSenha_usuario().equals(theProfessor.getSenha_usuario()))){
							
								final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>A senha deve ser a mesma do perfil de aluno já cadastrado.</div>";
								modelMap.addAttribute("errorMessage", errorMessage);
				
								return "professor-form";
							//se nao existe professor com esses dados, cria o ususario
							 
						} else {
							theProfessor.setAtivo_usuario("1");
							theProfessor.setDt_insert_usuario(current_date);
							theProfessor.setDt_last_update_usuario(current_date);
				
							// save the professor
							 
							professorService.saveUsuario(theProfessor);
				
							
							 // Envia email de confirmação
							 
							SimpleMailMessage msg = new SimpleMailMessage();
				
							msg.setTo(theProfessor.getEmail_usuario());
							msg.setFrom("noreply@hullo.com.br");
							msg.setSubject("Confirmação de cadastro");
							msg.setText(theProfessor.getNome_usuario() + ", seu cadastro de professor foi realizado com sucesso.");
				
							try {
								this.mailSender.send(msg);
								
							} catch (MailException e) {
							
								 // TODO Auto-generated catch block
								 
							}
				
							
							final String okNewProfessorMessage = "<div class='alert alert-success fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Professor cadastrado com sucesso. Faça login. </div>";
							modelMap.addAttribute("okNewProfessorMessage", okNewProfessorMessage);
							return "redirect:/usuario/usuarioLogin";
						}
					}else{
						
						final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>CNPJ inválido.</div>";
						modelMap.addAttribute("errorMessage", errorMessage);
				
						return "professor-form";
					}
				}else{
					
					final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>CPF inválido.</div>";
					modelMap.addAttribute("errorMessage", errorMessage);
			
					return "professor-form";
				}
		}else{
			
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Idade mínima 18 anos.</div>";
			modelMap.addAttribute("errorMessage", errorMessage);
	
			return "professor-form";
		}
	}
	
	/**
	 * metodo que traz a lista de cidades
	 * @param estado
	 * @return cidade
	 */
	@RequestMapping(value = "/formProfessor/cidades", method = RequestMethod.POST)
	public @ResponseBody List<CidadeImpl> obterCidade(@RequestBody EstadoImpl estado) {


		List<CidadeImpl> cidade = cidadeService.obterCidadesDoEstado(estado);

		return cidade;
	}

	/**
	 * metodo para abrir pagina de perfil professor
	 * @param session
	 * @return perfil-professor
	 */	
	@RequestMapping("/showPerfilProfessor")
	public String showPerfilProfessor(HttpSession session) {
		return "perfil-professor";
	}
	
	/**
	 * metodo para abrir pagina home do professor
	 * @param session
	 * @return home-professor
	 */
	@RequestMapping("/showHomeProfessor")
	public String showHomeProfessor(HttpSession session) {
		return "home-professor";
	}

	/**
	 * metodo para abrir pagina de update do professor
	 * @param session
	 * @return professor-update-form
	 */
	@PostMapping("/showFormUpdateProfessor")
	public String showFormUpdateProfessor(HttpSession session) {
		return "professor-update-form";
	}

	/**
	 * metodo para atualizar professor
	 * @param theUsuario
	 * @param session
	 * @param modelMap
	 * @return home-professor
	 * @return professor-update-form
	 */
	@RequestMapping("/updateProfessor")
	public String updateProfessor(@ModelAttribute("usuario") ProfessorImpl theUsuario, HttpSession session,	ModelMap modelMap) {

		//validar se ja existe usuario com esse email
		 
		ProfessorImpl validaProfessor = professorService.validaUsuario(theUsuario.getEmail_usuario(), theUsuario.getId_usuario());
		AlunoImpl validaAluno = alunoService.getUsuario(theUsuario.getEmail_usuario());
	
		// valida idade	
		 
		if(calculaIdade(theUsuario.getData_nascimento_usuario())){
			
			if(isCNPJ(theUsuario.getCnpj_usuario())){

				if (validaProfessor != null || validaAluno!= null) {
		
					final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a> Existe outro usuario com esse email </div>";
					modelMap.addAttribute("errorMessage", errorMessage);
					return "professor-update-form";
					
				} else {
					
					//Atualiza a sessão com os dados inseridos no formulario
					 
					Date current_date = new Date();
					theUsuario.setDt_last_update_usuario(current_date);
					professorService.updateUsuario(theUsuario);
		
					return "home-professor";
				}										
			}else{
				
				final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>CNPJ inválido.</div>";
				modelMap.addAttribute("errorMessage", errorMessage);
	
				return "professor-update-form";
			}
		}else{
			
			final String errorMessage = "<div class='alert alert-danger fade in'> <a href='#' class='close' data-dismiss='alert'>&times;</a>Idade mínima 18 anos.</div>";
			modelMap.addAttribute("errorMessage", errorMessage);
		
			return "professor-update-form";
		}
	
	}

	/**
	 * metodo para inativar professor
	 * @param theUsuario
	 * @param theModel
	 * @return redirect:/usuario/usuarioLogin
	 */
	@PostMapping("/inactivateProfessor")
	public String inactivateProfessor(@ModelAttribute("usuario") ProfessorImpl theUsuario, Model theModel) {
		Date current_date = new Date();

		theUsuario.setDt_last_update_usuario(current_date);

		professorService.inactivateUsuario(theUsuario);

		theModel.addAttribute(theUsuario);
		return "redirect:/usuario/usuarioLogin";

	}
	
	/**
	 * metodo que valida cpf
	 * @param CPF
	 * @return boolean
	 */
	public static boolean isCPF(String CPF) {
		CPF = CPF.replaceAll("[.-]","");
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		 
		    if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
		        CPF.equals("22222222222") || CPF.equals("33333333333") ||
		        CPF.equals("44444444444") || CPF.equals("55555555555") ||
		        CPF.equals("66666666666") || CPF.equals("77777777777") ||
		        CPF.equals("88888888888") || CPF.equals("99999999999") ||
		       (CPF.length() != 11))
		       return(false);

		    char dig10, dig11;
		    int sm, i, r, num, peso;

		    try {
			// Calculo do 1o. Digito Verificador
			 
		      sm = 0;
		      peso = 10;
		      for (i=0; i<9; i++) {              
		/* **
		 *  converte o i-esimo caractere do CPF em um numero:
		 *  por exemplo, transforma o caractere '0' no inteiro 0         
		 * (48 eh a posicao de '0' na tabela ASCII)
		 */
		        
		        num = (int)(CPF.charAt(i) - 48); 
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig10 = '0';
		      // converte no respectivo caractere numerico
		       
		      else dig10 = (char)(r + 48); 

		// Calculo do 2o. Digito Verificador
		 
		      sm = 0;
		      peso = 11;
		      for(i=0; i<10; i++) {
		        num = (int)(CPF.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso - 1;
		      }

		      r = 11 - (sm % 11);
		      if ((r == 10) || (r == 11))
		         dig11 = '0';
		      else dig11 = (char)(r + 48);

		//Verifica se os digitos calculados conferem com os digitos informados.
		 
		      if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		 }
	/**
	 * metodo de validar cnpj
	 * @param CNPJ
	 * @return boolean
	 */
	public static boolean isCNPJ(String CNPJ) {
		CNPJ = CNPJ.replaceAll("[.-/-]","");
		//para considerar campo vazio
		 
		if(CNPJ.equals("")) return(true); 
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		 
		    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
		        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
		        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
		        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
		        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		       (CNPJ.length() != 14))
		       return(false);

		    char dig13, dig14;
		    int sm, i, r, num, peso;

		    try {
		// Calculo do 1o. Digito Verificador
		 
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
		/* **
		 *  converte o i-ésimo caractere do CNPJ em um número:
		 *  por exemplo, transforma o caractere '0' no inteiro 0
		 * (48 eh a posição de '0' na tabela ASCII)
		 */
		
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }

		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig13 = '0';
		      else dig13 = (char)((11-r) + 48);

		// Calculo do 2o. Digito Verificador
		 
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }

		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig14 = '0';
		      else dig14 = (char)((11-r) + 48);

		//Verifica se os dígitos calculados conferem com os dígitos informados.
		 
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
	}	
	/**
	 * metodo de calcular idade
	 * @param dataNasc
	 * @return boolean
	 */
	public static boolean calculaIdade(java.util.Date dataNasc) {

	    Calendar dataNascimento = Calendar.getInstance();  
	    dataNascimento.setTime(dataNasc); 
	    Calendar hoje = Calendar.getInstance();  

	    int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR); 

	    if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)) {
	      idade--;  
	    } 
	    else 
	    { 
	        if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)) {
	            idade--; 
	        }
	    }
	    
	    if (idade >= 18) return true;
	    else return false;
	}
	
}
