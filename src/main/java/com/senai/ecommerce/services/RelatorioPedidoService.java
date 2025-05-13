package com.senai.ecommerce.services;

import com.senai.ecommerce.dto.RelatorioPedidoDTO;
import com.senai.ecommerce.entities.Pedido;
import com.senai.ecommerce.repositories.PedidoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RelatorioPedidoService {

    // Injeta automaticamente o repositório de pedidos
    @Autowired
    PedidoRepository pedidoRepository;

    // Método para gerar o relatório, recebendo o caminho de saída como parâmetro
    public void gerarRelatorio(String caminho) throws JRException {

        // Busca todos os pedidos do banco de dados
        List<Pedido> pedidos = pedidoRepository.findAll();

        // Converte cada Pedido em RelatorioPedidoDTO usando stream
        List<RelatorioPedidoDTO> escanor = pedidos.stream()
                .map(RelatorioPedidoDTO::new) // usa o construtor do DTO
                .collect(Collectors.toList()); // coleta em uma lista

        // Converte a lista para um datasource que o JasperReports entende
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(escanor);

        // Cria um mapa de parâmetros para passar para o relatório
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Relatórios de Pedido");

        // Compila o arquivo .jrxml (modelo do relatório) em um objeto JasperReport
        JasperReport jasperReport = JasperCompileManager.compileReport(
                getClass().getResourceAsStream("/relatorios/relatorio_pedidos.jrxml"));

        // Preenche o relatório com os dados e parâmetros
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        // Exporta o relatório preenchido para um arquivo PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, caminho);
    }
}
