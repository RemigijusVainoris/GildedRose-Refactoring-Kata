package com.vainoris.gildedroseservice.mappers;

public interface Mapper<S, R>
{
    S fromDTO(R in);

    R toDTO(S in);
}
