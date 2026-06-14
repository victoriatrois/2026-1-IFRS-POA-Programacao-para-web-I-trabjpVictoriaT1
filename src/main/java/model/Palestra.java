package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Palestra extends Evento {

    private int tempo;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "palestrante_da_palestra",
        joinColumns = @JoinColumn(name = "palestra_id"),
        inverseJoinColumns = @JoinColumn(name = "palestrante_id")
    )
    private Set<Palestrante> palestrantes = new HashSet<>();

    public Palestra() {
    }

    public Palestra(Date duracao, String descricao, int capacidadeMaxima, StatusEvento situacao, Cronograma cronograma, int tempo) {
        super(duracao, descricao, capacidadeMaxima, situacao, cronograma);
        this.tempo = tempo;
    }

    public void adicionaPalestrante(Palestrante palestrante) {
        this.palestrantes.add(palestrante);
        palestrante.getPalestras().add(this);
    }
}
