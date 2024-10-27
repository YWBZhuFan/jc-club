package com.jichi.subject.infra.basic.es;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuFan
 * @data 2024/10/13/013 23:35
 */
@Component
@ConfigurationProperties(prefix = "es.cluster")
public class EsConfigProperties {

    private List<EsClusterConfig> esConfig = new ArrayList<>();

    public List<EsClusterConfig> getEsConfig() {
        return esConfig;
    }

    public void setEsConfig(List<EsClusterConfig> esConfig) {
        this.esConfig = esConfig;
    }
}
