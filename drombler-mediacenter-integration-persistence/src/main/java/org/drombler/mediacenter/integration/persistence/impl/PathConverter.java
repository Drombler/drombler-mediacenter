package org.drombler.mediacenter.integration.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.drombler.identity.management.DromblerIdentityProviderManager;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

@Converter
@RequiredArgsConstructor
public class PathConverter implements AttributeConverter<Path, URI> {

    private final DromblerIdentityProviderManager dromblerIdentityProviderManager;

    @Override
    public URI convertToDatabaseColumn(Path attribute) {
        return attribute != null ? attribute.toUri() : null;
    }

    @Override
    public Path convertToEntityAttribute(URI dbData) {
        return dbData != null ? Paths.get(dbData) : null;
    }
}
