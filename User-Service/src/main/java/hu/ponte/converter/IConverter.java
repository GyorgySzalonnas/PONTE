package hu.ponte.converter;

public interface IConverter<E, D> {

    public E toEntity(D dto);

    public D toDTO(E entity);
}
