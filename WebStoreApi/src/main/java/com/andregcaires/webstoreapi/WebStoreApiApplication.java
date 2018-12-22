package com.andregcaires.webstoreapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andregcaires.webstoreapi.domain.Categoria;
import com.andregcaires.webstoreapi.domain.Produto;
import com.andregcaires.webstoreapi.repositories.CategoriaRepository;
import com.andregcaires.webstoreapi.repositories.ProdutoRepository;

@SpringBootApplication
public class WebStoreApiApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebStoreApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("It's on!");
		
		// Declaracoes
		Categoria cat1, cat2;
		Produto p1, p2, p3;
		
		// Instancias
		cat1 = new Categoria(null, "Roupa Masculina");
		cat2 = new Categoria(null, "Roupa Feminina");
		p1 = new Produto(null, "Camiseta", 15.00);
		p2 = new Produto(null, "Jaqueta Unissex", 70.00);
		p3 = new Produto(null, "Vestido", 45.00);
		
		// Joins
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat2));
		
		cat1.setProdutos(Arrays.asList(p1, p2));
		cat2.setProdutos(Arrays.asList(p2, p3));
		//cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		
		// Persistencias
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
	
}

