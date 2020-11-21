package org.drombler.mediacenter.integration.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.drombler.identity.core.DromblerId;
import org.drombler.identity.core.DromblerUserId;
import org.drombler.identity.management.DromblerIdentityProviderManager;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
@RequiredArgsConstructor
public class DromblerIdConverter implements AttributeConverter<DromblerId, String> {

    private final DromblerIdentityProviderManager dromblerIdentityProviderManager;

    @Override
    public String convertToDatabaseColumn(DromblerId attribute) {
        return attribute != null ? attribute.getDromblerIdFormatted() : null;
    }

    @Override
    public DromblerId convertToEntityAttribute(String dbData) {
        return dbData != null ? DromblerUserId.parseDromblerUserId(dbData, dromblerIdentityProviderManager) : null;
    }
}
