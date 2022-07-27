package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString

public class Region {


    @JsonProperty("region_id")
    private int rId;

    @JsonProperty("region_name")
    private String region_name;

    @JsonProperty("links")
    private List<Link> links;



}
