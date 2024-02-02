package com.mykhailoparm.Notes.mappers;

public interface Mapper<A, B> {
    B mapToDTO(A a);
    A mapFromDTO(B b);
}
