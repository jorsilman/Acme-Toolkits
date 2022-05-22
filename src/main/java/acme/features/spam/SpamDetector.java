package acme.features.spam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpamDetector {

	@Autowired
	private SystemConfigurationRepository repository;
	
	public List<String> normalizarTexto(final String texto){
		return null;
	}
	
	public boolean isSpam(final String texto) {
		final boolean res = true;
		final List<String> textoNormalizado = this.normalizarTexto(texto);
		
		final List<String> palabrasWeak = this.repository.getWeak();
		final List<String> palabrasStrong = this.repository.getStrong();
		return res;
	}
}
