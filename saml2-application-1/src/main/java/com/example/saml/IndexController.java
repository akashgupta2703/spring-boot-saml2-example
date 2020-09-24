package com.example.saml;

import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Index Page Controller
 *
 */
@Controller
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	private static final XmlMapper mapper = new XmlMapper();

	@GetMapping("/")
    public String getUserName(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal) throws IOException {
		
		printSAMLAttributes();
		return "index";
	}
	
	/**
	 * Prints the SAML assertion attributes
	 * @throws IOException
	 */
	private void printSAMLAttributes() throws IOException {
		
		Saml2Authentication authentication = (Saml2Authentication) SecurityContextHolder.getContext().getAuthentication();
		HashMap<String, Object> attributes = mapper.readValue(authentication.getSaml2Response(), new TypeReference<HashMap<String, Object>>() {});
		attributes.entrySet().stream().forEach(entry -> log.info(entry.getKey() + " : " + entry.getValue()));
	}
}
