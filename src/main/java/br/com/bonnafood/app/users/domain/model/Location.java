package br.com.bonnafood.app.users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    @Column(name = "location_country")
    private String country;

    @Column(name = "location_state")
    private String state;

    @Column(name = "location_city")
    private String city;
}
