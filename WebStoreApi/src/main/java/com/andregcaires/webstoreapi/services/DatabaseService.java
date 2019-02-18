package com.andregcaires.webstoreapi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.andregcaires.webstoreapi.domain.enums.Perfil;
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

@Service
public class DatabaseService {

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
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder _bcrypt;
	
	public void initDatabase() throws ParseException {
		// auxiliar
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		// Declaracoes
		Categoria cat1, cat2, cat3, cat4, cat5, cat6, cat7;
		Produto p1, p2, p3, p4, p5, p6, p7;
		Estado est1, est2;
		Cidade c1, c2, c3;
		Cliente cli1, cli2;
		Endereco end1, end2;
		Pedido ped1, ped2;
		Pagamento pagto1, pagto2;
		ItemPedido ip1, ip2, ip3;
		
		
		// Instancias
		cat1 = new Categoria(null, "Roupa Masculina");
		cat2 = new Categoria(null, "Roupa Feminina");
		cat3 = new Categoria(null, "Calçado Masculino");
		cat4 = new Categoria(null, "Calçado Feminino");
		cat5 = new Categoria(null, "Roupa Infantil Masculina");
		cat6 = new Categoria(null, "Roupa Infantil Feminina");
		cat7 = new Categoria(null, "Chapéu");
		p1 = new Produto(null, "Camiseta", 15.00);
		p2 = new Produto(null, "Jaqueta Unissex", 70.00);
		p3 = new Produto(null, "Vestido", 45.00);
		p4 = new Produto(null, "Boné", 25.00);
		p5 = new Produto(null, "Bermuda", 30.00);
		p6 = new Produto(null, "Sapatênis", 120.00);
		p7 = new Produto(null, "Sandalha", 65.00);
		est1 = new Estado(null, "MG");
		est2 = new Estado(null, "SP");
		c1 = new Cidade(null, "São Paulo", est2);
		c2 = new Cidade(null, "Belo Horizonte", est1);		
		c3 = new Cidade(null, "Campinas", est2);		
		cli1 = new Cliente(null, "Maria Silva", "maria@teste.com", "01127669001", TipoCliente.PESSOAFISICA, _bcrypt.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("16991234567", "169987456123"));
		cli2 = new Cliente(null, "Super User", "guaraldocaires@gmail.com", "05439485015", TipoCliente.PESSOAFISICA, _bcrypt.encode("456"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("16999999999", "16888888888"));
		end1 = new Endereco(null, "Logradouro Teste", "Num Teste", "Comp Teste", "Bairo Teste", "Cep Teste", cli1, c1);
		end2= new Endereco(null, "Logradouro 2", "Num 2", "Comp 2", "Bairo 2", "Cep 2", cli1, c2);
		ped1 = new Pedido(null, sdf.parse("31/01/1990 00:00"), cli1, end1);
		ped2 = new Pedido(null, sdf.parse("03/01/1990 09:15"), cli1, end2);
		pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO.getCod(), ped1, 6);
		ped1.setPagamento(pagto1);
		pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE.getCod(), ped2, sdf.parse("03/01/1990 09:15"), null);
		ped2.setPagamento(pagto2);
		
		ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		
		// Joins
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat2, cat5));
		p4.getCategorias().addAll(Arrays.asList(cat1, cat5));
		p5.getCategorias().addAll(Arrays.asList(cat5, cat1));
		p6.getCategorias().addAll(Arrays.asList(cat1));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p4, p5, p6));
		cat2.getProdutos().addAll(Arrays.asList(p2, p3, p7));
		cat3.getProdutos().addAll(Arrays.asList(p6));
		cat4.getProdutos().addAll(Arrays.asList(p7));
		cat5.getProdutos().addAll(Arrays.asList(p1, p4, p5));
		cat6.getProdutos().addAll(Arrays.asList(p3));
		cat7.getProdutos().addAll(Arrays.asList(p4));
		
		est2.setCidades(Arrays.asList(c1, c3));
		est1.setCidades(Arrays.asList(c2));
		//est2.getCidades().addAll(Arrays.asList(c1, c3));
		//est1.getCidades().addAll(Arrays.asList(c2));

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		// Persistencias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2)); // cliente deve ser salvo primeiro
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
