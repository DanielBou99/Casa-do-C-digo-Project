package snippet;

public class Snippet {
	@ExceptionHandler(NoResultException.class)
	public String trataDetalheNaoEcontrado(){
	    return "error";
	}
}

