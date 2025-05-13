package com.senai.ecommerce.controllers;

import com.senai.ecommerce.services.RelatorioPedidoService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relatorios")
public class RelatorioPedidoController {

    @Autowired
    RelatorioPedidoService relatorioPedidoService;

    @GetMapping
    public ResponseEntity<String> gerarRelatorioPDF(@RequestParam String caminho) throws JRException {
        relatorioPedidoService.gerarRelatorio(caminho);
        return ResponseEntity.ok("Relatorio criado com sucesso" + caminho);
    }

}
