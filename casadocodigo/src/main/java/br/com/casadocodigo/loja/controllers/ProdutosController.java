package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos") /*Nota��o para n�o ter que digitar /produtos em todos os mapeamentos dos metodos*/
/*OBS: � necess�rio "/" antes de "produtos" para utilizar s:mvcUrl no formul�rio JSP*/
public class ProdutosController {

	 /* Utilizamos o @AutoWired para indicar ao Spring que o 
	objeto anotado � um Bean dele e que queremos que ele nos d� uma inst�ncia 
	por meio do recurso de inje��o de depend�ncia. */
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) { /* Classe necess�ria para relacionar a ProdutoValidation */
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form") /* Mapeamento */
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView; /* Retorno da aplica��o ao acessar a p�gina mapeada*/
	}
	
	/*BindingResult -> Diz se houve erro de valida��o ou n�o*/
	@RequestMapping(name="salvar", method = RequestMethod.POST)
	@CacheEvict(value = "produtosHome", allEntries = true)
	public ModelAndView gravar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes, MultipartFile sumario) { 
		
		System.out.println(sumario.getOriginalFilename());
		
		if (result.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		
		produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		/*utilizando o redirectAttributes, o parametro sucesso  consegue sobreviver no redirect*/
		
		return new ModelAndView("redirect:produtos"); /*Redirect para o navegador n�o gravar os parametros*/
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView; 
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
		Produto produto = produtoDao.find(id);
		modelAndView.addObject("produto", produto);
		
		return modelAndView; 
	}
	
}
