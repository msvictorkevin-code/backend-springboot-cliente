package com.bolsaideas.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsaideas.springboot.backend.apirest.models.dao.IClienteDAO;
import com.bolsaideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsaideas.springboot.backend.apirest.models.entity.Region;

@Service
public class ClienteServiceImpl implements IClienteService {

  @Autowired private IClienteDAO clienteDAO;

  @Override
  @Transactional(readOnly = true)
  public List<Cliente> findAll() {
    // TODO Auto-generated method stub
    List<Cliente> list = (List<Cliente>) clienteDAO.findAll();
    return list;
  }

  @Override
  @Transactional(readOnly = true)
  public Cliente findById(Long id) {
    // TODO Auto-generated method stub
    Cliente cliente = clienteDAO.findById(id).orElse(null);
    return cliente;
  }

  @Override
  public Cliente save(Cliente cliente) {
    // TODO Auto-generated method stub
    return clienteDAO.save(cliente);
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    clienteDAO.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Cliente> findAll(Pageable pageable) {
    // TODO Auto-generated method stub
    return clienteDAO.findAll(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Region> findAllRegions() {
    // TODO Auto-generated method stub
    return clienteDAO.findAllRegions();
  }
}
