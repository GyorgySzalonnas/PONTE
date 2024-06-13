package hu.ponte.converter;

public interface IConverter<E, D> {

    public E toEntity(D d);

    public D toDTO(E e);
}
