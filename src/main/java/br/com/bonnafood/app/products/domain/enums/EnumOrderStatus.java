package br.com.bonnafood.app.products.domain.enums;

import java.util.List;

public enum EnumOrderStatus {
    CREATED("Created :)"),
    CONFIRMED("Confirmed!", CREATED),
    DELIVERED("Delivered :rocket:", CONFIRMED),
    CANCELED("Canceled :(", CONFIRMED);

    private final String description;
    private final List<EnumOrderStatus> previousStatus;

    EnumOrderStatus(String description, EnumOrderStatus... previousStatus) {
        this.description = description;
        this.previousStatus = List.of(previousStatus);
    }

    public boolean cannotChangeTo(EnumOrderStatus status) {
        return !status.previousStatus.contains(this);
    }

    public boolean canChangeTo(EnumOrderStatus status) {
        return !this.cannotChangeTo(status);
    }


}
