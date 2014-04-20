package pij.ryan.durling.serializers;

public interface IdSerializer {

    /**
     * persist the id
     *
     * @param id Integer
     */
    void persist(Integer id);

    /**
     * get the persisted id
     *
     * @return Integer
     */
    Integer getId();

    /**
     * Check if data exists
     *
     * @return boolean
     */
    boolean dataExists();
}
