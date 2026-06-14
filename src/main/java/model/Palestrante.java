package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Palestrante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;

    /*
     * A palestra precisa listar os seus palestrantes e o palestrante pode estar associado a várias palestras.
     * Como há utilidade em navegar nos dois sentidos, escolhi o relacionamento bidirecional.
     */
    @ManyToMany(mappedBy = "palestrantes")
    private Set<Palestra> palestras = new HashSet<>();

    public Palestrante() {
    }

    public Palestrante(String nome, String especialidade) {
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public Set<Palestra> getPalestras() {
        return palestras;
    }

    public void adicionaPalestra(Palestra palestra) {
        this.palestras.add(palestra);
    }
}
