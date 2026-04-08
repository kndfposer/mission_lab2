package org.example.loader;

import org.example.builder.MissionBuilder;

public interface MissionDataAdapter<T> {
    void fill(T source, MissionBuilder builder);
}
