package com.andregcaires.webstoreapi;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Cidade;
import com.andregcaires.webstoreapi.domain.Cliente;
import com.andregcaires.webstoreapi.domain.Endereco;
import com.andregcaires.webstoreapi.domain.Estado;
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
import com.andregcaires.webstoreapi.repositories.PagamentoRepository;
import com.andregcaires.webstoreapi.repositories.PedidoRepository;
import com.andregcaires.webstoreapi.repositories.ProdutoRepository;

@SpringBootApplication
public class WebStoreApiApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WebStoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("It's on!");
		
		// auxiliar
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		// Declaracoes
		Categoria cat1, cat2;
		Produto p1, p2, p3;
		Estado est1, est2;
		Cidade c1, c2, c3;
		Cliente cli1;
		Endereco end1, end2;
		Pedido ped1, ped2;
		Pagamento pagto1, pagto2;
		
		
		// Instancias
		cat1 = new Categoria(null, "Roupa Masculina");
		cat2 = new Categoria(null, "Roupa Feminina");
		p1 = new Produto(null, "Camiseta", 15.00);
		p2 = new Produto(null, "Jaqueta Unissex", 70.00);
		p3 = new Produto(null, "Vestido", 45.00);
		est1 = new Estado(null, "MG");
		est2 = new Estado(null, "SP");
		c1 = new Cidade(null, "São Paulo", est2);
		c2 = new Cidade(null, "Belo Horizonte", est1);		
		c3 = new Cidade(null, "Campinas", est2);		
		cli1 = new Cliente(null, "Maria Silva", "maria@teste.com", "12345678910", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("16991234567", "169987456123"));
		end1 = new Endereco(null, "Logradouro Teste", "Num Teste", "Comp Teste", "Bairo Teste", "Cep Teste", cli1, c1);
		end2= new Endereco(null, "Logradouro 2", "Num 2", "Comp 2", "Bairo 2", "Cep 2", cli1, c2);
		ped1 = new Pedido(null, sdf.parse("31/01/1990 00:00"), cli1, end1);
		ped2 = new Pedido(null, sdf.parse("03/01/1990 09:15"), cli1, end2);
		pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO.getCod(), ped1, 6);
		ped1.setPagamento(pagto1);
		pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE.getCod(), ped2, sdf.parse("03/01/1990 09:15"), null);
		ped2.setPagamento(pagto2);
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		// Joins
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3));

		est2.setCidades(Arrays.asList(c1, c3));
		est1.setCidades(Arrays.asList(c2));
		//est2.getCidades().addAll(Arrays.asList(c1, c3));
		//est1.getCidades().addAll(Arrays.asList(c2));

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		// Persistencias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.save(cli1); // cliente deve ser salvo primeiro
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	}
	
}

