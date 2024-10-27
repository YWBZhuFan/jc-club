package com.jichi.subject.infra.basic.es;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZhuFan
 * @data 2024/10/13/013 23:39
 */
@Data
public class EsIndexInfo implements Serializable {
    /**
     * 集群名称
     */
    private String clusterName;
    /**
     * 索引名称
     */
    private String indexName;
}
