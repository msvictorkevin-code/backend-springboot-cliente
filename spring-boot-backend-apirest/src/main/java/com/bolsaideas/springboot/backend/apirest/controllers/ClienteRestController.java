package com.bolsaideas.springboot.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsaideas.springboot.backend.apirest.models.entity.Cliente;
import com.bolsaideas.springboot.backend.apirest.models.entity.Region;
import com.bolsaideas.springboot.backend.apirest.models.service.IClienteService;
import com.bolsaideas.springboot.backend.apirest.models.service.IUploadFileService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

  @Autowired private IClienteService clienteService;

  @Autowired private IUploadFileService fileService;

  private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

  @GetMapping("/clientes")
  public List<Cliente> findAll() {
    return clienteService.findAll();
  }

  @GetMapping("/clientes/page/{page}")
  public Page<Cliente> index(@PathVariable Integer page) {
    Pageable pageable = PageRequest.of(page, 4);
    return clienteService.findAll(pageable);
  }

  @GetMapping("/clientes/{id}")
  public ResponseEntity<?> show(@PathVariable Long id) {
    Cliente oCliente = null;
    Map<String, Object> response = new HashMap<String, Object>();
    try {
      oCliente = clienteService.findById(id);
    } catch (DataAccessException ex) {
      response.put("mensaje", "Error al realizar consulta en base de datos");
      response.put(
          "error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    if (oCliente == null) {
      response.put(
          "mensaje",
          "El cliente Id:".concat(id.toString().concat(" no existe en la base de datos!")));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Cliente>(oCliente, HttpStatus.OK);
  }

  @PostMapping("/clientes")
  public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult bindResult) {
    Cliente nCliente = null;
    Map<String, Object> response = new HashMap<String, Object>();

    if (bindResult.hasErrors()) {
      List<String> errors =
          bindResult.getFieldErrors().stream()
              .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
              .collect(Collectors.toList());
      response.put("errors", errors);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }
    try {
      cliente.setCreateAt(new Date());
      nCliente = clienteService.save(cliente);
    } catch (DataAccessException ex) {
      // TODO: handle exception
      response.put("mensaje", "Error al realizar insert en base de datos");
      response.put(
          "error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    response.put("mensaje", "El cliente ha sido creado con exito!.");
    response.put("cliente", nCliente);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @PutMapping("/clientes/{id}")
  public ResponseEntity<?> update(
      @RequestBody Cliente cliente, BindingResult bindResult, @PathVariable Long id) {
    Cliente aCliente = clienteService.findById(id);
    Cliente uCliente = null;
    Map<String, Object> response = new HashMap<String, Object>();

    if (bindResult.hasErrors()) {
      List<String> errors =
          bindResult.getFieldErrors().stream()
              .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
              .collect(Collectors.toList());
      response.put("errors", errors);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
    }

    if (aCliente == null) {
      response.put(
          "mensaje",
          "Error: Nos pudo editar, el cliente Id:"
              .concat(id.toString().concat(" no existe en la base de datos!")));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    }

    try {

      aCliente.setNombre(cliente.getNombre());
      aCliente.setApellido(cliente.getApellido());
      aCliente.setEmail(cliente.getEmail());
      aCliente.setCreateAt(cliente.getCreateAt());
      aCliente.setRegion(cliente.getRegion());
      uCliente = clienteService.save(aCliente);
    } catch (DataAccessException ex) {
      // TODO: handle exception
      response.put("mensaje", "Error al realizar update en base de datos");
      response.put(
          "error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El cliente ha sido actualizado con exito!.");
    response.put("cliente", uCliente);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/clientes/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Map<String, Object> response = new HashMap<String, Object>();
    try {
      clienteService.delete(id);
      Cliente cliente = clienteService.findById(id);
      String nombreFotoAnterior = cliente.getFoto();
      fileService.eliminar(nombreFotoAnterior);

    } catch (DataAccessException ex) {
      // TODO: handle exception
      response.put("mensaje", "Error al realizar eliminar en base de datos");
      response.put(
          "error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    response.put("mensaje", "El cliente ha sido eliminado con exito!.");
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NO_CONTENT);
  }

  @PostMapping("/clientes/upload")
  public ResponseEntity<?> upload(
      @RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
    Map<String, Object> response = new HashMap<String, Object>();
    Cliente cliente = clienteService.findById(id);
    if (!archivo.isEmpty()) {
      String nombreArchivo = null;

      try {
        nombreArchivo = fileService.copiar(archivo);
      } catch (IOException ex) {
        // TODO: handle exception
        response.put("mensaje", "Error al subir la imagen del cliente " + nombreArchivo);
        response.put("error", ex.getMessage().concat(": ").concat(ex.getCause().getMessage()));
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
      }

      String nombreFotoAnterior = cliente.getFoto();
      fileService.eliminar(nombreFotoAnterior);

      cliente.setFoto(nombreArchivo);
      clienteService.save(cliente);
      response.put("mensaje", "Ha subido correctamente el nombre de la imagen " + nombreArchivo);
      response.put("cliente", cliente);
    }
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @GetMapping("/uploads/img/{nombreFoto:.+}")
  public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

    Resource recurso = null;
    try {
      recurso = fileService.cargar(nombreFoto);
    } catch (MalformedURLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    HttpHeaders headers = new HttpHeaders();
    headers.add(
        HttpHeaders.CONTENT_DISPOSITION, "attchment; filename=\"" + recurso.getFilename() + "\"");
    return new ResponseEntity<Resource>(recurso, headers, HttpStatus.OK);
  }

  @GetMapping("/clientes/regiones")
  public List<Region> listarRegiones() {
    return clienteService.findAllRegions();
  }
}
