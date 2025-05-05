package com.vileladev.enderecador.service;

import com.vileladev.enderecador.model.Endereco;
import com.vileladev.enderecador.model.enums.EstadoSigla;
import com.vileladev.enderecador.model.enums.PaisSigla;
import com.vileladev.enderecador.model.dto.EnderecoDTO;
import com.vileladev.enderecador.repository.EnderecoRepository;
import com.vileladev.enderecador.service.impl.EnderecoServiceImpl;
import com.vileladev.enderecador.utils.mapper.EnderecoMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private EnderecoMapper enderecoMapper;

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    private EnderecoDTO enderecoDTO;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(1L);
        enderecoDTO.setEndereco("Rua Teste");
        enderecoDTO.setCep("12345-678");
        enderecoDTO.setTipoLogradouro("Rua");
        enderecoDTO.setLogradouro("Teste");
        enderecoDTO.setBairro("Centro");
        enderecoDTO.setCidade("São Paulo");
        enderecoDTO.setEstado("São Paulo");
        enderecoDTO.setEstadoSigla(EstadoSigla.SP);
        enderecoDTO.setPais("Brasil");
        enderecoDTO.setPaisSigla(PaisSigla.BR);

        endereco = new Endereco();
        endereco.setId(1L);
        endereco.setEndereco("Rua Teste");
        endereco.setCep("12345-678");
        endereco.setTipoLogradouro("Rua");
        endereco.setLogradouro("Teste");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        endereco.setEstadoSigla(EstadoSigla.SP);
        endereco.setPais("Brasil");
        endereco.setPaisSigla(PaisSigla.BR);
    }

    @Test
    void salvar_DeveSalvarEnderecoComSucesso() {
        when(enderecoMapper.toEntity(any(EnderecoDTO.class))).thenReturn(endereco);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(enderecoMapper.toDTO(any(Endereco.class))).thenReturn(enderecoDTO);

        EnderecoDTO resultado = enderecoService.salvar(enderecoDTO);

        assertNotNull(resultado);
        assertEquals(enderecoDTO.getId(), resultado.getId());
        assertEquals(EstadoSigla.SP, resultado.getEstadoSigla());
        assertEquals(PaisSigla.BR, resultado.getPaisSigla());
        verify(enderecoRepository).save(any(Endereco.class));
    }

    @Test
    void atualizar_QuandoEnderecoExiste_DeveAtualizarComSucesso() {
        when(enderecoRepository.existsById(anyLong())).thenReturn(true);
        when(enderecoMapper.toEntity(any(EnderecoDTO.class))).thenReturn(endereco);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(enderecoMapper.toDTO(any(Endereco.class))).thenReturn(enderecoDTO);

        EnderecoDTO resultado = enderecoService.atualizar(1L, enderecoDTO);

        assertNotNull(resultado);
        assertEquals(enderecoDTO.getId(), resultado.getId());
        assertEquals(EstadoSigla.SP, resultado.getEstadoSigla());
        assertEquals(PaisSigla.BR, resultado.getPaisSigla());
        verify(enderecoRepository).save(any(Endereco.class));
    }

    @Test
    void atualizar_QuandoEnderecoNaoExiste_DeveLancarExcecao() {
        when(enderecoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> 
            enderecoService.atualizar(1L, enderecoDTO)
        );
    }

    @Test
    void buscarPorId_QuandoEnderecoExiste_DeveRetornarEndereco() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.of(endereco));
        when(enderecoMapper.toDTO(any(Endereco.class))).thenReturn(enderecoDTO);

        EnderecoDTO resultado = enderecoService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(enderecoDTO.getId(), resultado.getId());
        assertEquals(EstadoSigla.SP, resultado.getEstadoSigla());
        assertEquals(PaisSigla.BR, resultado.getPaisSigla());
    }

    @Test
    void buscarPorId_QuandoEnderecoNaoExiste_DeveLancarExcecao() {
        when(enderecoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> 
            enderecoService.buscarPorId(1L)
        );
    }

    @Test
    void deletar_QuandoEnderecoExiste_DeveDeletarComSucesso() {
        when(enderecoRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(enderecoRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> enderecoService.deletar(1L));
        verify(enderecoRepository).deleteById(1L);
    }

    @Test
    void deletar_QuandoEnderecoNaoExiste_DeveLancarExcecao() {
        when(enderecoRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> 
            enderecoService.deletar(1L)
        );
    }

    @Test
    void listarTodos_DeveRetornarListaDeEnderecos() {
        List<Endereco> enderecos = Arrays.asList(endereco);
        List<EnderecoDTO> enderecosDTO = Arrays.asList(enderecoDTO);

        when(enderecoRepository.findAll()).thenReturn(enderecos);
        when(enderecoMapper.toDTO(any(Endereco.class))).thenReturn(enderecoDTO);

        List<EnderecoDTO> resultado = enderecoService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(enderecoDTO.getId(), resultado.get(0).getId());
        assertEquals(EstadoSigla.SP, resultado.get(0).getEstadoSigla());
        assertEquals(PaisSigla.BR, resultado.get(0).getPaisSigla());
        verify(enderecoRepository).findAll();
    }
} 