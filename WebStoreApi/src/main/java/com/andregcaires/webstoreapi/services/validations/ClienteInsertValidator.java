package com.andregcaires.webstoreapi.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.enums.TipoCliente;
import com.andregcaires.webstoreapi.dto.ClienteEnderecoDTO;
import com.andregcaires.webstoreapi.repositories.ClienteRepository;
import com.andregcaires.webstoreapi.resources.exceptions.FieldMessage;
import com.andregcaires.webstoreapi.services.validations.utils.DocumentValidator;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert , ClienteEnderecoDTO> {
	
	@Autowired
	private ClienteRepository _repo;

	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteEnderecoDTO obj, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(obj.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !DocumentValidator.isValidSsn(obj.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		else if(obj.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !DocumentValidator.isValidTin(obj.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		
		if( _repo.findByEmail(obj.getEmail()) != null ) {
			list.add(new FieldMessage("email", "E-mail já existente"));
		}
		
		if( _repo.findByCpfOuCnpj(obj.getCpfOuCnpj()) != null ) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF ou CNPJ já existente"));
		}
		
		// instancia os erros no context para processamento do framework
		for(FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName())
				.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
}
