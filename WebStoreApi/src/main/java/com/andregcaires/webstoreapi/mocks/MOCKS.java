package com.andregcaires.webstoreapi.mocks;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.andregcaires.webstoreapi.domain.PagamentoComBoleto;

/*
 * Classe utilizara para simular serviços obtidos por outros web services
 * 
 * */

@Service
public class MOCKS {
	
	/*
	 * @use: PedidoService
	 * */	
	public void preencherPagamentoComBoleto(PagamentoComBoleto boleto, Date instanteDoPedido) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(instanteDoPedido);
		
		cal.add(Calendar.DAY_OF_MONTH, 7);
		
		boleto.setDataVencimento(cal.getTime());
		
	}

}
