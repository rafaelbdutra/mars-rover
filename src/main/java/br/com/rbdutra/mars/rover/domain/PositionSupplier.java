package br.com.rbdutra.mars.rover.domain;

import java.util.function.Supplier;

@FunctionalInterface
public interface PositionSupplier extends Supplier<Position> {

}
