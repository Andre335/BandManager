package excecao;

/**
 * Exceção do programa MoneySaver.
 */
public class BandManagerException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da exceção.
	 * 
	 * @param mensagem
	 *            A mensagem da exceção.
	 */
	public BandManagerException(String mensagem) {
		super(mensagem);
	}
}
