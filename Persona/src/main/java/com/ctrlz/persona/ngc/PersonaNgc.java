package com.ctrlz.persona.ngc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ctrlz.dominio.dto.PersonaDTO;
import com.ctrlz.persona.dao.PersonaDaoInt;
import com.ctrlz.util.excepcion.ExcepcionDao;
import com.ctrlz.util.excepcion.ExcepcionNgc;

@RestController
@RequestMapping(value = "/persona")
public class PersonaNgc implements PersonaNgcInt {
	
	@Autowired
	PersonaDaoInt personaDao;
	
	public void setPersonaDao(PersonaDaoInt personaDao) {
		this.personaDao = personaDao;
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public void crearPersona(@RequestBody PersonaDTO personaDTO) throws ExcepcionNgc{
		try {
			personaDao.crearPersona(personaDTO);
			personaDao.crearPersonaSession(personaDTO);
		} catch (ExcepcionDao e) {
			ExcepcionNgc expNgc = new ExcepcionNgc(e);			
			throw expNgc;
		} catch (Exception e) {
			ExcepcionNgc expNgc = new ExcepcionNgc(e);			
			expNgc.setMensajeUsuario("Error validando información");
			throw expNgc;
		}
	}	


}
