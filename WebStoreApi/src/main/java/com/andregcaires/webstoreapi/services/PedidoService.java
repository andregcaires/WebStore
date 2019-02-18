package com.andregcaires.webstoreapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andregcaires.webstoreapi.domain.ItemPedido;
import com.andregcaires.webstoreapi.domain.PagamentoComBoleto;
import com.andregcaires.webstoreapi.domain.Pedido;
import com.andregcaires.webstoreapi.domain.enums.EstadoPagamento;
import com.andregcaires.webstoreapi.mocks.MOCKS;
import com.andregcaires.webstoreapi.repositories.ItemPedidoRepository;
import com.andregcaires.webstoreapi.repositories.PagamentoRepository;
import com.andregcaires.webstoreapi.repositories.PedidoRepository;
import com.andregcaires.webstoreapi.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository _repo;
	
	@Autowired
	private MOCKS _boletoService;
	
	@Autowired
	private PagamentoRepository _pagamentoRepository;
	
	@Autowired
	private ProdutoService _produtoService;
	
	@Autowired
	private ItemPedidoRepository _itemPedidoRepository;
	
	@Autowired
	private ClienteService _clienteService;
	
	@Autowired
	private EmailService _emailService;
	
	public List<Pedido> findAll() {
		return _repo.findAll();
	}
	
	public Pedido find(Integer id) {
		
		Optional<Pedido> obj = _repo.findById(id);
		
		return obj.orElseThrow( () -> {
			throw new ObjectNotFoundException("Objeto não encontrado: ID:"+ id + "Tipo: "+ Pedido.class.getName());
		} );
	}
	
	@Transactional
	public Pedido insert(Pedido entity) {
		entity.setId(null);
		
		entity.setInstante(new Date());
		
		entity.setCliente(_clienteService.findById(entity.getCliente().getId()));
		
		entity.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		entity.getPagamento().setPedido(entity);
		
		if(entity.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) entity.getPagamento();
			pagto.setDataPagamento(entity.getInstante());
			
			_boletoService.preencherPagamentoComBoleto(pagto, entity.getInstante());
			
			entity.setPagamento(pagto);
		}
				
		for(ItemPedido ip : entity.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(_produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(entity);
		}
		
		entity = _repo.save(entity);
		_pagamentoRepository.save(entity.getPagamento());
		_itemPedidoRepository.saveAll(entity.getItens());
		System.out.println(entity);
		_emailService.sendOrderConfirmationEmail(entity);
		return entity;
	}
	
}
