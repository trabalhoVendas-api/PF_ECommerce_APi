package br.com.serratec.ecommerce.service;

import br.com.serratec.ecommerce.entity.RelatorioVendas;
import br.com.serratec.ecommerce.exception.ResourceNotFoundException;
import br.com.serratec.ecommerce.repository.RelatorioVendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioVendasService {

    @Autowired
    private RelatorioVendasRepository relatorioVendasRepository;

    public List<RelatorioVendas> listarRelatorios() {
        return relatorioVendasRepository.findAll();
    }

    public RelatorioVendas obterRelatorioPorId(Long id) {
        Optional<RelatorioVendas> relatorio = relatorioVendasRepository.findById(id);
        if (relatorio.isPresent()) {
            return relatorio.get();
        } else {
            throw new ResourceNotFoundException("Relatório não encontrado");
        }
    }

    public RelatorioVendas criarRelatorio(RelatorioVendas relatorioVendas) {
        return relatorioVendasRepository.save(relatorioVendas);
    }

    public RelatorioVendas atualizarRelatorio(Long id, RelatorioVendas relatorioVendasAtualizado) {
        RelatorioVendas relatorioExistente = obterRelatorioPorId(id);
        relatorioExistente.setNomeRelatorio(relatorioVendasAtualizado.getNomeRelatorio());
        relatorioExistente.setDataInicio(relatorioVendasAtualizado.getDataInicio());
        relatorioExistente.setDataFim(relatorioVendasAtualizado.getDataFim());
        relatorioExistente.setCliente(relatorioVendasAtualizado.getCliente());
        relatorioExistente.setProduto(relatorioVendasAtualizado.getProduto());
        return relatorioVendasRepository.save(relatorioExistente);
    }

    public void deletarRelatorio(Long id) {
        RelatorioVendas relatorioExistente = obterRelatorioPorId(id);
        relatorioVendasRepository.delete(relatorioExistente);
    }
}