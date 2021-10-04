package bstorm.akimts.restapi.exceptions.models;

public class VoitureNotFoundException extends RuntimeException {

    private final Integer id;

    public VoitureNotFoundException(Integer id) {
        super("La voiture d'id {"+id+"} n'a pu être trouvée");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
