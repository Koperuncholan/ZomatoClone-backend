package com.nseit.ZomatoClone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Integer fileId;
    private String Location;

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;

    @JsonIgnore
    @OneToOne(mappedBy = "file")
    private Dish dish;


}
