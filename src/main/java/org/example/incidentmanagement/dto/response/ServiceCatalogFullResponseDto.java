package org.example.incidentmanagement.dto.response;

public class ServiceCatalogFullResponseDto {

    private Integer serivceid;
    private String Flow;


    public ServiceCatalogFullResponseDto() {}

    public ServiceCatalogFullResponseDto(Integer serivceid, String Flow) {
        this.serivceid = serivceid;
        this.Flow = Flow;
    }

    public Integer getSerivceid() {
        return serivceid;
    }
    public void setSerivceid(Integer serivceid) {
        this.serivceid = serivceid;
    }
    public String getFlow() {
        return Flow;
    }
    public void setFlow(String flow) {
        Flow = flow;
    }

}
