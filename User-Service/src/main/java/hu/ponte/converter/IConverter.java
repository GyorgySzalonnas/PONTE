package hu.ponte.converter;

/**
 * @param <E> Entity
 * @param <D> Dto
 *
 * Interface to be implemented by DTO converters. Takes an Entity and DTO class as generic arguments.
 */
public interface IConverter<E, D> {

    public E toEntity(D dto);

    public D toDTO(E entity);
}
