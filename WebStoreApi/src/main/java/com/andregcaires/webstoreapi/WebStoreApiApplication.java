package com.andregcaires.webstoreapi;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Cidade;
import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.Endereco;
import com.andregcaires.webstoreapi.domain.Estado;
import com.andregcaires.webstoreapi.domain.ItemPedido;
import com.andregcaires.webstoreapi.domain.Pagamento;
import com.andregcaires.webstoreapi.domain.PagamentoComBoleto;
import com.andregcaires.webstoreapi.domain.PagamentoComCartao;
import com.andregcaires.webstoreapi.domain.Pedido;
import com.andregcaires.webstoreapi.domain.Produto;
import com.andregcaires.webstoreapi.domain.enums.EstadoPagamento;
import com.andregcaires.webstoreapi.domain.enums.TipoCliente;
import com.andregcaires.webstoreapi.repositories.CategoriaRepository;
import com.andregcaires.webstoreapi.repositories.CidadeRepository;
import com.andregcaires.webstoreapi.repositories.ClienteRepository;
import com.andregcaires.webstoreapi.repositories.EnderecoRepository;
import com.andregcaires.webstoreapi.repositories.EstadoRepository;
import com.andregcaires.webstoreapi.repositories.ItemPedidoRepository;
import com.andregcaires.webstoreapi.repositories.PagamentoRepository;
import com.andregcaires.webstoreapi.repositories.PedidoRepository;
import com.andregcaires.webstoreapi.repositories.ProdutoRepository;

@SpringBootApplication
public class WebStoreApiApplication implements CommandLineRunner {
	

	
	public static void main(String[] args) {
		SpringApplication.run(WebStoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Aplicação iniciada: "
							+ new Date().toString());
		
		
	}
	
}

