package nz.co.tm.API.responseData;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class LatestLingData {
    @JsonProperty("TotalCount")
    public int totalCount;
    @JsonProperty("Page")
    public int page;
    @JsonProperty
    public ArrayList<List> list;
}
