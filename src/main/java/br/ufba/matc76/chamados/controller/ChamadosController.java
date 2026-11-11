package br.ufba.matc76.chamados.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufba.matc76.chamados.model.Chamado;
import br.ufba.matc76.chamados.model.Prioridade;
import br.ufba.matc76.chamados.model.Status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chamados")
public class ChamadosController {

    private final List<Chamado> chamados = new ArrayList<>();

    private final AtomicLong contadorId = new AtomicLong(10);

    public ChamadosController() {
        carregarChamados();
    }

    private void carregarChamados() {

        chamados.add(new Chamado(1L, "Impressora não funciona",
                "Maria Silva", "Financeiro",
                Prioridade.ALTA, Status.ABERTO));

        chamados.add(new Chamado(2L, "Computador lento",
                "João Santos", "RH",
                Prioridade.MEDIA, Status.EM_ANDAMENTO));

        chamados.add(new Chamado(3L, "Problema no acesso ao sistema",
                "Ana Costa", "Financeiro",
                Prioridade.ALTA, Status.EM_ANDAMENTO));

        chamados.add(new Chamado(4L, "Solicitação de novo monitor",
                "Pedro Lima", "TI",
                Prioridade.BAIXA, Status.ABERTO));

        chamados.add(new Chamado(5L, "Internet sem conexão",
                "Carla Souza", "Marketing",
                Prioridade.ALTA, Status.RESOLVIDO));

        chamados.add(new Chamado(6L, "Erro no e-mail corporativo",
                "Lucas Alves", "RH",
                Prioridade.MEDIA, Status.ABERTO));

        chamados.add(new Chamado(7L, "Teclado com defeito",
                "Fernanda Rocha", "Financeiro",
                Prioridade.BAIXA, Status.RESOLVIDO));

        chamados.add(new Chamado(8L, "Sistema apresentando erro",
                "Rafael Oliveira", "Vendas",
                Prioridade.ALTA, Status.ABERTO));

        chamados.add(new Chamado(9L, "Instalação de software",
                "Juliana Martins", "TI",
                Prioridade.MEDIA, Status.EM_ANDAMENTO));

        chamados.add(new Chamado(10L, "Configuração de impressora",
                "Bruno Ferreira", "Marketing",
                Prioridade.BAIXA, Status.RESOLVIDO));
    }

    // Listar todos os chamados
    @GetMapping
    public List<Chamado> listarTodos() {
        return chamados;
    }

    // Consultar chamado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Chamado> buscarPorId(@PathVariable Long id) {

        return chamados.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Filtrar por setor
    @GetMapping("/setor")
    public List<Chamado> filtrarPorSetor(@RequestParam String nome) {

        return chamados.stream()
                .filter(c -> c.getSetor().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }

    // Filtrar por prioridade
    @GetMapping("/prioridade")
    public List<Chamado> filtrarPorPrioridade(@RequestParam String nivel) {

        return chamados.stream()
                .filter(c -> c.getPrioridade().name()
                        .equalsIgnoreCase(nivel))
                .collect(Collectors.toList());
    }

    // Buscar pelo título
    @GetMapping("/busca")
    public List<Chamado> buscarPorTitulo(@RequestParam String texto) {

        return chamados.stream()
                .filter(c -> c.getTitulo().toLowerCase()
                        .contains(texto.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Listar chamados de alta prioridade ainda não resolvidos
    @GetMapping("/criticos")
    public List<Chamado> listarCriticos() {

        return chamados.stream()
                .filter(c -> c.getPrioridade() == Prioridade.ALTA)
                .filter(c -> c.getStatus() != Status.RESOLVIDO)
                .collect(Collectors.toList());
    }

    // Resumo dos chamados por status
    @GetMapping("/resumo")
    public Map<String, Long> resumo() {

        Map<String, Long> resultado = new LinkedHashMap<>();

        resultado.put("abertos", contarPorStatus(Status.ABERTO));
        resultado.put("em_andamento", contarPorStatus(Status.EM_ANDAMENTO));
        resultado.put("resolvidos", contarPorStatus(Status.RESOLVIDO));
        resultado.put("total", (long) chamados.size());

        return resultado;
    }

    private long contarPorStatus(Status status) {

        return chamados.stream()
                .filter(c -> c.getStatus() == status)
                .count();
    }

    // Cadastrar novo chamado
    @PostMapping
    public ResponseEntity<Chamado> cadastrar(
            @RequestBody Chamado novoChamado) {

        novoChamado.setId(contadorId.incrementAndGet());

        chamados.add(novoChamado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoChamado);
    }
}

