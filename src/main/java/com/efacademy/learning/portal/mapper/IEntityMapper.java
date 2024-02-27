package com.efacademy.learning.portal.mapper;

import java.util.List;

public interface IEntityMapper<D, E> {

	E toDto(D dto);

	List<E> toDto(List<D> dto);

	D toEntity(E entity);

	List<D> toEntity(List<E> entity);

}
