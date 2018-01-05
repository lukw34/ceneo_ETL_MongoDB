package uek.ceneo.etl.services;

/**
 * Interfejs do obslugi procesu ETL
 */
public interface ETL {
    /**
     * Przeprowaza proces extract
     *
     * @param id Identyfiaktor produktu
     * @return Stosowny komunikat
     */
    String extract(String id);

    /**
     * Przeprowaza proces transform
     *
     * @param id Identyfiaktor produktu
     * @return Stosowny komunikat
     */
    String transform(String id);

    /**
     * Przeprowaza proces load
     *
     * @param id Identyfiaktor produktu
     * @return Stosowny komunikat
     */
    String load(String id);
}
