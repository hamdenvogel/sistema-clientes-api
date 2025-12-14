package io.github.hvogel.clientes.rest;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.hvogel.clientes.criteria.SearchCriteria;
import io.github.hvogel.clientes.exception.RegraNegocioException;
import io.github.hvogel.clientes.model.entity.Produto;
import io.github.hvogel.clientes.rest.dto.InfoResponseDTO;
import io.github.hvogel.clientes.rest.dto.ProdutoDTO;
import io.github.hvogel.clientes.rest.dto.TotalProdutosDTO;
import io.github.hvogel.clientes.service.ProdutoService;
import io.github.hvogel.clientes.service.TotalProdutosService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	private static final String TITULO_INFORMACAO = "Informação";
	private static final String PRODUTO_NAO_ENCONTRADO = "Produto não encontrado.";

	private final ProdutoService produtoService;
	private final TotalProdutosService totalProdutosService;

	public ProdutoController(ProdutoService produtoService, TotalProdutosService totalProdutosService) {
		super();
		this.produtoService = produtoService;
		this.totalProdutosService = totalProdutosService;
	}

	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDTO salvar(@RequestBody @Valid ProdutoDTO dto) {

		Produto produto = new Produto();
		produto.setDescricao(dto.getDescricao());
		produto.setPreco(dto.getPreco());
		produto.setMarca(dto.getMarca());
		produto.setModelo(dto.getModelo());
	produto.setAnoFabricacao(dto.getAnoFabricacao());
	produto.setAnoModelo(dto.getAnoModelo());

	InfoResponseDTO infoResponseDTO = InfoResponseDTO.builder()
			.withMensagem("Produto criado com sucesso.")
			.withTitulo(TITULO_INFORMACAO)
			.build();

		try {
			produtoService.salvar(produto);
		} catch (RegraNegocioException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		dto.setInfoResponseDTO(infoResponseDTO);
		dto.setId(produto.getId());

		return dto;
	}

	@PutMapping("{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.OK)
	public InfoResponseDTO atualizar(@PathVariable Integer id, @RequestBody @Valid ProdutoDTO dto) {
		try {
			Produto produto = produtoService.obterPorId(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
			produto.setDescricao(dto.getDescricao());
			produto.setPreco(dto.getPreco());
			produto.setId(dto.getId());
		produto.setAnoFabricacao(dto.getAnoFabricacao());
		produto.setAnoModelo(dto.getAnoModelo());
			produto.setMarca(dto.getMarca());
			produto.setModelo(dto.getModelo());
			produtoService.atualizar(produto);

		} catch (RegraNegocioException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	return InfoResponseDTO.builder()
			.withMensagem("Produto atualizado com sucesso.")
			.withTitulo(TITULO_INFORMACAO)
			.build();
}

@DeleteMapping("{id}")
@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
@ResponseStatus(HttpStatus.OK)
public InfoResponseDTO deletar(@PathVariable Integer id) {

	Produto produto = produtoService.obterPorId(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, PRODUTO_NAO_ENCONTRADO));
	produtoService.deletar(produto);

	return InfoResponseDTO.builder()
			.withMensagem("Prestador deletado com sucesso.")
			.withTitulo(TITULO_INFORMACAO)
			.build();
}

@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Produto> obterTodos() {
		return produtoService.obterTodos();
	}

	@GetMapping("{id}")
	public Produto obterPorId(@PathVariable Integer id) {
		return produtoService.obterPorId(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, PRODUTO_NAO_ENCONTRADO));
	}

	@GetMapping("totalProdutos")
	public TotalProdutosDTO obterTotalProdutos() {
		return TotalProdutosDTO.builder()
				.withTotalProdutos(totalProdutosService.obterTotalProdutos())
				.build();
	}

	@PostMapping("pesquisa-avancada-criteria")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Page<Produto> specification(
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "4") Integer tamanhoPagina,
			@RequestParam(value = "sort", defaultValue = "descricao,asc") String[] sort,
			@RequestBody List<SearchCriteria> searchCriteria) {

		List<Order> orders = new ArrayList<>();

		if (sort[0].contains(",")) {
			// Faz o sort em mais de duas colunas.
			// sortOrder="field, direction"
			Direction direction;
			for (String sortOrder : sort) {
				String[] parts = sortOrder.split(",");

				if ("asc".equals(parts[1])) {
					direction = Direction.ASC;
				} else {
					direction = Direction.DESC;
				}

				orders.add(new Order(direction, parts[0]));
			}
		} else {
			// sort=[field, direction]
			Direction direction;
			if ("asc".equals(sort[1])) {
				direction = Direction.ASC;
			} else {
				direction = Direction.DESC;
			}

			orders.add(new Order(direction, sort[0]));
		}

		Pageable pageable = PageRequest.of(pagina, tamanhoPagina, Sort.by(orders));
		Page<Produto> produtos = null;
		produtos = produtoService.executaCriteria(searchCriteria, pageable);
		return produtos;
	}

	@GetMapping("pesquisa-avancada")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Page<Produto> list(
			@RequestParam(value = "page", defaultValue = "0") Integer pagina,
			@RequestParam(value = "size", defaultValue = "10") Integer tamanhoPagina,
			@RequestParam(value = "sort", defaultValue = "descricao,asc") String[] sort,
			@RequestParam(value = "descricao", required = false) String descricao) {

		List<Order> orders = new ArrayList<>();

		if (sort[0].contains(",")) {
			Direction direction;
			for (String sortOrder : sort) {
				String[] parts = sortOrder.split(",");
				if ("asc".equals(parts[1])) {
					direction = Direction.ASC;
				} else {
					direction = Direction.DESC;
				}

				orders.add(new Order(direction, parts[0]));
			}
		} else {
			Direction direction;
			if ("asc".equals(sort[1])) {
				direction = Direction.ASC;
			} else {
				direction = Direction.DESC;
			}

			orders.add(new Order(direction, sort[0]));
		}

		Pageable pageable = PageRequest.of(pagina, tamanhoPagina, Sort.by(orders));
		Page<Produto> produtos = null;
		if (descricao != null && !descricao.isEmpty()) {
			produtos = produtoService.pesquisarPelaDescricao(descricao, pageable);
		} else {
			produtos = produtoService.recuperarTodos(pageable);
		}

		return produtos;
	}

}
