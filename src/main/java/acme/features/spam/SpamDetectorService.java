package acme.features.spam;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.systemConfiguration.SystemConfiguration;

@Service
public class SpamDetectorService {
	@Autowired
	private static SystemConfigurationRepository repository;
	
	public static String normalizarTexto(final String texto){
		return texto.trim().replace("-", " ").replaceAll("\\s+", " ").replaceAll("[ ]", " ");
	}
	
	public static boolean isSpam(final String texto) {
		boolean res = false;
		final String textoNormalizado = SpamDetectorService.normalizarTexto(texto);
		
		
		final SystemConfiguration systemConfiguration = SpamDetectorService.repository.getSystemConfiguration().get(0);
		
		
		final String[] palabrasWeak = systemConfiguration.getWeakSpamWords().split(",");
		final String[] palabrasStrong = systemConfiguration.getStrongSpamWords().split(",");
		final Double strongSpamThreshole = systemConfiguration.getStrongSpamThreshold();
		final Double weakSpamThreshole = systemConfiguration.getWeakSpamThreshold();
		
		
		
		int ratioWeak = 0;
		for(final String palabra:palabrasWeak) {
			final int count = StringUtils.countMatches(textoNormalizado, palabra);
			ratioWeak+=count;
		}
		ratioWeak = ratioWeak / palabrasWeak.length;
		
		int ratioStrong = 0;
		for (final String palabra : palabrasStrong) {
			final int count = StringUtils.countMatches(textoNormalizado, palabra);
			ratioStrong+=count;
		}
		ratioStrong = ratioStrong / palabrasStrong.length;
		
		if(ratioWeak >= weakSpamThreshole || ratioStrong >= strongSpamThreshole) {
			res = true;
		}
		return res;
	}
	
}
